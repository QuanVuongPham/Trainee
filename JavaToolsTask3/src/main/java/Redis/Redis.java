package Redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * ghi vào redis sử dụng jedis
 */
public class Redis
{
    public static void main(String[] args)
    {
        Jedis jedis = new Jedis("127.0.0.1");

        Map<String, String> test = new HashMap<String, String>();
        for(int i = 0; i <= 10000; i++)
        {
            test.put(Integer.toString(i), Integer.toString(i*i));
        }

        jedis.hmset("test", test);

//        for(String keyMap : test.keySet())
//        {
//            System.out.println(keyMap + " " + test.get(keyMap));
//        }
    }
}
