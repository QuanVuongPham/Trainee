package Task2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.*;


/**
 * cho file input, đọc file và đến số lần xuất hiện của từng từ, ghi ra file output
 */
public class CountWord
{

    /**
     * Sử dụng Map với key là từ và value là số lần xuất hiện của từ
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        Map<String, Integer> WordMap = new HashMap<String, Integer>();

        Scanner scanner = new Scanner(Paths.get("input"));
        while (scanner.hasNext())
        {
            String word = scanner.next();

            if(!WordMap.containsKey(word))
            {
                WordMap.put(word, 1);
            }
            else
            {
                int count = WordMap.get(word) + 1;
                WordMap.put(word, count);
            }
        }
        scanner.close();

        PrintWriter  printWriter = new PrintWriter("output", "UTF-8");
        for(Map.Entry<String, Integer> entry : WordMap.entrySet())
        {
            String key = entry.getKey();
            int value  = entry.getValue();

            printWriter.println(key + " : " + value);
        }

        printWriter.close();











//        Set<String> WordSet = new TreeSet<String>();
//        ArrayList<String> WordList = new ArrayList<String>();
//
//
//        Scanner scanner = new Scanner(Paths.get("cat"));
//        while (scanner.hasNext())
//        {
//            String word;
//            word = scanner.next();
//            WordSet.add(word);
//            WordList.add(word);
//        }
//
//        System.out.println(WordSet);
//        System.out.println(WordList);
//
//        scanner.close();
//
//        PrintWriter printWriter = new PrintWriter("output", "UTF-8");
//
//        Object[] ArrayWordSet = WordSet.toArray();
//        Object[] ArrayWordList = WordList.toArray();
//
//
//        for(int i = 0; i < ArrayWordSet.length; i++)
//        {
//            int count = 0;
//            for(int j = 0; j < ArrayWordList.length; j++)
//            {
//                if( ArrayWordSet[i] == ArrayWordList[j]) count++;
//            }
//            printWriter.println(ArrayWordSet[i] + " " + count);
//        }
//
//        printWriter.close();



    }
}
