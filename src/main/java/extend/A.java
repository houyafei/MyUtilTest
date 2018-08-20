package extend;

/**
 * 用于测试继承关系的类
 *
 * 父类中如果没有默认的构造方法，子类必须使用调用super（）方法初始化父类
 *
 * @author yafei.hou  on 2018/8/13
 */
public class A {

    private String name;

    public A(String name) {
        this.name = name;
        print();
    }

    public void print(){
        System.out.println("this is A");
    }
}
