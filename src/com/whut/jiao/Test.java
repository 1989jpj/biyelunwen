package com.whut.jiao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class Test {
	
	public static void main(String args[]){
		Jedis jedis = new Jedis("localhost", 6379);
		JedisPubSub jedisPubSub = new JedisPubSub() {
			
			@Override
			public void onUnsubscribe(String arg0, int arg1) {
				// TODO Auto-generated method stub
				System.out.println(arg0 + "1:" + arg1);
			}
			
			@Override
			public void onSubscribe(String arg0, int arg1) {
				// TODO Auto-generated method stub
				System.out.println(arg0 + "2:" + arg1);
			}
			
			@Override
			public void onPUnsubscribe(String arg0, int arg1) {
				// TODO Auto-generated method stub
				System.out.println(arg0 + "3:" + arg1);
			}
			
			@Override
			public void onPSubscribe(String arg0, int arg1) {
				// TODO Auto-generated method stub
				System.out.println(arg0 + "4:" + arg1);
			}
			
			@Override
			public void onPMessage(String arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				System.out.println(arg0 + "5:" + arg1);
			}
			
			@Override
			public void onMessage(String arg0, String arg1) {
				// TODO Auto-generated method stub
				System.out.println(arg0 + "6:" + arg1);
			}
		};
		
		jedis.subscribe(jedisPubSub, "number1");
	}
}
