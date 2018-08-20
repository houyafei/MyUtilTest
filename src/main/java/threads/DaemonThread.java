package threads;

/**
 * 后台线程中有finally，当程序退出时，后台线程结束，finally内的部分就不再执行了
 *
 *
 * @author yafei.hou  on 2018/8/14
 */
public class DaemonThread {

    static class Daem implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("is ok");
            }
        }
    }


    public static void main(String[] args){
        Thread thread = new Thread(new Daem());
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
