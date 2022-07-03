package com.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 - Map computeIfAbsent method

     - default V computeIfAbsent(K key,
            Function<K, V> mappingFunction)

 - Default methods
     - Default methods can be overridden by implementations
     - They are available to instances but have a default implementation
     - They are not static

 - What is a Function
     - A piece of code we can pass around
     - Takes one argument and returns another
     - Think something that maps a value to something else
*/
public class MapComputeIfAbsentMethod {

    public static void main(String[] args) {
        new MapComputeIfAbsentMethod().concurrent();
    }

    void simpleExample() {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(1, 1);

        /*
         - Why not use put?
         - put replaces an existing value whereas putIfAbsent does not
         - It returns the computed value
         - Or the existing value if there was one
         - Basically the 'up to date' value
        */
        Integer upToDateValue = m.computeIfAbsent(2, k -> k + 2);

        print(m);
        print(upToDateValue);
    }

    // Why not putIfAbsent()?
    void whyNotPutIfAbsent() {
        Map<Integer, Integer> m = new HashMap<>();
        // Because it returns the previous value (which could be null)
        // Also won't take a function which facilitates some cool stuff
        Integer oldValue = m.putIfAbsent(1, 1);
    }

    void newValue() {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(1, 1);
        // Convenient way of construing a new object (assuming the constructor takes
        // the key as an argument
        m.computeIfAbsent(2, Integer::valueOf);
    }

    void multiValued() {
        Map<Integer, Map<Integer, Integer>> m = new HashMap<>();

        // Before
        Map<Integer, Integer> value = new HashMap<>();
        value.put(1, 1);
        m.put(1, value);

        /*
         - Convenient way of construing a new object (assuming the constructor takes
           the key as an argument
         - Hang on? Whats the argument? It is the initial capacity
         - Returns the existing one if no new one needs to be created
        */
        m.computeIfAbsent(2, HashMap::new).put(1, 1);
        m.computeIfAbsent(2, k -> new HashMap<>()).put(1, 1);
    }

    void memoize(int i ) {
        Map<Integer, Integer> memoCache = new ConcurrentHashMap<>();
        // Convenient method to memoize
        int value = memoCache.computeIfAbsent(i, k -> k * 21);
        print(value);
    }

    /*
     - Exceptions
          - NullPointerException
                 - if the specified key is null and this map does not
                   support null keys, or the mappingFunction is null
          - UnsupportedOperationException
                 - if the put operation is not supported by this
                   map (optional)
                 - think immutable map
          - ClassCastException
                 - if the class of the specified key or value prevents it
                   from being stored in this map (optional)
                 - if you implement a map and want to restrict types
          - IllegalArgumentException
                 - if some property of the specified key or value
                   prevents it from being stored in this map (optional)
                 - if you are implementing a map and want to restrict the values
          - ConcurrentModificationException
                 - Not declared by the default implementation
                 - Is there in HashMap
                 - Should be there for non-concurrent maps
                 - As a user don't modify the collection in the mapping function
    */

    void concurrent() {
        Map<Integer, Integer> m = new HashMap<>();
        m.computeIfAbsent(1, k -> m.put(2, 2));
    }

    // -----------------------------------------------------------------------------------

    static void print(Map<?, ?> m) {
        m.entrySet(). forEach(System.out::println);
    }

    static void print(Object o) {
        System.out.println(o.toString());
    }

}
