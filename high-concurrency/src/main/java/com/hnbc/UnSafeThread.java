package com.hnbc;

import java.util.concurrent.CountDownLatch;

public class UnSafeThread {

    private static int num = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void inCreate() {
        num += 1;
    }

	//添加注释测试git，再次测试
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    inCreate();
                    /*try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
                countDownLatch.countDown();
            }).start();
        }

        while (true) {
            if (countDownLatch.getCount() == 0) {
                System.out.println(num);
                break;
            }
        }
    }

    /*public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    inCreate();
                }
            }).start();
        }

        System.out.println(num);
    }*/

    /*public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 100; j++) {
                inCreate();
            }
        }

        System.out.println(num);
    }*/
}
