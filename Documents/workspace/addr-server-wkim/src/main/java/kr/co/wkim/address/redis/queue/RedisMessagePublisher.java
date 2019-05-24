package kr.co.wkim.address.redis.queue;

import org.springframework.stereotype.Service;

/**
 * Redis Message Publisher
 * created by basquiat
 *
 */
@Service("redisMessagePublisher")
public class RedisMessagePublisher implements MessagePublisher {
	
	// 시스템 스레드 많이 올라오면 안되기 때문에?
	// log로 선언
	
	// redisTemplate를 autowired시켜서 사용해야함
	
	// redis의 pub message를 수행한다.
	// final??
	@Override
	public void publish(final String message) {
		//LOG.info();
		 
		//config에서 설정한 convertAandSend 메서드를 사용해야 한다.
		//사용해서 messge를 퍼블리시? 한다고 예상...
	}
	
	
}
