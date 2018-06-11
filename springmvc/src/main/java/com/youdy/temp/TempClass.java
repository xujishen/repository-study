package com.youdy.temp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 此类要完成每0.1秒向文件输出1或者2. 直到十次结束.
 */
public class TempClass {
    static String tempFilePath = "E:\\Projects\\repository-study\\springmvc\\src\\main\\java\\com\\youdy\\temp\\temp.txt";
    static File file = new File(tempFilePath);
    static int loop = 5;
    static long st = 100;
    static AtomicBoolean flag = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException, IOException {
        CountDownLatch cdl = new CountDownLatch(2);
        clearFile();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                try {
                    while (true) {
                        if (!flag.get()) {
                            writeFile("1");
                            flag.compareAndSet(false, true);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            cdl.countDown();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                try {
                    while (true) {
                        if (flag.get()) {
                            writeFile("2");
                            flag.compareAndSet(true, false);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            cdl.countDown();
        });
        t1.start();
        t2.start();
        System.out.println("两线程开始工作...");
        cdl.await();
        System.out.println("两线程结束工作!!!");
    }

    static void writeFile(String content) {
        try (FileWriter fileWriter = new FileWriter(file, true);) {
            fileWriter.append(content);
            TimeUnit.MILLISECONDS.sleep(st);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void clearFile() {
        try (FileWriter fileWriter = new FileWriter(file, false);) {
            fileWriter.write("");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}