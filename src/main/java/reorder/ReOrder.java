package reorder;

/**
 * 多线程中出现指令重排序问题
 *
 * @author yafei.hou  on 2018/6/11
 */
public class ReOrder {

        private static boolean flag;
        private static int num;

        public static void main(String[] args) {
            //reOrder();
            int[] x = new int[25];
            System.out.println(x[24]);
        }

    private static void reOrder() {
        Thread t1 = new Thread(() -> {
            while (!flag) {
                Thread.yield();
            }
            System.out.println(num);
            System.out.println(num);
        }, "t1");
        t1.start();
        num = 5;
        flag = true;
    }


}
