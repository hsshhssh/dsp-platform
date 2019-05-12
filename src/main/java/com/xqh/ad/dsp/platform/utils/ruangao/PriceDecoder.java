package com.xqh.ad.dsp.platform.utils.ruangao;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
/**
 * Created by samson.huang on 2019/5/12
 */
public class PriceDecoder {

    public static void main(String[] args) {
        try {
            String token="A2K3UISD7T2dYSYN";
            String decoded_str = PriceDecoder.decodePrice(
                    "XtYd8ecCwZstpsy2JuFgxw", token);


            String price= decoded_str.trim();

            System.out.println("before:1567.23;after:"+price);

            decoded_str = PriceDecoder.decodePrice(
                    "if6r5JF5x-mTkbXs8SpPNA",token);
            price = decoded_str.trim();

            System.out.println("before:1897;after:"+price);


            String str="rH4hHBLC6P0p1t_PuoXeAw";

            System.out.println("before:1500.0;after:"+PriceDecoder.decodePrice(
                    str,token));

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param encoded_price 加密的价格
     * @param token         约定的密钥
     * @return              解密的价格
     */

    public static String decodePrice(String encoded_price, String token)
            throws InvalidKeyException, UnsupportedEncodingException,
            NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {
        byte[] after_base64_decode = Base64Decode(encoded_price);

        byte[] raw = token.getBytes("utf-8");
        SecretKeySpec key_spec = new SecretKeySpec(raw, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key_spec);
        byte[] decoded_price = cipher.doFinal(after_base64_decode);
        return new String(decoded_price, "utf-8");
    }

    private static byte[] Base64Decode(String encoded_price)  {
        return Base64.decodeBase64(encoded_price);
    }
}
