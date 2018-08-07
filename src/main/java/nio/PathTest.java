package nio;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * Path使用
 *
 * @author yafei.hou  on 2018/7/24
 */
public class PathTest {

    public static void main(String[] args) throws IOException {
        channel2Channel();
    }

    /**
     * 从一个通道读取数据到另外一个通道
     *
     * @throws IOException io异常
     */
    private static void channel2Channel() throws IOException {
        Path path = Paths.get("D:\\javaproject\\NumberFlow\\src\\main\\java\\cmd\\Cmd.java");
        Path path1 = Paths.get("D:\\cmd.txt");

        FileChannel fileChannel = FileChannel.open(path, READ);
        FileChannel tofile = FileChannel.open(path1, WRITE);

        ByteBuffer buffer = ByteBuffer.allocate(2018);
        int num = fileChannel.read(buffer);

        while (num > 0) {
            //将buffer转为可读模式
            buffer.flip();
            //将buffer写到FileChannel中
            tofile.write(buffer);
            //整理下缓冲中的数据
            buffer.compact();
            //再次读取数据到buffer中
            num = fileChannel.read(buffer);
        }

        fileChannel.close();
        tofile.close();
    }

}
