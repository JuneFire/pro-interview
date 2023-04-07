package test;

/**
 * @Author: zkcheng
 * @Date: 2022/05/04/4:21
 * @Description:
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        File dir = new File(".");

        String source = dir.getCanonicalPath() + File.separator + "Code.txt";
        String dest = dir.getCanonicalPath() + File.separator + "Dest.txt";

        File fin = new File(source);
        FileInputStream fis = new FileInputStream(fin);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));

        FileWriter fstream = new FileWriter(dest, true);
        BufferedWriter out = new BufferedWriter(fstream);

        String aLine = null;
        while ((aLine = in.readLine()) != null) {
            //处理每一行，并添加到输出文件Dest.txt
            out.write(aLine);
            out.newLine();
        }

        //不要忘记关闭缓冲读者
        in.close();

        //关闭缓冲区写程序
        out.close();
    }
}
