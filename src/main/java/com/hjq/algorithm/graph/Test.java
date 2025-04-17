package com.hjq.algorithm.graph;

import java.util.Stack;

public class Test {
    private int i;

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }


    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static Integer f2(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {

            Integer last = f2(stack);

            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>() ;
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);

        f2(test);

        int i = 0;
    }


}
