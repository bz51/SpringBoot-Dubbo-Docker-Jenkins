package com.gaoxi.facade.redis;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 下午3:33
 * @description Redis服务接口
 */
public interface RedisUtils {

    /**
     * 批量删除
     * @param keys key数组
     */
    public void remove(final String... keys);

    /**
     * 批量删除指定key
     * @param pattern
     */
    public void removePattern(final String pattern);

    /**
     * 删除指定key
     * @param key
     */
    public void remove(final String key);

    /**
     * 判断指定key是否存在
     * @param key
     * @return
     */
    public boolean exists(final String key);

    /**
     * 获取指定key
     * @param key
     * @return
     */
    public Object get(final String key);

    /**
     * 添加key-value（使用默认失效时间）
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value);

    /**
     * 添加key-value（指定失效时间）
     * @param key
     * @param value
     * @param expireTime 失效时间（单位秒）
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime);
}
