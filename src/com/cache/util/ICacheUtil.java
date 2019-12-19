package com.cache.util;


/**
 * cache工具类接口
 */
public interface ICacheUtil {
	/**
	 * Get an item from the cache, nontransactionally
	 * @param key
	 * @return the cached object or <tt>null</tt>
	 * @throws CacheException
	 */
	public Object get(Object key);
	/**
	 * Add an item to the cache, nontransactionally, with
	 * failfast semantics
	 * @param key
	 * @param value
	 * @throws CacheException
	 */
	public void put(Object key, Object value);
	
	/**
     * 往缓存中写入内容
     * @param key
     * @param value
     * @param exp   超时时间，单位为秒
     */
    public void put(Object key, Object value, int exp);
    
	/**
	 * Remove an item from the cache
	 */
	public void remove(Object key);
	/**
	 * Clear the cache
	 */
	public void clear();
}
