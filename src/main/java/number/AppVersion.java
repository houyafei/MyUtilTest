package number;

/**
 * 版本号比较
 *
 * @author yafei.hou  on 2018/7/19
 */
public class AppVersion {
    private static String str;

    public static void main(String[] args) {

        //System.out.println("和为: "+compareAppVersion("5.3.01","5.3"));


        //System.out.println(str);

        //stringequal();

        Integer num = 1;
        Integer num02 = null;
        System.out.println("num"+num);
        System.out.println("num"+num02);

    }

    private static void stringequal() {
        String testNew = new String("thisischina");
        String test = "thisischina";
        String str = "thisis";
        String str2 = "china";
        System.out.println(test == "thisis" + "china");
        System.out.println(test == str + str2);
        System.out.println(test == (str + str2).intern());
        System.out.println(testNew == test);
    }

    /**
     * APP版本号比较
     * 版本号1大于版本号2 ：返回 1
     * 版本号1小于版本号2 ：返回 -1
     * 版本号1等于版本号2 ：返回 0
     *
     * @param appVersion1 版本号1
     * @param appVersion2 版本号2
     * @return 版本号1 与版本号的大小比较
     */
    public static int compareAppVersion(String appVersion1, String appVersion2) {
        String[] version1 = appVersion1.split("\\.");
        String[] version2 = appVersion2.split("\\.");

        for (int i = 0; i < version1.length && i < version2.length; i++) {
            if (Integer.valueOf(version1[i]) > Integer.valueOf(version2[i])) {
                return 1;
            }
            if (Integer.valueOf(version1[i]) < Integer.valueOf(version2[i])) {
                return -1;
            }
        }
        return Integer.compare(version1.length, version2.length);
    }

}
