/**
 * <br>项目名: csvo
 * <br>文件名: WebSocket.java
 * <br>Copyright 2016
 */
package com.id.tools.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.StringUtils;
 
//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping
@ServerEndpoint("/websocket/{userCode}")
public class WebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
     
    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
    
    private static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();
     
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
     
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     * @throws IOException 
     */
    @OnOpen
    public void onOpen(@PathParam("userCode") String userid,Session session) throws IOException{
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        if(StringUtils.isNotBlank(userid)) {
        	webSocketMap.put(userid, this);
        	sessionMap.put(userid, session);
        }
        
        this.sendMessage(userid, "connect success");
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }
     
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("userCode") String userid, Session session){
        webSocketSet.remove(this);  //从set中删除
        webSocketMap.remove(userid, session);
    	sessionMap.remove(userid, session);
        subOnlineCount();           //在线数减1    
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
     
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(@PathParam("userCode") String userid, String message, Session session) {
        System.out.println("来自用户【" + userid + "】的消息:" + message);
        
        //群发消息
        for(WebSocket item: webSocketSet){             
            try {
                item.sendMessage(userid, message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
     
    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
     
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String userid, String message) throws IOException{
        this.session.getBasicRemote().sendText(userid + ":-----" + message);
        //this.session.getAsyncRemote().sendText(message);
    }
 
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }
     
    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }
}
