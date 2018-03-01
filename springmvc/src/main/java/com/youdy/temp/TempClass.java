package com.youdy.temp;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TempClass {
    static String tempFilePath = "E:\\Projects\\repository-study\\springmvc\\src\\main\\java\\com\\youdy\\temp\\temp.txt";
    static File file = new File(tempFilePath);
    static int loop = 5;
    static long st = 100;
    static FileWriter fileWriter;

    static {
        // file.clear();
        try {
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static AtomicBoolean flag = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException, IOException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                try {
                    while (true) {
                        if (!flag.get()) {
                            fileWriter.append("1");
                            TimeUnit.MILLISECONDS.sleep(st);
                            fileWriter.flush();
                            flag.compareAndSet(false, true);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < loop; i ++) {
                try {
                    while (true) {
                        if (flag.get()) {
                            fileWriter.append("2");
                            TimeUnit.MILLISECONDS.sleep(st);
                            fileWriter.flush();
                            flag.compareAndSet(true, false);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(5L);
        fileWriter.close();
    }
}