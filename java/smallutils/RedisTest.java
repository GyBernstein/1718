package javawebframework;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

	private static String ADDR = "1.1.1.1";
	private static int PORT = 6279;
	private static int MAX_ACTIVE = 1024;
	private static int MAX_IDLE = 200;
	private static int MAX_WAIT = 10000;
	private static int TIMEOUT = 10000;
	private static JedisPool jedisPool = null;
	
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static final String KEY = "test"; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis jedis = getJedis();
//		System.out.println(jedis.exists(ImesConstant.PRODUCT_SERIAL_CODE_KEY));
		StringBuilder builder = new StringBuilder();
		boolean exists = jedis.exists(KEY);
		if (exists) {
			// 存在则继续加
			int num = Integer.parseInt(jedis.get(KEY)) + 1;
			int numLength = String.valueOf(num).length();
			for (int i = 0; i < 5-numLength; i++) {
				builder.append("0");
			}
			builder.append(num+"");
		} else {
			// 不存在则设置初始值
			for (int i = 0; i < 4; i++) {
				builder.append("0");
			}
			builder.append("1");
		}
		System.out.println(builder.toString());
		jedisPool.returnResource(jedis);
		
	}

}
