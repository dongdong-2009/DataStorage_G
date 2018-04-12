package com.tendency.cb.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.expr.NewArray;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

public class JedisUtil
{
    private String ADDR = ProtocolUtils.GetConfg().RedisIP;

    // Redis的端口号
    private int PORT = Integer.parseInt(ProtocolUtils.GetConfg().RedisPort);

    // private static String AUTH = "admin";

    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private int MAX_ACTIVE = -1;

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private int MAX_IDLE = 200;

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private int MAX_WAIT = 10000;

    private int TIMEOUT = 100000;

    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private boolean TEST_ON_BORROW = false;
    // private static String JEDIS_SLAVE;
    private JedisPool jedisPool;

    public JedisUtil()
    {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWait(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, "tendency123456");

        //jedisPool = new JedisPool(config, ADDR, PORT);


    }

    static Object getJedis_ = new Object();

    public Jedis getJedis()
    {
        synchronized (getJedis_)
        {
            Jedis resource = null;
            try
            {
                if (jedisPool != null)
                {
                    resource = jedisPool.getResource();
                    return resource;
                }
                else
                {
                    return null;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }


    }

    static Object returnResource_ = new Object();

    public void returnResource(Jedis jedis)
    {
        synchronized (returnResource_)
        {
            if (jedis != null)
            {
                jedisPool.returnResource(jedis);
            }
        }

    }

    int RedisPineCount = 0;
    /**
     * 存储REDIS队列 顺序存储
     *
     * @param byte[] key reids键名
     * @param byte[] value 键值
     */
    static Object lpush1_ = new Object();

    public void lpush1(String key, String value)
    {

        synchronized (lpush1_)
        {

            Jedis jedis = null;
            long resuly = 0;
            try
            {
                if (jedisPool != null)
                {
                    jedis = getJedis();
                    Pipeline p = jedis.pipelined();
                    if (RedisPineCount >= 100)
                    {
                        p.lpush(key, value);
                        p.lpush(key, value);
                        p.lpush(key, value);
                        p.sync();
                        RedisPineCount = 0;
                        //ProtocolUtils.LogOut("redis 批量插入成功");
                        jedisPool.returnResource(jedis);
                    }
                    else
                    {
                        p.lpush(key, value);
                        RedisPineCount++;
                    }
                }
            }
            catch (Exception e)
            {
                // TODO: handle exception
                //ProtocolUtils.LogOut("入队列异常："+e.getMessage());
            }
            finally
            {
                jedisPool.returnResource(jedis);
                //return resuly;

            }
        }


    }

    static Object lpush_ = new Object();

    public long lpush(String key, String value)
    {

        Jedis jedis = null;
        long resuly = 0;
        synchronized (lpush_)
        {
            try
            {
                jedis = getJedis();
                resuly = jedis.lpush(key, value);
                return resuly;

            }
            catch (Exception e)
            {
                // TODO: handle exception
                //ProtocolUtils.LogOut("入队列异常："+e.getMessage());
                return resuly;
            }
            finally
            {
                jedisPool.returnResource(jedis);

            }
        }


    }

    static Object lpush2_ = new Object();

    public long lpush2(byte[] key, byte[] value)
    {

        synchronized (lpush2_)
        {
            Jedis jedis = null;
            long resuly = 0;
            try
            {
                jedis = getJedis();
                resuly = jedis.lpush(key, value);
                return resuly;

            }
            catch (Exception e)
            {
                // TODO: handle exception
                //ProtocolUtils.LogOut("入队列异常："+e.getMessage());
                return resuly;
            }
            finally
            {
                jedisPool.returnResource(jedis);


            }
        }


    }

    static Object GetlLen_ = new Object();

    public Long GetlLen()
    {
        synchronized (GetlLen_)
        {
            Long bytes = null;
            Jedis jedis = null;
            try
            {
                jedis = getJedis();
                bytes = jedis.llen("RedisData");
                return bytes;
            }
            catch (Exception e)
            {
                // TODO: handle exception
                try
                {
                    Thread.sleep(1000 * 30);
                }
                catch (InterruptedException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            finally
            {
                jedisPool.returnBrokenResource(jedis);
            }
        }
        return null;
    }

    /**
     * 获取队列数据
     *
     * @param byte[] key 键名
     * @return
     */
    static Object rpop_ = new Object();

    public List<String> rpop(String key)
    {

        synchronized (rpop_)
        {
            List<String> bytes = null;
            Jedis jedis = null;
            try
            {
                jedis = getJedis();
                //key="show";
                bytes = jedis.brpop(0, key);
                return bytes;

            }
            catch (Exception e)
            {

                e.printStackTrace();
                try
                {
                    Thread.sleep(1000 * 30);
                }
                catch (InterruptedException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                return bytes;

            }
            finally
            {
                jedisPool.returnBrokenResource(jedis);

            }

        }


    }

    static Object CallBackrpop_ = new Object();

    public List<String> CallBackrpop(String key)
    {

        synchronized (CallBackrpop_)
        {
            List<String> bytes = null;
            Jedis jedis = null;
            try
            {
                jedis = getJedis();
                bytes = jedis.brpop(0, key);
                return bytes;

            }
            catch (Exception e)
            {

                e.printStackTrace();
                try
                {
                    Thread.sleep(1000 * 30);
                }
                catch (InterruptedException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                return bytes;

            }
            finally
            {
                jedisPool.returnBrokenResource(jedis);

            }

        }


    }

    static Object CallBackOnlineStatusrpop_ = new Object();

    public List<String> CallBackOnlineStatusrpop(String key)
    {

        synchronized (CallBackOnlineStatusrpop_)
        {
            List<String> bytes = null;
            Jedis jedis = null;
            try
            {
                jedis = getJedis();
                bytes = jedis.brpop(0, key);
                return bytes;

            }
            catch (Exception e)
            {

                e.printStackTrace();
                try
                {
                    Thread.sleep(1000 * 30);
                }
                catch (InterruptedException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                return bytes;

            }
            finally
            {
                jedisPool.returnBrokenResource(jedis);

            }

        }


    }

    /**
     * 向缓存中设置字符串内容
     *
     * @param key
     * key
     * @param value
     * value
     * @return
     * @throws Exception
     */
    static Object set_ = new Object();

    public boolean set(String key, String value) throws Exception
    {
        synchronized (set_)
        {
            Jedis jedis = null;
            try
            {
                jedis = getJedis();
                jedis.set(key, value);
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
            finally
            {
                jedisPool.returnBrokenResource(jedis);
            }
        }

    }

    /**
     * 删除缓存中得对象，根据key
     *
     * @param key
     * @return
     */
    static Object del_ = new Object();

    public boolean del(String key)
    {
        synchronized (del_)
        {
            Jedis jedis = null;
            try
            {
                jedis = getJedis();
                jedis.del(key);
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
            finally
            {
                jedisPool.returnBrokenResource(jedis);
            }
        }

    }

    /**
     * 删除缓存中得对象，根据key
     *
     * @param key
     * @return
     */
    static Object hdel_ = new Object();

    public boolean hdel(String key, String va)
    {
        synchronized (hdel_)
        {
            Jedis jedis = null;
            try
            {
                jedis = getJedis();
                jedis.hdel(va, va);
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
            finally
            {
                jedisPool.returnBrokenResource(jedis);
            }
        }

    }

    /**
     * 根据key 获取内容
     *
     * @param key
     * @return
     */
    static Object get_ = new Object();

    public String get(String key)
    {
        synchronized (get_)
        {
            Jedis jedis = null;
            try
            {
                jedis = getJedis();
                String value = jedis.get(key);
                return value;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
            finally
            {
                jedisPool.returnBrokenResource(jedis);
            }
        }


    }

    static Object hset_ = new Object();

    public long hset(String aream, String key, String value)
    {
        synchronized (hset_)
        {
            Long result = (long) 0;
            Jedis resource = null;
            try
            {
                resource = getJedis();
                result = resource.hset(aream, key, value);

                return result;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return 0;
            }
            finally
            {
                if (resource != null)
                {
                    jedisPool.returnBrokenResource(resource);
                }
            }
        }

    }

    static Object hget_ = new Object();

    public String hget(String aream, String key)
    {
        synchronized (hget_)
        {
            String result;
            Jedis resource = null;
            try
            {
                resource = getJedis();
                if (resource.hexists(aream, key))
                {
                    result = resource.hget(aream, key);
                    return result;
                }
                else
                {
                    return null;
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
            finally
            {
                if (resource != null)
                {
                    jedisPool.returnResource(resource);
                }
            }
        }

    }

    static Object hRemove_ = new Object();

    public long hRemove(String aream, String key)
    {
        synchronized (hRemove_)
        {
            Long result = (long) 0;
            Jedis resource = null;
            try
            {
                resource = getJedis();
                if (resource.hexists(aream, key))
                {
                    result = resource.hdel(aream, key);
                }
                return result;
            }
            catch (Exception e)
            {

                return (long) 0;
            }
            finally
            {
                if (resource != null)
                {
                    jedisPool.returnResource(resource);
                }
            }
        }
    }

    public void Stop()
    {
        // TODO Auto-generated method stub
        jedisPool.destroy();
    }

    static Object validateObject_ = new Object();
    ;

    public boolean validateObject()
    {
        synchronized (validateObject_)
        {
            Jedis resource = null;
            try
            {
                resource = getJedis();
                if (resource.isConnected() && resource.ping().equals("PONG"))
                {

                    return true;
                }
                else
                {
                    jedisPool.destroy();
                    return false;
                }

            }
            catch (final Exception e)
            {
                jedisPool.destroy();
                return false;
            }
            finally
            {
                if (jedisPool != null)
                {
                    jedisPool.returnResource(resource);
                }

            }
        }
    }

    static Object GetRedisInfo_ = new Object();
    ;

    public String[] GetRedisInfo()
    {
        synchronized (validateObject_)
        {
            Jedis resource = null;
            try
            {
                resource = getJedis();
                return resource.info().split("\r");

            }
            catch (final Exception e)
            {
                jedisPool.destroy();
                return null;
            }
            finally
            {
                if (jedisPool != null)
                {
                    jedisPool.returnResource(resource);
                }

            }
        }
    }

}
