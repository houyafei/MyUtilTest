package NumberFlow;

import org.fit.cssbox.demo.ImageRenderer;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yafei.hou
 * Created by yafei.hou on 2017/12/25.
 *
 * 将网页转换为图片
 *
 */
public class CssBox4me {


    public static void main(String[] args) throws Exception {
        cssboxImage();
    }

    private static void cssboxImage() throws IOException, SAXException {
        ImageRenderer render = new ImageRenderer();
        System.out.println("kaishi");
        String url = "https://test.phicomm.com/product-manger/bpm/statistic/measure";
        FileOutputStream out = new FileOutputStream(new File("D:"+ File.separator+"html.png"));
        render.renderURL(url, out, ImageRenderer.Type.PNG);
        System.out.println("OK");
    }




}
