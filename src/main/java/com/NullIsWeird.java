package com;

import java.util.ArrayList;
import java.util.List;

import static com.Utils.print;

/*
 - 10 Weird Facts About Null
*/
public class NullIsWeird {

    public static void main(String[] args) {
        new NullIsWeird()._10();
    }

    /*
     - Null has a special type... but is not an Object!
    */
    void _1() {
         // System.out.println(null.toString());
    }

    /*
     - Different types look equal == if they are null
    */
    void _2() {
        List<?> l = null;
        ArrayList<?> i = null;
        System.out.println(l == i);
    }

    /*
     - == is true!
    */
    void _3() {
        print(null == null);
    }

    /*
     - Null is not an instanceof anything
    */
    void _4() {
         String s = null;
         print(s instanceof String);
    }

    /*
     - null is not even an instanceof null!
    */
    void _5() {
         // print(null instanceof null);
    }

    /*
     - Casting a null does nothing
    */
    void _6() {
        String sx = (String) null;
        print(sx + "Example");
        print(sx.toUpperCase()); // Actually still null
    }

    /*
     - You cannot cast to null
    */
    void _7() {
        String s = "";
        // String b = (null)s;
    }

    /*
     - You can call a method on a null reference if its static!
    */
    void _8() {
        Statik a = null;
        a.str();
    }

    /*
     - Unboxing a null causes an NPE...not just calling members on an null
    */
    void _9() {
        Integer i = null;
        int j = i; // NPE!
    }

    /*
     - You cannot synchronize with a null
    */
    void _10() {
         // synchronized(null) { }
    }














    // ---- Plumbing ----

    static class Statik {

        static void str() {
            print("hello");
        }

    }

}
