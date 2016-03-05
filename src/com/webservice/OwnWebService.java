package com.webservice;

import java.awt.SecondaryLoop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.request.OwnRequest;
import com.response.OwnResponse;

public class OwnWebService {
    ServerSocket serverSocket;
    FileInputStream fis;
    File file;

    public void start() throws UnknownHostException, IOException {
        serverSocket = new ServerSocket(10086, 10, InetAddress.getByName("127.0.0.1"));
        Socket socket = null ;
        boolean shutdowm = false;
        while (!shutdowm) {
            try{  socket = serverSocket.accept();
            OutputStream ops = socket.getOutputStream();
            InputStream ips = socket.getInputStream();
            OwnRequest ownRequest = new OwnRequest(ips);
            OwnResponse ownResponse = new OwnResponse(ops);
            ownResponse.setRequest(ownRequest);
            String uri = ownRequest.getUri();
            System.out.println(uri);
            String staticResourcePath =  ownRequest.getStaticResourcePath(uri);
            System.out.println(staticResourcePath);
            if(uri==null)   System.exit(1); 
            if(uri.startsWith("/static")){
                ownResponse.sendStaticResource(staticResourcePath);
            }
            else if(uri.startsWith("/servlet")) {
                //响应动态请求的实现
            }
            else if(uri.equals("/shutdown")){
                 shutdowm  = true;
            }
            }
            finally{
                socket.close();
            }
            }
        }    


    public static void main(String[] args) throws UnknownHostException, IOException {
        new OwnWebService().start();
    }
}
