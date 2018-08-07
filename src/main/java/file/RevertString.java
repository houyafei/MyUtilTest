package file;

/**
 * 翻转字符串
 *
 * @author yafei.hou  on 2018/6/22
 */
public class RevertString {


    public static void main(String[] args) {

        long srcNum = -12345670;

        long desNum = revertDesNum(srcNum);
        System.out.println(desNum);


    }

    private static long revertDesNum(long srcNum) {
        boolean isPositiveNum = true;
        long temp = srcNum;
        //判断正负
        if (srcNum < 0) {
            temp = Math.abs(srcNum);
            isPositiveNum = false;
        }
        //个位数
        if (temp < 10) {
            return srcNum;
        }

        //翻转数据
        String src = String.valueOf(temp);
        StringBuilder stringBuilder = new StringBuilder();

        char[] cs = src.toCharArray();
        for (int i = cs.length - 1; i >= 0; i--) {
            stringBuilder.append(cs[i]);
        }

        long result = Long.parseLong(stringBuilder.toString());

        return isPositiveNum ? result : -1 * result;
    }


}
