package com.cache.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象缓存代理类
 * @author wyj <br/>
 * date：2017-8-18
 */
public abstract class AbstractCacheProxy {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	//TODO 拓展缓存方案
	public static int cache_tag=0;  //0为ehcache,1为....

	protected ICacheUtil cache;
	public AbstractCacheProxy(){
		
	}
	public AbstractCacheProxy(String cacheName) {
	    switch (cache_tag) {
        case 0:
            cache = new EhCacheUtil(cacheName);
            break;
        default:
            break;
        }
	}
	
	
}