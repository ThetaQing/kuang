# 网络编程
## 1 基本知识
### 1.1 概述
计算机网络：利用通信线路和通信设备，将地理位置不同的、功能独立的多台计算机互连起来，以功能完善的网络软件来实现资源共享和信息传递，
就构成了计算机网络系统。  
网络编程的目的：传播交流信息，数据交换，通信  
想要达到这个目的需要什么：  
1、如何准确地定位网络上地一台主机 192.168.16.124：端口，定位到该计算机上的某个资源；  
2、找到了这个主机，如何传输数据呢？传输介质  
### 1.2 网络通信的要素
* 如何实现网络的通信：  
通信双方的地址：ip+端口号  
规则
### 1.3 小结
* 1、网络编程中的两个主要问题  
如何准确定位到网络上的一台或多台主机；  
找到主机之后如何进行通信。  
* 2、网络编程中的要素  
IP和端口号；IP类  
网络通信协议。TCP类、UDP类  
* 3、socket：套接字，即两台机器之间通讯的端点。  
* 4、万物皆对象

## 2 实际应用
### 2.1 IP
ip地址类：InetAddress  
* 唯一定位一台网络上计算机；  
* 127.0.0.1：本机localhost   
* ip地址的分类  
    * ipv4/ipv6  
        * ==IPV4== 127.0.0.1，由4个字节组成，每个字节0~255，共42亿；30亿在北美，4亿在亚洲，2011年用尽；  
        * ==IPV6== 128位。8个无符号整数  
    * 公网（互联网）-私网（局域网）
        * ABCD类地址
        * 192.168.xx.xx，专门给组织内部使用的
* 域名  
记忆IP问题  
inetAddress类
### 2.2 端口
端口表示计算机上的一个程序的进程；  
* 不同的进程有不同的端口好，用来区分软件；  
* 被规定0~65535  
* 被分为TCP、UDP，所以一共有65535*2  单个协议下端口号不能冲突，不同协议可以相同；  
* 端口分类  
    * 公有端口0~1023  
        * HTTP：80  
        * HTTPS：443
        * FTP：21  
        * Telent：23  
    * 程序注册端口：1024~49151，分配用户或者程序  
        * Tomcat：8080  
        * MySQL：3306
        * Oracle：1521  
    * 动态、私有：49152~65535  
      ```  
        netstat -ano  # 查看所有的端口  
        netstat -ano|findstr "1080"  # 查看指定的端口号
        tasklist|findstr "1128"  # 查看指定端口的进程  
        Ctrl+Shift+Esc 快速打开任务管理器
      ```  
inetSocketAddress类  
### 2.3 通信协议  
协议：约定。  
**网络通信协议**：速率、传输码率、代码结构、传输控制……  
问题解决：分层  
**TCP/IP协议簇：实际上是一组协议**  
重要的两个协议：（传输层）  
* TCP：用户传输协议  
* UDP：用户数据包协议  

著名协议：
* TCP协议  
* IP：网络互连协议  

**TCP UDP对比**  
TCP：打电话  
* 连接：稳定  
* 三次握手、四次挥手  
    ```
  三次握手：ABA  
  最少需要三次，保证稳定连接
    A:你瞅啥？  
    B:瞅你咋地？  
    A:干一场  
  
  四次挥手：ABBA
  A：我要走了  
  B：你真的要走了吗
  B: 你真的真的要走了吗？
  A: 我真的要走了！
  ```
* 客户端、服务端有明确的界限  
* 传输完成、释放连接、效率低
  

UDP：发短信  
* 不连接，不稳定  
* 客户端、服务器没有明确的界限
* 不管有没有准备好，都可以发给你  
* 导弹  
* DDOS：洪水攻击，（饱和攻击）  
### 2.4 TCP

客户端  
1、通过socket连接服务器；  
2、发送消息getOutPutStream。  
```java
package socket.TCP;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//客户端
public class TcpClientDemo01 {
    public static void main(String[] args) {

        Socket socket = null;
        OutputStream os = null;
        try {
            // 1、要知道的服务器地址、端口号
            InetAddress serverIP = InetAddress.getByName("127.0.0.1");
            int port = 9999;

            // 2、创建一个socket连接
            socket = new Socket(serverIP,port);

            // 3、发送消息  IO 流
            os = socket.getOutputStream();
            os.write("hello,welcome to class".getBytes());

            // 关闭资源
            os.close();
            socket.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        //todo:关闭资源
        }

    }
}

```
服务器  
1、建立服务的端口ServeSocket；  
2、等待客户端连接accept；  
3、读取客户端消息getInputStream。  
```java
package socket.TCP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

// 服务端
public class TcpServerDemo01 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            // 1、我得有一个地址
            serverSocket = new ServerSocket(9999);

            while(true){  // 循环监听
                // 2. 等待客户端连接过来
                socket = serverSocket.accept();  // 监听，一旦监听到就是来自客户端的socket

                // 3、读取客户端的消息
                is = socket.getInputStream();
                // 管道流,通过管道流将两节包裹起来，如果直接用string去写入的话，一旦出现中文就有可能乱码
                baos = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer))!= -1){
                    baos.write(buffer,0,len);
                }
                System.out.println(baos.toString());
            }



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
          // todo:关闭资源
        }
    }
}

```  
注意：端口号一定要对应  
  
