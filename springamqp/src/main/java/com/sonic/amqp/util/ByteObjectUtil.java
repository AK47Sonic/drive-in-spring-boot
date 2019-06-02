package com.sonic.amqp.util;

import com.sonic.amqp.bean.Book;

import java.io.*;

/**
 * ByteObjectUtil
 *
 * @author Sonic
 * @since 2019/6/2
 */
public class ByteObjectUtil {

    /**
     * 对象转byte
     * @param obj
     * @return
     */
    public static byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        try {
            // object to bytearray
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();

        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                bo.close();
                oo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return bytes;
    }

    /**
     * byte转对象
     * @param bytes
     * @return
     */
    public static Object ByteToObject(byte[] bytes) {
        Object obj = null;
        try {
            // bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        System.out.println(ByteToObject(ObjectToByte(new Book("aa", "aa"))));
    }

}
