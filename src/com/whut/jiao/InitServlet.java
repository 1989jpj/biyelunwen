package com.whut.jiao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.catalina.websocket.MessageInbound;

/**
 * Servlet implementation class InitServlet
 */
@SuppressWarnings("deprecation")
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<MessageInbound> socketList;   
    
    //Init方法，只有在web.xml中进行配置时，才会在服务器启动时执行
    public void init(ServletConfig config) throws ServletException{
    	InitServlet.socketList = new ArrayList<MessageInbound>();
    	super.init(config);
    	System.out.println("Server start==========");
    }
    
    public static List<MessageInbound> getSocketList(){
    	return InitServlet.socketList;
    }
}
