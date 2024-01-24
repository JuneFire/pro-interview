package main.java.test;

import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class IOTest {

    public static void listAllFiles(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }
        if (dir.isFile()) {
            System.out.println(dir.getName());
            return;
        }
        for (File file : dir.listFiles()) {
            listAllFiles(file);
        }
    }

    public static void copyFile(String src, String dist) throws IOException {
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dist);

        byte[] buffer = new byte[20 * 1024];
        int cnt;

        // read() 最多读取 buffer.length 个字节
        // 返回的是实际读取的个数
        // 返回 -1 的时候表示读到 eof，即文件尾
        while ((cnt = in.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, cnt);
        }

        in.close();
        out.close();
    }


    // 读取文件
    public static void readFileContent(String filePath) throws IOException {

        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        int i = 0;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(i++ + "  " + line);
        }

        // 装饰者模式使得 BufferedReader 组合了一个 Reader 对象
        // 在调用 BufferedReader 的 close() 方法时会去调用 Reader 的 close() 方法
        // 因此只要一个 close() 调用即可
        bufferedReader.close();
    }

    // 序列化
    private static class A implements Serializable {

        private int x;
        private String y;

        A(int x, String y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x = " + x + "  " + "y = " + y;
        }
    }

    // 从url中读取字节流数据
    public static void UrlInput(String args) throws IOException {

        URL url = new URL(args);

        /* 字节流 */
        InputStream is = url.openStream();

        /* 字符流 */
        InputStreamReader isr = new InputStreamReader(is, "utf-8");

        /* 提供缓存功能 */
        BufferedReader br = new BufferedReader(isr);

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }

    // 文件 NIO 实例
    public static void fastCopy(String src, String dist) throws IOException {

        /* 获得源文件的输入字节流 */
        FileInputStream fin = new FileInputStream(src);
        /* 获取输入字节流的文件通道 */
        FileChannel fcin = fin.getChannel();
        /* 获取目标文件的输出字节流 */
        FileOutputStream fout = new FileOutputStream(dist);
        /* 获取输出字节流的文件通道 */
        FileChannel fcout = fout.getChannel();
        /* 为缓冲区分配 1024 个字节 */
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {

            /* 从输入通道中读取数据到缓冲区中 */
            int r = fcin.read(buffer);
            /* read() 返回 -1 表示 EOF */
            if (r == -1) {
                break;
            }
            /* 切换读写 */
            buffer.flip();
            /* 把缓冲区的内容写入输出文件中 */
            fcout.write(buffer);
            /* 清空缓冲区 */
            buffer.clear();
        }
    }


    public static void getRespString() throws IOException {
        RandomAccessFile rf = new RandomAccessFile("zh.txt", "rw");
        FileChannel channel = rf.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(4); // 至少为4，因为UTF-8最大为4字节

        while (channel.read(buffer) != -1) {

            byte b;
            int idx;
            out:
            for (idx = buffer.position() - 1; idx >= 0; idx--) {
                b = buffer.get(idx);

                if ((b & 0xff) >> 7 == 0) {  // 0xxxxxxx
                    break;
                }
                if ((b & 0xff & 0xc0) == 0xc0) {   // 11xxxxxx，110xxxxx、1110xxxx、11110xxx
                    idx -= 1;
                    break;
                }
                if ((b & 0xff & 0x80) == 0x80) {
                    for (int i = 1; i < 4; i++) {
                        b = buffer.get(idx - i);
                        if ((b & 0xff & 0xc0) == 0xc0) {
                            if ((b & 0xff) >> (5 + 1 - i) == 0xf >> (3 - i)) {
                                break out;
                            } else {
                                idx = idx - 1 - i;
                                break out;
                            }
                        }
                    }
                }
            }


            buffer.flip();
            int limit = buffer.limit();
            buffer.limit(idx + 1);  // 阻止读取跨界数据
            System.out.println(Charset.forName("UTF-8").decode(buffer).toString());

            buffer.limit(limit);  // 恢复limit
            buffer.compact();
        }

        channel.close();
        rf.close();
    }


    private static String getRespString(String str) throws Exception {
        Charset charset = null;
        CharsetDecoder decoder = null;
        int capacity = 10;

        charset = Charset.forName("UTF-8");
        decoder = charset.newDecoder();

        String s = "客户端发送dsad德生科技电脑fdas上考虑迪士尼年少弗拉门发生ofjam打什么的即破发麦克 ‘；打， 饭哦按都";
        byte[] bytes = s.getBytes("UTF-8");

        //模拟接收的ByteBuffer   size 10
        ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
        //用于临时存放Bytebuffer转换后的字符
        CharBuffer charBuffer = CharBuffer.allocate(capacity);
        //用于连接展示字符串
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (true) {
            byteBuffer.put(bytes[i]);
            i++;
            if (byteBuffer.remaining() == 0 || i == bytes.length) {
                byteBuffer.flip();
                CoderResult coderResult;
                if (i != bytes.length) {
                    coderResult = decoder.decode(byteBuffer, charBuffer, false);
                } else {
                    coderResult = decoder.decode(byteBuffer, charBuffer, true);
                }
                //有错误
                if (coderResult.isError()) {
                    coderResult.throwException();
                }
                charBuffer.flip();
                sb.append(charBuffer);
                charBuffer.clear();
                byteBuffer.compact();
            }
            //退出循环
            if (i == bytes.length) {
                break;
            }
        }
        System.out.println(sb);
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
//        listAllFiles(new File(".."));
//        copyFile("D:\\PycharmProjects\\deep-learning-for-image-processing\\pytorch_object_detection\\yolov3_spp\\requirements.txt","requirements.txt");
//        String str1 = "中文";
//        byte[] bytes = str1.getBytes("UTF-8");
//        String str2 = new String(bytes, "UTF-8");
//        System.out.println(str2);

//        readFileContent("requirements.txt");

/*        A a1 = new A(123, "abc");
        String objectFile = "a1";

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(objectFile));
        objectOutputStream.writeObject(a1);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(objectFile));
        A a2 = (A) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(a2);*/

//        UrlInput("http://www.baidu.com");
//        fastCopy("D:\\WorkPlace1\\pro-interview\\interview-com.leetcode\\src\\com.leetcode\\Mind\\Sort.java", "Main.java.java.com.test.txt");

        getRespString("234");
    }
}
