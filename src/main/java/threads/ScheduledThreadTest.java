package threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 延时和定时执行的线程
 * <p>
 * 延时3秒开始执行线程任务
 *
 * @author yafei.hou  on 2018/2/28
 */
public class ScheduledThreadTest {

    public static void main(String[] args) {

        //scheduleTest();

        //scheduleTest2();
    }

    private static void scheduleTest2() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("this is the ScheduledExecutorService %d，%s",
                            index, Thread.currentThread().getName()));

                }
            },1,3, TimeUnit.SECONDS);
        }
    }

    /**
     * 延时执行的线程
     */
    private static void scheduleTest() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            scheduledThreadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("this is the ScheduledExecutorService %d，%s",
                            index, Thread.currentThread().getName()));
                }
            }, 3, TimeUnit.SECONDS);
        }
    }

}
