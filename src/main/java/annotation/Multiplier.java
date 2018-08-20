package annotation;

/**
 * 使用标注
 *
 * @author yafei.hou  on 2018/8/20
 */
@ExtractInterface("IMultiplier")
public class Multiplier {

    public int multiply(int x, int y) {
        int total = 0;
        for (int i = 0; i < x; i++) {
            total += y;
        }
        return total;
    }

    public static void main(String[] args){
        Multiplier multiplier = new Multiplier();
        System.out.println("11 * 16 = "+multiplier.multiply(11,16));

    }

}
