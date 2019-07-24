package guava;

import benchmark.BenchmarkService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

import static spark.Spark.get;

/**
 * dùng guava cache để giảm số lần tính toán và so sánh với đa luồng.
 *
 * em sẽ dùng service bài 3 làm nội dung override hàm load của CacheLoader rồi benchmark nó
 */
public class GuavaCacheService
{
    public static Random random = new Random();
    // map để chứa tạm giá trị đầu ra của service, key là tham số, value là đầu ra của service
    public static Map<String, String> GuavaMap = new HashMap<String, String>();
    public static String inputParam; // param đầu vào cho link
    public static LoadingCache<String, String> GuavaCache;

    public static void main(String[] args)
    {
        GuavaCache = CacheBuilder.newBuilder()
                        .maximumSize(10000)
                        .expireAfterAccess(30, TimeUnit.SECONDS)
                        .build(new CacheLoader<String, String>()
                        {
                            @Override
                            public String load(String inputParam) throws Exception
                            {
                                return outputParam();
                            }
                        });

        Options options = new OptionsBuilder().include(BenchmarkService.class.getSimpleName()).forks(1).build();

        try {
            new Runner(options).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }


    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void useGuavaCache()
    {
        outputParam();
        try {
            System.out.println(GuavaCache.get(inputParam()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    //tạo parameter để truyền vào link và làm tham số để lấy kết quả từ cache.
    private static String inputParam()
    {
        inputParam = Integer.toString(random.nextInt(10000));
        return  inputParam;
    }

    //gọi lại service từ bài 3 và lấy kết quả đầu ra để làm nội dung override hàm load của CacheLoader().
    private static String outputParam()
    {
        String randomUrl = "http://localhost:4567/prime?n=" + inputParam();
        get("/prime", (request, response) ->
        {
            String params = request.queryParams("n");
            return Integer.toString(Integer.parseInt(params) * Integer.parseInt(params));
        });

        try
        {
            TimeUnit.SECONDS.sleep(1);
            URL url = new URL(randomUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
            {
                stringBuffer.append(inputLine);
            }
            in.close();

            String outputParam = stringBuffer.toString(); // kết quả đầu ra

            GuavaMap.put(inputParam, outputParam);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return GuavaMap.get(inputParam);
    }
}
