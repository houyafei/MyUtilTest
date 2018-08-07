package threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发相关的操作,
 * <p>
 * 问：已知数组 A 内容为1、2、3、4...52，数组 B 内容为 26 个英文字母，
 * 使用两个线程分别输入两个数组，然后使程序运行打印内容为 12a34b56c78e... 的规律，
 * 请给出代码实现？
 *
 * 还可以使用 LockSupport
 *
 * @author yafei.hou  on 2018/7/31
 */
public class ConcurrentTest {


    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        char[] cs = {'a', 'b', 'c', 'd', 'e'};
        Lock canPrint = new ReentrantLock();
        Condition printNUm = canPrint.newCondition();
        Condition printCs = canPrint.newCondition();
        new PrintNum(num, canPrint, printNUm, printCs).start();
        new PrintChar(cs, canPrint, printNUm, printCs).start();
    }

    static class PrintNum extends Thread {

        private int count = 0;

        private int[] num;

        private Condition printNUm, printCs;

        private Lock canPrint;

        PrintNum(int[] num, Lock canPrint, Condition printNUm, Condition printCs) {
            this.num = num;
            this.canPrint = canPrint;
            this.printCs = printCs;
            this.printNUm = printNUm;
        }

        @Override
        public void run() {
            try {
                while (count % 2 == 0 && count < num.length) {

                    System.out.print(num[count++]);
                    System.out.print(num[count++]);
                    canPrint.lock();
                    printCs.signal();
                    printNUm.await();
                    canPrint.unlock();

                }
                canPrint.lock();
                printCs.signal();
                canPrint.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class PrintChar extends Thread {

        private int count = 0;

        private char[] cs;

        private Condition printNUm, printCs;

        private Lock canPrint;

        PrintChar(char[] cs, Lock canPrint, Condition printNUm, Condition printCs) {
            this.cs = cs;
            this.canPrint = canPrint;
            this.printCs = printCs;
            this.printNUm = printNUm;
        }

        @Override
        public void run() {
            try {
                while (count < cs.length) {
                    System.out.print(cs[count++]);
                    canPrint.lock();
                    printNUm.signal();
                    printCs.await();
                    canPrint.unlock();
                }
                canPrint.lock();
                printNUm.signal();
                canPrint.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
