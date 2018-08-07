package threads;

/**
 * 线程处理方法二
 *
 * @author yafei.hou  on 2018/7/31
 */
public class CurrentTest2 {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        char[] cs = {'a', 'b', 'c', 'd', 'e'};
    }

    static  void canPrintNum(boolean printCsOver){
        synchronized (lock){
            if (printCsOver){
                lock.notify();
            }else {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
