package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程任务执行
 *
 * @author yafei.hou  on 2018/2/28
 */
public class SingleThreadTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+"--"+index);
                }
            });
        }
    }
}
