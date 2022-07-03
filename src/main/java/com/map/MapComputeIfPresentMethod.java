package com.map;

import java.util.HashMap;
import java.util.Map;

/*
 - Map computeIfPresent method

    default V computeIfPresent(K key,
            BiFunction<? super K, ? super V, ? extends V> remappingFunction)

     default V computeIfPresent(K key,
            BiFunction<K, V, V> remappingFunction)

 - Default methods
     - Default methods can be overridden by implementations
     - They are available to instances but have a default implementation
     - They are not static

 - What is a BiFunction
     - A piece of code we can pass around
     - Takes two arguments and returns another
     - Think something that maps two inputs to something else
*/
public class MapComputeIfPresentMethod {

    public static void main(String[] args) {
        new MapComputeIfPresentMethod().concurrent();
    }

    void basics() {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(2, 2);
        print(m);
        m.computeIfPresent(2, (k, v) -> 6 * 9);
        print(m);
    }

    // No effect if that key is is not already in the map
    void noExistingValue() {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(2, 2);
        print(m);
        m.computeIfPresent(3, (k, v) -> (k * v));
        print(m);
    }

    void computeANull() {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(2, 2);
        print("Before " + m);
        m.computeIfPresent(2, (k, v) -> null);
        print("After " + m);
    }

    /*
     - Why not computeIfAbsent?
        - We only want to operate if a value is present
     - Why not put?
        - We want to compute a value with a function
        - We don't want to write a value if there is not already a value for this key
     - Why not compute?
        - Kind of similar to put in that it will write even if there is no existing value
        - It does accept a function though
        - Similar to merge except that merge also takes a new value
        - This method only computes from the key and existing value
        - If the existing value is null you will want to handle that in your function
        - But then if you need to handle null with a hard coded value then why not just use merge?
     - Why not merge?
        - Similar to compute but you pass in a new value in case one is not set
        - You only execute the merge function if an existing value is present
        - Differs from computeIfPresent because this writes a value even where one does not exist
    */

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
        m.put(1, 1);
        m.computeIfPresent(1, (k, v) -> m.put(2, 2));
    }

    // -----------------------------------------------------------------------------------

    static void print(Map<?, ?> m) {
        m.entrySet(). forEach(System.out::println);
    }

    static void print(Object o) {
        System.out.println(o.toString());
    }

}
