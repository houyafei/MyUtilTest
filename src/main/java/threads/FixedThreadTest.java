package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * 线程池中的线程数量确定，超出部分则等待
 *
 * 定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
 *
 * @author yafei.hou  on 2018/2/28
 */
public class FixedThreadTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("index = " + index+","+this.getClass());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

    }

}
