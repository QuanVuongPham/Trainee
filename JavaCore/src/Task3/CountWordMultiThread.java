package Task3;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;


/**
 * đếm từ từ nhiều file trong 1 folder, và tìm ra 10 từ xuất hiện nhiều nhất và ít nhất, sử dụng tối da 6 luồng.
 */

/**
 * Sử dụng Map với từng file trong folder ,với key là từ và value là số lần xuất hiện của nó
 * <p>
 * Nối tất cả các Map lại
 * <p>
 * để lấy ra 10 từ xuất hiện nhiều nhất, sắp xếp map theo value, lưu vào 1 set cho tới khi set có size là 10 thì thôi
 * <p>
 * sắp xếp value ngược lại để lấy 10 từ xuất hiện ít nhất
 * <p>
 * (định nghĩa hàm trong lớp và gọi hàm trong method run(), khai báo đối tượng trong hàm main)
 */
public class CountWordMultiThread implements Runnable {
    private String name;
    static File InputFile = new File("InputFolder");
    static File[] ArrayFile = InputFile.listFiles();
    static Map<String, Integer> WordMap = new HashMap<String, Integer>();

    public CountWordMultiThread(String name) {
        this.name = name;
    }

    public CountWordMultiThread() {

    }

    public void run() {
        //for (int i = 0; i < ArrayFile.length; i++) {
        synchronized (this)
        {
            try {
                CountWordFromFolder(this.name);
            } catch (IOException e) {
                e.printStackTrace();
            }
            WordMap.putAll(WordMap);
        }
        //}


    }

    public void CountWordFromFolder(String InputFileName) throws IOException {
        //String InputFileName = ArrayFile[i].toString();

        Scanner scanner = new Scanner(Paths.get("./InputFolder/" + InputFileName));
        while (scanner.hasNext()) {
            String word = scanner.next();

            if (!WordMap.containsKey(word)) {
                WordMap.put(word, 1);
            } else {
                int count = WordMap.get(word) + 1;
                WordMap.put(word, count);
            }
        }
        scanner.close();
    }

    public static void GetThe10MostAppear() {
        Map<String, Integer> SortValueIncrease = WordMap
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Set<String> StringSet = new HashSet<String>();

        for (Map.Entry<String, Integer> entry : SortValueIncrease.entrySet()) {
            String word = entry.getKey();
            StringSet.add(word);
            if (StringSet.size() == 10) break;
        }

        System.out.println(StringSet);
    }

    public static void GetThe10LeastAppear() {
        Map<String, Integer> SortValueDecrease = WordMap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Set<String> StringSet = new HashSet<String>();

        for (Map.Entry<String, Integer> entry : SortValueDecrease.entrySet()) {
            String word = entry.getKey();
            StringSet.add(word);
            if (StringSet.size() == 10) break;
        }

        System.out.println(StringSet);
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(6);
        File file = new File("./InputFolder");
        String[] dir = file.list();

        for (int i = 0; i < dir.length; i++)
        {
            executor.submit(new CountWordMultiThread(dir[i]));
        }

        try
        {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        executor.shutdown();


        //CountWordMultiThread pro = new CountWordMultiThread();
        GetThe10MostAppear();
        GetThe10LeastAppear();
    }
}
//
//public class CountWordMultiThread
//{
//    public static void main(String[] args)
//    {
//        ExecutorService executor = Executors.newFixedThreadPool(6);
//        File file = new File("./InputFolder");
//        String[] dir = file.list();
//        for(int i =0;i<dir.length;i++){
//        executor.submit(new CountWordMultiThread(dir[i]));}
//        executor.shutdown();
//
//
//
//        CountWordMultiThread pro = new CountWordMultiThread();
//        pro.GetThe10MostAppear();
//        pro.GetThe10LeastAppear();
//    }
//}
