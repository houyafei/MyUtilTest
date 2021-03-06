package splitString;

import java.util.HashMap;
import java.util.Map;

/**
 * 计算数据的和
 *
 * @author yafei.hou  on 2018/6/2
 */
public class SplitSum {

    public static void main(String[] args){


        String[] strNum = {
                "8336, 3427, 1528, 1340, 3650, 15980, 42083, 51552, 35991, 27943, 25701, 24279, 22435, 19515, 19626, 19984, 20693, 21871, 24030, 25902, 31358, 36942, 34304, 11801",
            "2273, 895, 447, 441, 1285, 5747, 13928, 15306, 10792, 8347, 7582, 7483, 6994, 6056, 6169, 6247, 6536, 6716, 7221, 7782, 9150, 10641, 9889, 3534",
                "6055, 2448, 1023, 852, 2576, 12122, 33952, 41547, 26984, 20353, 18951, 17386, 16130, 13679, 13486, 14125, 14474, 15301, 17819, 19260, 23955, 29126, 27114, 9294",
                "1534, 554, 271, 255, 850, 4295, 9995, 10626, 6757, 4874, 4517, 4176, 4182, 3667, 3622, 3980,3980, 4197, 4620, 4856, 5928, 7176, 6840, 4081",
                "2311, 1054, 508, 406, 1069, 3736, 7748, 9321, 8095, 6616, 6360, 6074, 5983, 5735, 5638, 5797, 6070, 6123, 6397, 6391, 6910, 7178, 6889, 4473",
                "954, 451, 232, 187, 447, 1579, 3302, 4050, 3487, 2732, 2560, 2522, 2465, 2288, 2333, 2528,  2408, 2512, 2531, 2589, 2701, 2827, 2798, 1844"
        };
        String[] strName = {
                "all-pv",
                "all-uv",
                "balance-pv",
                "balance-uv",
                "sp-pv",
                "sp-uv"
        };
        Map<String,Integer> result = new HashMap<>(12);
        for (int i = 0; i < strNum.length; i++) {
            String[] nums = strNum[i].split(",");
            int sum = 0 ;
            for (String num : nums) {
                sum  += Integer.valueOf(num.trim());
            }
            System.out.println(sum);
            result.put(strName[i],sum);
        }


        System.out.println(result);

    }

}
