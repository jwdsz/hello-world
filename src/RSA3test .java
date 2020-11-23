import org.apache.tomcat.util.codec.binary.StringUtils;

import java.io.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * Created by ZXD on 2019/8/6.
 */
public class RSA2test {
    public static final String private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCov5hIg25SAec3/Lp4yGzHiW3ZCE57IkJDGQ3Ux5kDFzLoRpR/WBkNNuCmBISDjYYIG67kt0zmPfNllIyA2zAAPm6qsyL9+peC+kbWWdnAuElrZtGgOxoe5fucLq+eNX1UCJJNXyaPawmwll5IHAmHSyvck9KQ95m/oEoCCvCOJfpWLSuoEY9qJuRcnTAD7l2iqAEhDJ12SJ47yhEB5YRz17HOsuZp35fAAr1h8ak7EUzx61qfBW7LUTF4hf2kewQGCaVx/GUuh1zxu4WRbtUx2vJpq8+DC3m2UU/ggWF/lGflyU9xp8sVV6zDLPF8NzAypVNPmU6JvGtT6/g/LUkRAgMBAAECggEBAJmeWbZhl7OmMeSA+QxtgMGhdVOnQTLNPD8y9qlmzv/GRby4TAUloSCrfZUE9dGAq6yD7xIdYC8V5JbrxYCkiS0fqMVS8J7/WqCM0KHgB+Buo7/XSSwxwZSxTv2ebpd7IE24w98VqNfU3GxaZxgEFhSJNTPHimAYV2ngNJ9pcBYmOAlsp2718ReQO5Cw+0eyT5GaRRHbCsfqVvmXVVWWCjGhMK1F5I33X8iuauVBIJ9b109IPFnepdWPKY5R4z24W0uL0HUUnWFlQQxdYwBN0y5LK5xh5kUxDCaGF2/Q+MmrzXsmiW5IKcP1SY4h0lCAZUU/JV607ToBtjvWf/Y7sEECgYEA1gYIdX3aW7sJMdEwdhEkz9jYEONW1x0Ad4sp0gfyb9VJNmwz3t+L9DgrUe58OZg8rvO/uhk3C2DVYcz6d/vvcKp/ilJy0PV9jd/linDyiKGa24VEbrJYTbBXhYTLg5ozHOb4o/QO5jShyV6vBS4Zhr37pKLCVHDjA7zlJ1cSANkCgYEAydhUY3R49tLq//HDzmREOKToJi+TSB+MEFfasnU+MaGyJHUe4AwiuTZdZHDqBC9u311FlZbH8u7iTJJ3jvISWmEF1ZbluWzJy4+0HHooYTGUF83FeIN3aB12kOYov7/mlLI9TOTcGAMUmWyNikGm6pAv7ex+f6f+iGm90wB0ZvkCgYEAr0axlsdDFOzQUuQm9AqGQ1IrYWlNrU4rG7v0u/WWLtBV7SQgNt6NF60IjUjqo6Bx0jX9RsNvuPnMxxO/7JN/PJ3lZbqwHUFvWcmLix2FAzCWSkGSEbYtazKH+ukOYvDd+0WNS3iM2ysvdGL+oxdmAFuGwwYocE8jG+SPJvC/5cECgYEAiHvyXCnW3bezVgwJxhbAlrjTjXagFaXXVPnDFOb4fiEDT2muzfsGw8yCDDfZ1aipRPpR4hvJ6ngjp3lmxM7lIB35DvtxLRflv/LU7Gihw079/7aRb7Md29YIZWRsa3R8m1GG5+bc+JOkqzXe430PPA7xieVfiDx1fZJa3EGzdCkCgYBQt0PW0u4wojnKwt1hkdigAzw+XQEBj0tGXPLRviiV1Xxtb32s4b/lCxCeUkVcbGtivyFb2JKU09Vay70xYNCWwBHUObtVx4CFHyqQ9rKK5xlgTl0o/MZ0m3moF4n/VfME4JjztHArVKL6lUjX/LT/sqGowebToeAnJ5G9yVwcVg==";

    public static final String public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqL+YSINuUgHnN/y6eMhsx4lt2QhOeyJCQxkN1MeZAxcy6EaUf1gZDTbgpgSEg42GCBuu5LdM5j3zZZSMgNswAD5uqrMi/fqXgvpG1lnZwLhJa2bRoDsaHuX7nC6vnjV9VAiSTV8mj2sJsJZeSBwJh0sr3JPSkPeZv6BKAgrwjiX6Vi0rqBGPaibkXJ0wA+5doqgBIQyddkieO8oRAeWEc9exzrLmad+XwAK9YfGpOxFM8etanwVuy1ExeIX9pHsEBgmlcfxlLodc8buFkW7VMdryaavPgwt5tlFP4IFhf5Rn5clPcafLFVeswyzxfDcwMqVTT5lOibxrU+v4Py1JEQIDAQAB";

    public static final String  SIGN_ALGORITHMS = "SHA256WithRSA";

    public static void main(String[] args)throws Exception {

        Map<String,String> map = new HashMap<>();
        map.put("app_id","2016092101248425");
        map.put("buyer_id","2088102114562585");
        map.put("sign_type","RSA2");
        map.put("sign","12312313");

        //去除sign参数
        Map<String, String> result = new HashMap<String, String>();
        for (String key : map.keySet()) {
            String value = map.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }

        //根据参数名字母排序
        List<String> keys = new ArrayList<>(result.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = result.get(key);
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        System.out.println(prestr);
        //私钥签名
        String sign = sign(prestr,private_key,"utf-8");
        System.out.println(sign);
        String signss = "kg+5JIO0M/UH84KtZFlZOmFHPLNfRPqpteWh5ZbNrcOKgY3YzscVysw+L/v3hWJCT85Ln40eb5AdOmxRmEH/wBmwHdRWzNXRpftCWKsf3cz1fLfeD2JhwJBt5Fg8tbiQLrLYrkKDerfmCazxf6GGgFPq+K75rSJG4xMaB1wCGmCgl9PLWA9uwvX6pwPgSBHmwv4HTrlFbQs3cYW+htyaHUDhwGctHGbP0DnLBnyELqjHvMjaM5r1l5MkSia+14aPB6baRTEAd4geL8aUFGgjy95ru7gWDBGFA4NEHNlxmDj5rAXjiOUSAnwILTPSw5xpiHAtLOVwByWz+5JoioaYTA==";
        //签名检验，公钥检验
        System.out.println(verify(prestr,signss,public_key,"utf-8"));

    }


    /**
     * RSA签名
     * @param content 待签名数据
     * @param privateKey 商户私钥
     * @param input_charset 编码格式
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String input_charset)
    {
        try
        {
            PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) );
            KeyFactory keyf 				= KeyFactory.getInstance("RSA");
            PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes(input_charset) );

            byte[] signed = signature.sign();

            return Base64.encode(signed);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * RSA验签名检查
     * @param content 待签名数据
     * @param sign 签名值
     * @param ali_public_key 支付宝公钥
     * @param input_charset 编码格式
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String ali_public_key, String input_charset)
    {
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(ali_public_key);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update( content.getBytes(input_charset) );

            boolean bverify = signature.verify( Base64.decode(sign) );
            return bverify;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }


}
