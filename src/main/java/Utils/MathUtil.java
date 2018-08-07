package Utils;

import java.math.BigDecimal;

/**
 * 计算
 *
 * @author wei.yang on 2018/5/24
 */
public class MathUtil {

    private static final int ZERO = 0;

    /**
     * 转化为格式化好的double数据
     *
     * @param value 数据
     * @return double数据
     */
    public static double toFormatDouble(double value, Scale scale) {
        return BigDecimal.valueOf(value).setScale(scale.val, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 转化为integer
     *
     * @param value 待转化的数据
     * @return 转化好的数据
     */
    public static int toFormatInteger(double value) {
        return BigDecimal.valueOf(value).setScale(ZERO, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 小数位
     */
    public enum Scale {
        /**
         * 保留一位
         */
        ONE(1),

        /**
         * 保留两位
         */
        TWO(2),

        /**
         * 保留三位
         */
        THREE(3);

        private int val;

        Scale(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }
}
