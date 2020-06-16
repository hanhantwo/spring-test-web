package com.bjmashibing.system.io;

import java.io.*;
import java.net.Socket;

/**
 * @author: 马士兵教育
 * @create: 2020-05-17 16:18
 */
public class SocketClient {

    public static void main(String[] args) {

        try {
            Socket client = new Socket("192.168.150.11",9090);
            //设置发送缓冲区大小为20个字节
            client.setSendBufferSize(20);
            //是否开启快速传输功能
            //该配置如果设置为false，客户端发送信息的时候会将内容攒成一个包一起发送，
            //如果为true，则是着急着发，会有多少发多少。这样就会一条信息拆分发送
            client.setTcpNoDelay(true);
            OutputStream out = client.getOutputStream();

            InputStream in = System.in;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while(true){
                String line = reader.readLine();
                if(line != null ){
                    byte[] bb = line.getBytes();
                    for (byte b : bb) {
                        out.write(b);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
