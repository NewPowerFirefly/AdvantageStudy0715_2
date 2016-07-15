package com.gaobo.firefly.advantagestudy1.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by gy on 2016/6/8.
 */
public class StreamUtil {
    public static  String getString(InputStream is){
        byte[] bf = new byte[1024];
        int length = -1;
        StringBuffer sb=new StringBuffer();
        try {
            while((length=is.read(bf))!=-1){
                sb.append(new String(bf,0,length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  sb.toString();
    }
}
