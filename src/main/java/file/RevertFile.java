package file;

import java.io.*;
import java.util.LinkedList;

/**
 * 将文件读出来后，逆序输出
 *
 * @author yafei.hou  on 2018/3/13
 */
public class RevertFile {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new FileReader(new File("E:\\我的笔记\\健康数据统计sql")));
        LinkedList<String> list= new LinkedList<>();
        BufferedWriter bfout = new BufferedWriter(new FileWriter(new File("逆序.txt"),true));
        BufferedWriter bfout2 = new BufferedWriter(new FileWriter(new File("正序.txt"),true));
        StringBuilder sb = new StringBuilder();
        String s = "";
        int count=1;
        while ((s=bf.readLine())!=null){
            System.out.print(count+"\t");
            System.out.println(s);
            list.addFirst(s);
            bfout2.write(count+"\t");
            bfout2.write(s);
            bfout2.newLine();
            count++;
        }
        for (int i=0;i<list.size();i++) {
            bfout.write(i+"\t");
            bfout.write(list.get(i));
            bfout.newLine();
        }
        bfout.flush();
        bfout2.flush();
        bfout.close();
        bfout2.close();
        bf.close();
    }

}
