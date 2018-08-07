package nio;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * netty使用小栗子
 *
 * @author yafei.hou  on 2018/7/25
 */
public class NettyTest {

    public static void main(String[] args) throws IOException {
        //打开服务端channel
        ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();
        //设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //绑定一个本地端口
        serverSocketChannel.bind(new InetSocketAddress(9999));

        //打开selector
        Selector selector = Selector.open();
        //为通道注册事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        byte[] result ;
        while (true){
           int readChannel = selector.select();
            if (readChannel <0) {
                continue;
            }
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterable = keys.iterator();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (iterable.hasNext()){
                SelectionKey key = iterable.next();
                if (key.isAcceptable()){

                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println(socketChannel==null);
                    while (socketChannel==null){
                        socketChannel = serverSocketChannel.accept();
                    }

                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.read(buffer);
                    buffer.flip();
                    result = new byte[buffer.remaining()];
                    buffer.get(result);
                    System.out.println(new String(new String(result).getBytes(StandardCharsets.UTF_8)));
                    socketChannel.write(buffer);
                    buffer.compact();
                }
                iterable.remove();
            }
        }
    }



}
