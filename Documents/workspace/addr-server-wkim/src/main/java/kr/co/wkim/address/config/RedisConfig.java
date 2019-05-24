package kr.co.wkim.address.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

/**
 *
 * redis config
 *
 * created by wkimdev
 *
 */
public class RedisConfig {
	
	// yml properties 파일에 환경설정이 되어 있어야 함
	// 환경설정 이후, value 어노테이션으로 프로퍼티스값 불러옴.
	@Value("${}")
	private String pubChannel;
	
	@Value("${}")
	private String redisHost;
	
	@Value("${}")
	private int redisPort;
	
	@Value("${}")
	private String redisPassword;
	
	// bean 어노테이션으로
	// setup, connection factory값을 가져온다.
	// org.springframework.context.annotation 의 Configuration 어노테이션과 
	// Bean 어노테이션 코드를 이용하여 스프링 컨테이너에 새 로운 빈 객체를 제공할 수 있다.
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(redisHost);
		jedisConnectionFactory.setPort(redisPort);
		jedisConnectionFactory.setPassword(redisPassword);
		jedisConnectionFactory.setUsePool(true);		
		return jedisConnectionFactory;
	}
	
	// redis Template Setup
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return template;
	}
	
	/**
     * redis listner
     * @return RedisMessageListenerContainer
     */
	@Bean
	public RedisMessageListenerContainer redisContainer() {
		final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        // subscribe 채널 등록
        container.addMessageListener(messageListener(), topics());
		return container;
	}

	private Collection<? extends Topic> topics() {
		// TODO Auto-generated method stub
		return null;
	}

	private MessageListener messageListener() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
