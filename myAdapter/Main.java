package myAdapter;

import java.util.Hashtable;

public class Main {
    public static void main(String[] args) {
        Hashtable<Object, Object> map = new Hashtable<>();
        map.put(map, null);
        System.out.println("Hash code of the map: " + map.hashCode());
    }
}
