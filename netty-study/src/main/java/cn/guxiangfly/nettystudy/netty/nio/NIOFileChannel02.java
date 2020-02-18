package cn.guxiangfly.nettystudy.netty.nio;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author guxiang02
 * @Date 2020/1/28 10:08
 **/
public class NIOFileChannel02 {
    public static void main(String[] args) throws Exception {

        File file = new File("netty-study/src/main/resources/file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());

        //将通道数据读入buffer
        fileChannel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
