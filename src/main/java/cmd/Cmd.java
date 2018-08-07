package cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * java执行文件复制到远程机器上的命令
 *
 * @author yafei.hou  on 2018/4/10
 */
public class Cmd {

    public static void main(String[] args) {
        executeLinuxCmd2("java");
    }

    public List<String> executeLinuxCmd(String cmd) {
        System.out.println("got cmd job : " + cmd);
        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec(new String[]{"/bin/sh", "-c", cmd});
            InputStream in = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
            List<String> list = new ArrayList<String>();
            String result = null;
            while ((result = bs.readLine()) != null) {
                System.out.println("job result [" + result + "]");
                list.add(result);
            }
            in.close();
            process.destroy();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String executeLinuxCmd2(String cmd) {
        System.out.println("got cmd job : " + cmd);
        Runtime run = Runtime.getRuntime();
        String line = null;
        StringBuilder out = new StringBuilder();
        try {
            Process process = run.exec(cmd);
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                out.append(line + "\n");
            }
            System.out.println("job result [" + out.toString() + "]");
            bufferedReader.close();
            process.destroy();
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
