package kr.co.wkim.address.redis.queue;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * Redis Subscriber Service
 * redis 구독 서비스 
 * 이건 그냥 클래스인가???
 * created by wkimdev
 *
 */
public class RedisMessageSubscriber implements MessageListener{

	@Override
	public void onMessage(Message message, byte[] pattern) {
		// TODO Auto-generated method stub
		
	}
	
	// walletservice 선언
	
	//new String(message.getBody())
    // 수많은 채널중 필요한 채널만 걸려서 로그를 찍고 서비스를 태워야 한다.
    // 메세지로부터 객체로 변환시키고 변환된 메세지를 통해서 DB에 인서트 또는 업데이트 로직을 태운다.
	

}
