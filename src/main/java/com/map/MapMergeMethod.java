package com.map;

import java.util.HashMap;
import java.util.Map;

/*
 - Map merge method
 - default V merge(
    - K key,
    - V value,
    - BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
    - BiFunction<? super V, V, V> remappingFunction) {

 - Default methods
     - Default methods can be overridden by implementations
     - They are available to instances but have a default implementation
     - They are not static

 - What is a BiFunction
     - Takes two arguments
     - Returns one
     - Think merging two values

*/
public class MapMergeMethod {

    public static void main(String[] args) {
        Map<String, String> m = new HashMap<>();
        m.put("ID123", "Joe");

        print(m);

        // Combine if key present and has a value
        // Return the new value or null if no value associated with the key
        String upToDateValue = m.merge("ID123", "Joe", (v1, v2) -> v1 + v2);

        print(upToDateValue);

        // Add if key not present - ignore combine function
        m.merge("ID999", "Ali", (v1, v2) -> v1 + v2);

        print(m);

        // Remove if combine function evaluates to null
        // Careful! This will remove the existing value!
        m.merge("ID999", "Smith", (v1, v2) -> null);

        print("x" + m);

        /*
         - Nulls
        */

        // Null key
        // A null key doesn't make much sense so why is it allowed?
        m.merge(null, "a", (v1, v2) -> null);
        // Well some collections allow null keys like HashMap for example

        print("y" + m);

        // // Null value
        // // The value you pass in cannot be null... because this is the value you want
        // // to merge!
        // m.merge("a", null, (v1, v2) -> null);
        //
        // print("z" + m);

        // Null remapping function
        // Not permitted in HashMaps
        // m.merge("a", "a", null);

        /*
         - Exceptions
            - UnsupportedOperationException
                - Think immutable maps
            - NullPointerException
                - If key is null
                - and the collection does not support null keys
                - Or if the value or remapping function is null
            - ClassCastException
                - If you want to prevent some type being stored
            - IllegalArgumentException
                - If you want to prevent some value being stored
        */

        m.merge("a", "a", (v1, v2) -> m.put("x", "y"));
        // ConcurrentModificationException
        m.merge("a", "a", (v1, v2) -> m.put("x", "y"));
        // Why are you editing the map in the middle of editing it! Are you crazy?

    }

    static void print(String s) {
        System.out.println(s);
    }

    static void print(Map<?, ?> m) {
        m.entrySet(). forEach(System.out::println);
    }

}
