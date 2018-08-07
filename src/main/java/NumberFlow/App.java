package NumberFlow;


import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger logger = Logger.getLogger(App.class);

    private static char[] cs = {' ','0','1',' ',};

    public static void main( String[] args )
    {
        randomNumber(args);

    }

    /**
     * 随机生成任意 数量的0和1
     *
     * @param args 数量参数
     */
    private static void randomNumber(String[] args) {
        if (args.length!=1){
            System.out.println("请输入循环次数：");
            return;
        }
        int count = Integer.parseInt(args[0]);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {

            for (int j = 0; j < 500; j++) {
                stringBuilder.append(cs[(int) (Math.random()*100)%4]);
            }

            logger.info(stringBuilder.toString());
            stringBuilder.delete(0,500);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
