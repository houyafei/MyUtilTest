package splitString;


import com.google.common.base.Strings;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分割字符和数字组成测字符串
 *
 * @author yafei.hou  on 2018/3/28
 */
public class SplitString {

    private static Pattern pattern = Pattern.compile("[0-9]+");
    private static Pattern pattern2 = Pattern.compile("\\D+");

    public static void main(String[] args) {
//        String str = "QA1_1";
//        //System.out.println(getSplitStrings(str));
//        //System.out.println(getSplitStrings2(str));
//        //getSplitStrings2(str);
//        String s = "3.0.1.2";
//        System.out.println(s.substring(0,s.indexOf(".")));
//        //s.substring(0, s.indexOf(".",s.indexOf(".")+1 ));
//        System.out.println(s.substring(0, s.indexOf(".",s.indexOf(".")+1 )));
//        String city = "上海Shanghai——sdSH";
//        city = city.toLowerCase();
//        String sd = "sd";
//        System.out.println(city.contains(sd));
//        System.out.println(city);

//        String s1 = "4545878qw";
//        System.out.println(s1.matches("^[0-9]*$"));
        System.out.println(isOldVersion("5.3.093"));
    }

    private static List<String> getSplitStrings(String o1) {
        List<String> stringList = new ArrayList<>();
        char[] strChars1 = o1.toCharArray();
        StringBuilder stringBuilder1 = new StringBuilder();
        for (int i = 0; i < strChars1.length; i++) {
            int len = stringBuilder1.length();
            if (len == 0) {
                stringBuilder1.append(strChars1[i]);
            } else if (stringBuilder1.toString().matches("[0-9]+") ) {
                if (!(strChars1[i] < '0' || strChars1[i] > '9')) {
                    stringBuilder1.append(strChars1[i]);
                } else {
                    stringList.add(stringBuilder1.toString());
                    stringBuilder1.delete(0, len);
                    stringBuilder1.append(strChars1[i]);
                }
            } else {
                if ((strChars1[i] < '0' || strChars1[i] > '9')) {
                    stringBuilder1.append(strChars1[i]);
                } else {
                    stringList.add(stringBuilder1.toString());
                    stringBuilder1.delete(0, len);
                    stringBuilder1.append(strChars1[i]);
                }
            }
        }
        stringList.add(stringBuilder1.toString());
        stringBuilder1.delete(0, stringBuilder1.length());
        return stringList;
    }

    private static Collection<String> getSplitStrings2(String o1){
        List<String> stringList = new ArrayList<>();
        Matcher matcher = pattern.matcher(o1);
        while (matcher.find()){
            stringList.add(matcher.group());
        }
        Matcher m=pattern2.matcher(o1);
        while (m.find()){
            stringList.add(m.group());
        }
        Map<Integer,String> map = new TreeMap<>();
        for (int i = 0; i < stringList.size(); i++) {
            map.put(o1.indexOf(stringList.get(i)),stringList.get(i));
        }
        System.out.println(map.values());
        return  map.values();
    }


    private static boolean isOldVersion(String appVersion) {
        if (Strings.isNullOrEmpty(appVersion)) {
            return true;
        }
        String[] versionNum = appVersion.split("\\.");
        if (versionNum.length < 2) {
            return true;
        }
        try {

            if (Integer.valueOf(versionNum[0]) == 5 && Integer.valueOf(versionNum[1]) >= 3) {
                return false;
            }
            if (Integer.valueOf(versionNum[0]) > 5) {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
        return true;
    }

}
