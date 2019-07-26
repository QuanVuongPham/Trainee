import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetContentFromUrl
{
    public static void main(String[] args)
    {
        try
        {
            Document doc = Jsoup.connect("http://dantri.com.vn").get();
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd-MM-YYYY");
            Date date = new Date();
            String fileName = dateFormat.format(date);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            String content = doc.text();
            bufferedWriter.write(content);
            System.out.println("done");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
