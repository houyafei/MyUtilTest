package number;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 随机数产生
 *
 * @author yafei.hou  on 2018/7/25
 */
public class RandomTest {

    public static void main(String[] args)  {


        Map<String,Integer> map1 = new HashMap<>();
        map1.put("test1",4);
        map1.put("test2",8);
        map1.put("test3",12);
        map1.put("test4",16);

        Map<String,Integer> result = new HashMap<>();
        result.put("test1",0);
        result.put("test2",0);
        result.put("test3",0);
        result.put("test4",0);
        for (int i = 0; i < 10000; i++) {
            String temp = selectOpenScreenByWeight(map1);
            result.put(temp,result.get(temp)+1);
        }
        System.out.println(result);
    }


    private static  String selectOpenScreenByWeight(Map<String,Integer> map1) {

        List<String> ids = Lists.newArrayList();

        for (Map.Entry<String,Integer> model:map1.entrySet()) {
            int weight = model.getValue();
            if (weight <= 0) {
                weight = 1;
            }
            if (weight > 20) {
                weight = 20;
            }
            for (int i = 0; i < weight; i++) {
                ids.add(model.getKey());
            }

        }
        String resourceId = ids.get((int) (Math.random() * ids.size()));
        return resourceId;
    }

}
