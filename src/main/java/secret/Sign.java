package secret;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * 用于签名的算法
 *
 * @author yafei.hou  on 2018/6/5
 */
public class Sign {


    public static void main(String[] args) {

        User user = new User("name", "password001", "eeee",null,"male");

       // System.out.println(JSON.toJSONString(user));
        String str = "{\"gender\":1,\"electrodeNumber\":4,\"sign\":\"4C8C860C95A3A1DEC9C6145D1A98D017EFE9127B\",\"weight\":1000,\"multiFrequency\":true,\"rawData\":\"000000000000000000000000\",\"type\":\"s9\",\"mac\":\"c8:b2:1e:70:ab:ba\",\"heartRate\":0,\"createTime\":\"2018-06-07 19:13:10\",\"appId\":\"211527918605862\",\"age\":24,\"height\":170,\"timestamp\":1528369990000}";
        System.out.println(str);
        try {
            System.out.println(sortWithSign(JSON.parseObject(str), "9370253f6fd9678f21152c652dde7a91"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String sortWithSign(JSONObject params, String secret) throws NoSuchAlgorithmException {
        Map<String, Object> paramsMap = JSONObject.toJavaObject(params, Map.class);
        String strParams = paramsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .filter((Map.Entry<String, Object> o) -> {
                    return o.getValue() != null && !"sign".equals(o.getKey());
                })
                .reduce("", (String s, Map.Entry<String, Object> o) -> {
                    return s + o.getKey() + o.getValue();
                }, (String s, String s2) -> {
                    return s;
                });
        return sign(strParams, secret);
    }

    /**
     * 签名(SHA1)
     *
     * @param param 参数
     * @return 加密后的数据
     * @throws NoSuchAlgorithmException 加密不存在
     */
    public static String sign(String param, String secret) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA1");
        String message = param + secret;
        byte[] digestMsg = digest.digest(message.getBytes());
        return toHex(digestMsg);
    }

    /**
     * 转为16进制
     *
     * @param input 输入
     * @return 输出
     */
    private static String toHex(byte[] input) {
        StringBuilder builder = new StringBuilder();
        String temp;
        for (int val : input) {
            temp = Integer.toHexString(val & 0xff);
            if (temp.length() == 1) {
                builder.append("0");
            }
            builder.append(temp);
        }
        return builder.toString().toUpperCase();
    }

}

class User {
    private String username;
    private String password;
    private String sign;
    private String empty;

    private String gender;

    public User(String username, String password, String sign, String empty,String gender) {
        this.username = username;
        this.password = password;
        this.sign = sign;
        this.empty = null;
        this.gender=gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sign='" + sign + '\'' +
                ", empty='" + empty + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}