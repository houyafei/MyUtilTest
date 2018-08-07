package number;

import java.util.Arrays;

/**
 * Arrays.sort()测试针对基本类型和其他类型排序方式
 *
 * @author yafei.hou  on 2018/8/3
 */
public class SortTest {

    public static void main(String[] args){
        int[]  intArray = new int[]{1,4,5,76,8,34,71,31,135,62,7624};
        Arrays.sort(intArray);
        System.out.println(Arrays.toString(intArray));
    }

}
