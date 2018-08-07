package java8;

/**
 * lambda表达式使用
 *
 * @author yafei.hou  on 2018/7/4
 */
public class LambdaTeat {

    public static void main(String[] args) {
        int sumAdd = operation(3, 7, (a1, b1) -> a1 + b1);
        int min = operation(10, 3, (a, b) -> a - b);
        int plus = operation(10, 9, (a, b) -> a * b);
        int div = operation(10, 5, (a, b) -> a / b);
        System.out.println(sumAdd);
        System.out.println(min);
        System.out.println(plus);
        System.out.println(div);
    }

    public interface MathOperationInteface {
        int operation(int a, int b);
    }

    public static int operation(int a, int b, MathOperationInteface math) {
        return math.operation(a, b);
    }
}
