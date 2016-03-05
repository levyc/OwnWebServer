package com.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.request.OwnRequest;

/**
 * 响应对象
 * 
 * @author levy
 *
 */
public class OwnResponse {
    private OutputStream ops;
    public OwnResponse(OutputStream outputStream) {
        this.ops = outputStream;
    }

    public void setRequest(OwnRequest request) {
    }

    /**
     * 响应请求静态资源的HTTP请求
     */
    public void sendStaticResource(String staticResourcePath) {
        
        FileInputStream fis = null;
        
        int ch;
        
        byte[] staticData = new byte[2048];
        
        try {
            fis = new FileInputStream(new File(staticResourcePath));
            ch = fis.read(staticData);
            if(ch!=-1){
                ops.write(staticData,0,ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(fis!=null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


    }
   



}
