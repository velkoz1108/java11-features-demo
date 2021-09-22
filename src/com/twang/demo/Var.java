package com.twang.demo;

import java.util.Comparator;
import java.util.function.IntBinaryOperator;

/**
 * Local Variable Type Inference
 */
public class Var {
//    var d = new Object();  // error

    public static void main(String[] args) {
        var  a = 1;

        var b = "string";

        var c = 'c';
        var length = b.length();
        System.out.println("length = " + length);


        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);


        IntBinaryOperator op = (var v1, var v2) -> v1 + v2;
        int i = op.applyAsInt(1, 4);
        System.out.println("i = " + i);

    }
}
