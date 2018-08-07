package number;

import Utils.MathUtil;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 数字类型转换
 *
 * @author yafei.hou  on 2018/6/12
 */
public class Double2Int {

    public static void main(String[] args) {
//        double targetRate = 100.0 * (200 - 1000) / (100 - 1000);
//        System.out.println((int) targetRate);



        float currentV = (float) BigDecimal.valueOf(99969 * 0.0001).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        float targetV = (float) BigDecimal.valueOf(100000 * 0.0001).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        float rangeV = (float) (30000*0.0001);
        float result = 1 - 1.0f * (targetV - currentV) / rangeV;
        result = (float) MathUtil.toFormatDouble(result, MathUtil.Scale.THREE);
        System.out.println(result);

        System.out.println(String.format("%.1f", 100169/10000.0));
        System.out.println(String.format("%.1f", 100500/10000.0));
        System.out.println(String.format("%.1f", 100600/10000.0));

        byte[] msgBody = new byte[16];
        System.out.println(Arrays.toString(msgBody));


        int cout = 1;

        while (true){
            System.out.print(cout++);
            System.out.print(" ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }



}
