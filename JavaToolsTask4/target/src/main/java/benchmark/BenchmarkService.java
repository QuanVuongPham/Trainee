package benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static spark.Spark.*;

/**
 * Yêu cầu :
 * sử dụng 4 luồng call service, viết benchmark
 *
 * em sẽ dùng spark để tạo service, sau đó benchmark nó.
 */

public class BenchmarkService
{
    public synchronized void CallService()
    {
        //tạo link ngẫu nhiên   
        Random random = new Random();
        String randomParam = Integer.toString(random.nextInt(10000));
        String randomUrl = "http://localhost:4567/prime?n=" + randomParam;


        //service lấy từ bài 3 sử dụng spark, trả về gía trị bình phương của parameter
        get("/prime", (request, response) ->
        {
            String params = request.queryParams("n");
            return Integer.toString(Integer.parseInt(params) * Integer.parseInt(params));
        });

        //gửi request tới url vừa tạo
        try
        {
            TimeUnit.SECONDS.sleep(1);
            URL url = new URL(randomUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //tạo 4 luồng song song gọi vào service
    public void ThreadCallService()
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Runnable task = () -> CallService();

        executorService.submit(task);
        executorService.submit(task);
        executorService.submit(task);
        executorService.submit(task);

        executorService.shutdown();
    }


    
    @org.openjdk.jmh.annotations.Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Measurement(time = 1)
    public void init()
    {
        ThreadCallService();
    }

    public static void main(String[] args) {
        Options options = new OptionsBuilder().include(BenchmarkService.class.getSimpleName()).forks(3).build();

        try {
            new Runner(options).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }

}