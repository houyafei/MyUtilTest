package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 带有缓存的线程池,
 *
 * 当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
 *
 * @author yafei.hou  on 2018/2/28
 */
public class CachedThreadTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;

            try {
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    System.out.println("index = " + index+","+this.getClass());
                }
            });
        }

    }

}
