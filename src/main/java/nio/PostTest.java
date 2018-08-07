package nio;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 网络接口测试
 *
 * @author yafei.hou  on 2018/7/25
 */
public class PostTest {

    private static String url = "https://test.phicomm.com:3442/blood-pressure-meter/health/discovery/open/screen/get/v3";

    private static String content = "{\n" +
            "  \"accountToken\": \"string\",\n" +
            "  \"appVersion\": \"5.4\",\n" +
            "  \"osVersion\": \"android\",\n" +
            "  \"screenHeight\": 1330,\n" +
            "  \"screenWidth\": 750,\n" +
            "  \"types\": [\n" +
            "    \"-1\"\n" +
            "  ]\n" +
            "}";

    private static Map<String, String> headers = new HashMap<>();

    public static void main(String[] args) throws IOException {

        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < 2; i++) {
            String name = obtainResourceName();
            if (result.containsKey(name)){
                result.put(name,result.get(name)+1);
            }else {
                result.put(name,1);
            }
        }
        System.out.println(result);

    }

    private static String obtainResourceName() throws IOException {
        headers.put("phicomm-userId","8013");
        String result = post(url,content,headers);
        System.out.println(result);
        JSONObject object = JSON.parseObject(result);
        JSONObject data = object.getJSONObject("data");
        return data.getString("itemId");
    }

    /**
     * 发送http请求
     *
     * @param urlAddress url地址
     * @param content    正文
     * @param headers    header
     */
    public static String post(String urlAddress, String content, Map<String, String> headers) throws IOException {
        URL url = new URL(urlAddress);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setConnectTimeout(10000);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        for (String key : headers.keySet()) {
            urlConnection.setRequestProperty(key, headers.get(key));
        }
        urlConnection.connect();
        StringBuilder stringBuilder = new StringBuilder();
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            outputStream = urlConnection.getOutputStream();
            outputStream.write(content.getBytes());
            inputStream = urlConnection.getInputStream();
            byte[] data = new byte[1024];
            int len;
            while ((len = inputStream.read(data)) != -1) {
                stringBuilder.append(new String(Arrays.copyOfRange(data, 0, len)));
            }
        } catch (Exception e) {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return stringBuilder.toString();
    }
}
