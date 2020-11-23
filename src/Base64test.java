import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;

/**
 * Created by ZXD on 2019/8/5.
 */
public class Base64test {
    private static final String TEST_STRING = "0123456789，0123456789，0123456789，0123456789，0123456789，0123456789，0123456789";
  /*  public static void main(String[] args) throws Exception {


        File file = new File("E:\\qqfile\\297139205\\FileRecv\\201908021505197.zip");
        InputStream in = new FileInputStream(file);
        byte[] buff = new byte[(int)file.length()];
        in.read(buff);
        in.close();
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String base64Result = base64Encoder.encode(buff);
        System.out.println(base64Result.replaceAll("\r\n",""));
        System.out.println("=========================================================================");
        System.out.println("=========================================================================");
        System.out.println("=========================================================================");
        System.out.println("=========================================================================");
        System.out.println("=========================================================================");

        String jdk18 =  Base64.getEncoder().encodeToString(buff);

        System.out.println(jdk18);

    }*/
  public static void main(String[] args) {
      int d = 60;
      System.out.println(Math.toRadians(d));
  }
}
