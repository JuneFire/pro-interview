import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zkCheng
 * @date 2022/1/26 10:20
 * https://blog.csdn.net/w372426096/article/details/82661866
 */
public class ReflectCase {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Proxy proxy = new Proxy();
        Method method = Proxy.class.getDeclaredMethod("run");
        method.invoke(proxy);
    }

    static class Proxy{
       public void run(){
            System.out.println("run");
        }
    }
}