**文件上传**
==传输的是什么就用什么管道流，这里前半部分传输的是文件用文件的管道流，后半部分传输的是比特流，就用比特流管道==  
注意这里的输入输出是以服务器为参照，输入流表示输入服务器。  
客户端  
* 1、创建一个socket连接；  
* 2、创建一个输出流；  
* 3、准备输出数据，如果是一个文件，创建一个文件输入流，将文件读入内存并用buffer完成读写，并写入到输出流中；  
* 4、通知服务器发送完成；  
* 5、确认服务器接收完毕，客户端接收服务器发来的确认消息用ByteArrayOutputStream完成接收；  
* 6、关闭资源  
```java
package socket.TCP;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClientDemo02 {
    public static void main(String[] args){

        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
        InputStream inputStream =null;
        ByteArrayOutputStream baos = null;
        try {
            //1、创建一个socket连接
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9000);
            // 2 create an output stream
            os = socket.getOutputStream();

            // 3. file stream
            // 3.1 读入文件
            fis = new FileInputStream(new File("G://Code//Java//kuang//src//socket//TCP//lychee.jpg"));
            // 3.2 将这个文件写出
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer))!= -1){
                os.write(buffer,0,len);

            }
            // 通知服务器已经传输完成
            socket.shutdownOutput();  // 传输完成
            // 确定服务端接收完毕
            inputStream = socket.getInputStream();
            // getBytes返回的是byte[]类型
            baos = new ByteArrayOutputStream();

            byte[] buffer2 = new byte[1024];
            int len2;
            while((len2=inputStream.read(buffer2))!=-1){
                baos.write(buffer2,0,len2);
            }
            System.out.println(baos.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if(baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket!= null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}

```
服务端  
* 1、创建一个socket连接；  
* 2、监听客户端的连接；  
* 3、创建输入流对客户端发来的socket进行读取socket.getInputStream；  
* 4、将输入流写出到内存；  
* 5、通知客户端完成接收；  
* 6、关闭资源。  
```java
package socket.TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo02 {
    public static void main(String[] args) {
        try {

            // 1 create a socket
            ServerSocket serverSocket = new ServerSocket(9000);
            //2. listen to client
            Socket socket = serverSocket.accept();  // 阻塞式监听，会一直等待客户端连接
            // 3. get instream
            InputStream is = socket.getInputStream();  // 读取文件流

            // 4. 文件输出 将这个文件写出
            FileOutputStream fos = new FileOutputStream(new File("G://Code//Java//kuang//src//socket//TCP//receive.png"));

            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer))!= -1){  // 读取比特，用buffer存储，len表示实际buffer中存储了多少个byte
                fos.write(buffer,0,len);  // 从buffer中将0-len个写入到输出流中
            }

            // 通知客户端完成接收
            OutputStream os = socket.getOutputStream();
            os.write("recieved".getBytes());


            // 5. 关闭资源
            fos.close();
            is.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


```
**Tomcat服务器**

### 2.5 UDP
发短信：不用连接，但是需要知道对方地址  
DatagramPacket、 DatagramSocket  
发送端：  
* 1、创建socket；  
* 2、创建一个包用于发送；  
* 3、发送这个包。
```java
package socket.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

// 没有明确的客户端和服务端，这里只是做个区分
// 不需要连接服务端
public class UdpSendDemo01 {
    public static void main(String[] args) {

        try {
            // 1. 建立一个socket
            DatagramSocket socket = new DatagramSocket();

            // 2. 创建一个包
            // 2.1 需要发送的数据
            String msg = "你好啊，服务器！";
            //  2.2 服务器地址、端口号
            InetAddress localhost = InetAddress.getByName("localhost");
            int port = 9090;
            // 2.3 创建这个包
            DatagramPacket packet = new DatagramPacket(msg.getBytes(), 0, msg.getBytes().length, localhost, port);

            // 3. 发送这个包
            socket.send(packet);

            // 4. 关闭流
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

```  

接收端：  
* 1、开放这个端口；  
* 2、创建一个包用于接收；  
* 3、 接收一个发送端发来的包。  
```java
package socket.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

// 没有明确的服务端概念，这里只是为方便区分

// 接收端：等待发送端的发送
public class UdpReceivedDemo01 {
    public static void main(String[] args) {

        try {
            // 1. 开放端口
            DatagramSocket socket = new DatagramSocket(9090);

            // 2. 接收
            // 2.1 创建接收的包容器
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
            // 2.2 接收
            socket.receive(packet);  // 阻塞接收

            System.out.println(packet.getAddress().getHostAddress());
            System.out.println(new String(packet.getData()));  // 显示
            // 3. 关闭
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```  


