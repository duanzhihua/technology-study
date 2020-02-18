package cn.guxiangfly.nettystudy.netty.nio;

import java.nio.IntBuffer;

/**
 * @Author guxiang02
 * @Date 2020/1/23 19:28
 **/
public class BasicBuffer {

    public static void main(String[] args) {

        //举例说明Buffer 的使用 (简单说明)
        //创建一个Buffer, 大小为 5, 即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        intBuffer.put(1);
        intBuffer.put(2);
        intBuffer.put(3);
        intBuffer.put(4);
        intBuffer.put(5);

        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            int i = intBuffer.get();
            System.out.println(i);

        }
    }
}
