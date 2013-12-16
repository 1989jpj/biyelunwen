package com.whut.jiao;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
@Deprecated
public class MyMessageInbound extends MessageInbound {

	private String user;
	
	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onTextMessage(CharBuffer msg) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println(msg+"连接成功");
		user = msg.toString();
		
		WsOutbound outbound = this.getWsOutbound();
		/*
		 * 该代码段主要实现间隔三秒钟向客户端发送随机数
		 * */
		/*while (true) {
			int temp = (int) (Math.random() * 10);
			String dataString = String.valueOf(temp);
			outbound.writeTextMessage(CharBuffer.wrap(dataString));
			outbound.flush();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		
		/*
		 * 下面代码，主要用redis的pub/sub功能，实现消息的推送
		 * */
	}

	@Override
	protected void onClose(int status){
		InitServlet.getSocketList().remove(this);
		System.out.println(user + "断开连接!");
		super.onClose(status);
	}

	@Override
	protected void onOpen(WsOutbound outbound){
		super.onOpen(outbound);
		InitServlet.getSocketList().add(this);
		Jedis jedis = new Jedis("localhost");
		final WsOutbound wsOutbound = this.getWsOutbound();
		JedisPubSub jedisPubSub = new JedisPubSub() {
			
			@Override
			public void onUnsubscribe(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onSubscribe(String arg0, int arg1) {
				// TODO Auto-generated method stub
				System.out.println("您已建立数据监控连接！");
			}
			
			@Override
			public void onPUnsubscribe(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPSubscribe(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPMessage(String arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onMessage(String arg0, String arg1) {
				// TODO Auto-generated method stub
				try {
					wsOutbound.writeTextMessage(CharBuffer.wrap(arg1));
					wsOutbound.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		jedis.subscribe(jedisPubSub, "number1");
	}

}
