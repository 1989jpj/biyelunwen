package com.whut.jiao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

/**
 * Servlet implementation class WsServlet
 */
@Deprecated
@WebServlet("/WsServlet")
public class WsServlet extends WebSocketServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		System.out.println("########");
		return new MyMessageInbound();
	}

}
