package club.uglyland.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : ZGQ
 * @Date : 2020/7/19 19:29
 * @Version : 1.0
 */
public class CollectionsUtil {
    public static <K,V> Map<K,V>  getMap(K key,V value){
        Map<K,V> map = new HashMap<>();
        map.put(key,value);
        return map;
    }

    public static <K,V> Map<K,V>  getMap(K key1,V value1,K key2 ,V value2){
        Map<K,V> map = new HashMap<>();
        map.put(key1,value1);
        map.put(key2,value2);
        return map;
    }
}
