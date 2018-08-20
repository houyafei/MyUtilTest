package extend;

/**
 * 继承关系
 *
 * @author yafei.hou  on 2018/8/13
 */
public class B extends A {

    private String person ;

    public B(String name) {
        super(name);
        this.person = "hyf ";
        print();
    }

    @Override
    public void print() {
        System.out.println("ok : "+person);
    }

    public static void main(String[] args){
        new B("zhou");
    }
}
