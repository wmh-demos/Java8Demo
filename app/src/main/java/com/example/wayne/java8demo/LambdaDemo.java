package com.example.wayne.java8demo;

/**
 * Created by wayne on 2017/7/12.
 */

public class LambdaDemo {

    @FunctionalInterface
    interface OperatorTypeOne {
        int operate(int value);
    }

    interface OperatorTypeTwo {
        int operate(int lhs, int rhg);
    }

    public void showUsage() {
        //Java 7 写法
        executeTask(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World by Java 7!");
            }
        });

        executeTask(new OperatorTypeOne() {
            @Override
            public int operate(int value) {
                return value * 2;
            }
        });

        executeTask(new OperatorTypeTwo() {
            @Override
            public int operate(int lhs, int rhg) {
                return lhs * rhg;
            }
        });

        //Java 8 写法
        executeTask(() -> System.out.println("Hello World by Java 8"));

        executeTask(value -> value * 2);
        executeTask((int value) -> value * 2);
        executeTask((int value) -> {return value * 2;});

        executeTask((lhs, rhg) -> lhs * rhg);
        executeTask((int lhs, int rhg) -> lhs * rhg);
        executeTask((int lhs, int rhg) -> {return lhs * rhg;});
    }

    private void executeTask(Runnable task) {
        task.run();
    }

    private void executeTask(OperatorTypeOne operator) {
        System.out.println("operatorOne: " + operator.operate(10));
    }

    private void executeTask(OperatorTypeTwo operator) {
        System.out.println("operatorTwoInt: " + operator.operate(2, 5));
    }
}
