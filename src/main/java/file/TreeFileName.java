package file;

import java.io.File;

/**
 * 将文件名字按照树形结构输出
 *
 * @author yafei.hou  on 2018/3/13
 */
public class TreeFileName {

    public static void main(String[] args) {
        File file=new File("D:\\springmvc\\mybatisSpring\\src\\main");
        openFiles(file);
    }

    private static void openFiles(File file) {
        if (file.isDirectory()) {
            int size = file.getParentFile().getAbsolutePath().length();
            printSpace(size);
            System.out.println("└─" +file.getName()+File.separator);
            File[] files = file.listFiles();
            for (File fileName : files) {
                openFiles(fileName);
            }
        } else {
            printSpace(file.getParentFile().getAbsolutePath().length());
            System.out.println("└─" + file.getName());
        }
    }

    private static void printSpace(int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(" ");
        }
    }
}
