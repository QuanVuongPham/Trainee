package RestfulWebService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static spark.Spark.*;

/**
 * viết 1 restful web service trả về bình phương giá trị của parameter
 *
 * bài này em sử dụng spark
 */

public class RestfulWebService
{

    public static void main(String[] args)
    {
        Random random = new Random();
        String randomParam = Integer.toString(random.nextInt(10000));
        String randomUrl = "http://localhost:4567/prime?n=" + randomParam;

        // kích hoạt service
        get("/prime", (request, response) ->
        {
            String params = request.queryParams("n");
            return Integer.toString(Integer.parseInt(params) * Integer.parseInt(params));
        });


        //chờ 1s để đảm bảo service hoàn tất, sau đó connect và đọc dữ liệu về để kiểm tra
        try
        {
            TimeUnit.SECONDS.sleep(1);
            URL url = new URL(randomUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String InputLine;
            StringBuffer Response = new StringBuffer();
            while ((InputLine = in.readLine()) != null)
            {
                Response.append(InputLine);
            }
            in.close();

            System.out.println(Response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
