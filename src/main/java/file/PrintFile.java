package file;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * 打印某个文件下所有的文件
 *
 * @author yafei.hou  on 2018/8/6
 */
public class PrintFile {


    public static void main(String[] args){
        Set<String> nameSets = new HashSet<>();
//        //URL url  =PrintFile.class.getClass().getClassLoader().getResource("/");
//        File file = new File(Object.class.getClass().getResource("/")+"file");
//        System.out.println(file.isDirectory());
        //System.out.println(url);
        getClassNames("file",nameSets);

        System.out.println(nameSets);
    }

    private static void getClassNames(String packageName, Set<String> classNames) {
        URL url = Object.class.getResource("/"+packageName.replace(".",File.separator));
        System.out.println(url);
        File baseFile = new File(url.getFile());
        for (File file:baseFile.listFiles()){
            if (file.isDirectory()) {
                getClassNames(packageName+"."+file.getName(),classNames);
            } else {
                classNames.add(packageName+"."+file.getName().replace(".class",""));
            }
        }

    }

}
