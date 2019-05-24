package kr.co.wkim.address.redis.queue;

/**
 * 
 * Redis Message Publisher Interface
 * 
 * created by wkimdev
 *
 */
public interface MessagePublisher {
	
	//message를 인자로 받아 처리하는 publish라는 메서드의 인터페이
	public void publish(final String message);

}
