package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Channel通道
 *
 * @author yafei.hou  on 2018/7/24
 */
public class ChannelTest {

    public static void main(String[] args) throws IOException {

        getChannelFromInputStream();

    }

    /**
     * 从输入输出流中获取通道
     *
     * @throws IOException io异常
     */
    private static void getChannelFromInputStream() throws IOException {
       // RandomAccessFile file = new RandomAccessFile("D:\\javaproject\\NumberFlow\\src\\main\\java\\cmd\\Cmd.java", "r");

        FileInputStream fileInputStream = new FileInputStream("D:\\javaproject\\NumberFlow\\src\\main\\java\\cmd\\Cmd.java");

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\md.txt");

        FileChannel tofile = fileOutputStream.getChannel();
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int readNum = fileChannel.read(buffer);
        byte[]  temp;
        while (readNum != -1) {
            buffer.flip();
            tofile.write(buffer);
            buffer.clear();
            readNum = fileChannel.read(buffer);
        }

        fileChannel.close();
        tofile.close();
    }

}
