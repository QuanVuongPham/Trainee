
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * s : đề bài cho
 * sPrevious : giá trị liền trước của S trong quá trình lấy số user
 * count : biến đếm để xử lý việc in ra DEBUG, đề yêu cầu 12s thì in ra DEBUG
 * tương đương với count == 6.
 */
public class ParseJsonData
{
    public static int s, sPrevious, count = 0;
    public static final Logger logger = LoggerFactory.getLogger(ParseJsonData.class);

    public synchronized void countS()
    {
        try
        {
            String url = "http://news.admicro.vn:10002/api/realtime?domain=kenh14.vn";
            URL Obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) Obj.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String InputLine;
            StringBuffer Response = new StringBuffer();
            while ((InputLine = in.readLine()) != null)
            {
                Response.append(InputLine);
            }
            in.close();

            JSONObject JsonData = new JSONObject(Response.toString());
            JSONArray JsonArray = JsonData.getJSONArray("source");

            s = 0;
            for(int i = 0; i < JsonArray.length(); i++)
            {
                JSONObject JsonArrayObject = JsonArray.getJSONObject(i);
                s += JsonArrayObject.getInt("number");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void LogS()
    {
        if( count == 0) logger.info(Integer.toString(s));
        else if((float) s/sPrevious >= 1.015)
        {
            logger.info(Integer.toString(s));
            count = 0;
        }
        else if(count == 6)
        {
            logger.debug(Integer.toString(s));
            count = 0;
        }
        else System.out.println(s);

        sPrevious = s;
        count++;
    }


    public static void main(String[] args)
    {
        ParseJsonData parseJsonDataObject = new ParseJsonData();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        Runnable countS = () -> parseJsonDataObject.countS();

        Runnable LogS = () -> parseJsonDataObject.LogS();

        executorService.scheduleAtFixedRate(countS, 0, 1, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(LogS, 0, 2, TimeUnit.SECONDS);
    }
}
