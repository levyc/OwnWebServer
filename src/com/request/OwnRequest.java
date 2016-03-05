package com.request;

import java.io.IOException;
import java.io.InputStream;

/**
 * 请求对象
 * 
 * @author levy
 *
 */
public class OwnRequest {
    InputStream ins;
    byte[] requestMes = new byte[1024 * 1024];
    StringBuffer sb = new StringBuffer();
    String uri = null;

    public OwnRequest(InputStream inputStream) {
        this.ins = inputStream;
    }

    public String getUri() throws IOException {
        int count = readtRequestMes();
        
        if (count != -1) {
            for (int j = 0; j < count; j++) {
                sb.append((char) requestMes[j]);
            }
            System.out.println(sb);
            int index1 = sb.indexOf(" ");
            System.out.println(index1);
            int index2 = sb.indexOf(" ", index1 + 1);
            System.out.println(index2);
            uri = sb.substring(index1 + 1, index2);
            System.out.println(uri);
        }
        return uri;
    }

    public String getStaticResourcePath(String uri) throws IOException {
        return System.getProperty("user.dir") + uri;
    }

    public int readtRequestMes() throws IOException {
        return ins.read(requestMes);
    }
}
