思路：
1.用serverSocket写一个服务器，用于等待请求的到来。当请求来到时，创建请求和响应对象 ，将socket的输入传给请求用于读请求报文
输出流传给响应对象用于给写要返回的数据给客户端。
2.写一个request，用于接收浏览器发来的请求信息，含有一些对请求报文进行处理的函数
3.写一个respoense，用于返回响应信息给浏览器，含有一些返回资源的函数