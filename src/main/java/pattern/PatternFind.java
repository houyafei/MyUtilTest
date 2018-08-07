package pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * 模式匹配题
 *
 * @author yafei.hou  on 2018/6/2
 */
public class PatternFind {

    public static void main(String[] args) {

        String template = " {\"thirdparty_id\":\"weight_target_stage\"," +
                "\"payload\":{\"aps\":{\"alert\":{\"body\":\"只剩%d天了，距离目标体脂还有%s %%.\"}," +
                "\"process\":\"%s\",\"push_ways\":\"%s\",\"resourceId\":\"bfr\",\"type\":\"body_target\"," +
                "\"userId\":\"%s\",\"memberId\":\"%s\"}},\"description\":\"体脂变化超过2%%\",\"appkey\":\"%s\"," +
                "\"type\":\"listcast\",\"production_mode\":\"%s\"," +
                "\"timestamp\":\"%s\",\"device_tokens\":\"%s\"}";

        String result= String.format(template, 12, 2, 2, 0, 22, 2, 22, 22, 22, 2);
        System.out.println(result);
    }

    private static boolean march(String pattern, String str) {
        char[] cs = pattern.toCharArray();
        String[] strArray = str.split("\\s+");
        if (cs.length != strArray.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>(cs.length);
        for (int i = 0; i < cs.length; i++) {
            if (map.containsKey(cs[i])) {
                if (!map.get(cs[i]).equals(strArray[i])) {
                    return false;
                }
            } else {
                map.put(cs[i], strArray[i]);
            }
        }
        return true;
    }

}
