package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * json测试
 *
 * @author yafei.hou  on 2018/7/27
 */
public class JSONTest {

    public static void main(String[] args ){
        //list2json();
        map2json();
    }

    private static void map2json(){
        HashMap<String,UserTest> map = Maps.newHashMap();
        List<String> books = new ArrayList<>();
        books.add("java");
        books.add("C++");
        books.add("phiCare");
        map.put("a",new UserTest("xinxiang",books));
        //map.put("key",null);
        map.put("b",new UserTest("yafei",books));
        System.out.println("map:");
        System.out.println(map);
        System.out.println("map 2 json");
        System.out.println(JSON.toJSONString(map));
        System.out.println("json 2 map");

        HashMap<String,JSONObject> mapJson = JSONObject.parseObject(JSON.toJSONString(map),HashMap.class);

        mapJson.forEach((k,v)->{
            UserTest userTest = JSONObject.parseObject(v.toJSONString(),UserTest.class);
            System.out.println(userTest.getBooks());
        });

        System.out.println(mapJson);
    }

    private static void list2json() {
        List<String> books = new ArrayList<>();
        books.add("java");
        books.add("C++");
        books.add("phiCare");
        List<UserTest> list = new ArrayList<>();
        list.add(new UserTest("xinxiang",books));
        list.add(new UserTest("yafei",books));
        String jsonStr = JSON.toJSONString(list);
        System.out.println(jsonStr);
        List<UserTest> temp = JSONObject.parseArray(jsonStr,UserTest.class);
        temp.forEach((m)->{
            System.out.println(m.getBooks());
        });

    }
}


class UserTest{
    private String name;
    private List<String> books;

    public UserTest(String name, List<String> books) {
        this.name = name;
        this.books = books;
    }

    public UserTest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "UserTest{" +
                "name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
