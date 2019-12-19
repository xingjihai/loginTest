package com.cache.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jdbctemplate.Member;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

 
/**
 * Ehcache工具类
 */
public class EhCacheUtil implements ICacheUtil{

	private net.sf.ehcache.Cache cache;

	/**
	 * 根据name初始化EhCacheUtil
	 * @param name
	 */
	public EhCacheUtil(String name){
        try {
            CacheManager manager = CacheManager.getInstance();
            cache = manager.getCache(name);
            
            if (cache == null) {
                manager.addCache(name);
                cache = manager.getCache(name);
            }
        } catch (net.sf.ehcache.CacheException e) {
        	e.printStackTrace();
        }
	}


    /**
     * Gets a value of an element which matches the given key.
     * @param key the key of the element to return.
     * @return The value placed into the cache with an earlier put, or null if not found or expired
     * @throws CacheException
     */
	public Object get(Object key){
    	
    	Object obj = null;
        try {
            if (key!= null) {
                Element element = cache.get((Serializable) key);
                if (element!=null) {
                    obj = element.getObjectValue();
                }
            }
        } catch (net.sf.ehcache.CacheException e) {
            e.printStackTrace();
        }
        return obj;
    }

	/**
     * 往缓存中写入内容
     * @param key
     * @param value
     * @param exp   超时时间，单位为秒
     */
    public void put(Object key, Object value, int exp) {
        try {
            Element element = new Element((Serializable) key,
                    (Serializable) value);
            element.setTimeToLive(exp);
            cache.put(element);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * Puts an object into the cache.
     * @param key a {@link Serializable} key
     * @param value a {@link Serializable} value
     * @throws CacheException if the parameters are not {@link Serializable}, the {@link CacheManager}
     * is shutdown or another {@link Exception} occurs.
     */
    public void put(Object key, Object value){
        try {
            Element element = new Element((Serializable) key, (Serializable) value);            
            cache.put(element);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the element which matches the key.
     * <p>
     * If no element matches, nothing is removed and no Exception is thrown.
     * @param key the key of the element to remove
     * @throws CacheException
     */
    public void remove(Object key){
        try {
            cache.remove((Serializable) key);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Remove all elements in the cache, but leave the cache
     * in a useable state.
     * @throws CacheException
     */
    public void clear(){
        try {
            cache.removeAll();
        } catch (IllegalStateException e) {
        	e.printStackTrace();
        } 
    }
    
    /**
     * 删除CacheManager工厂中全部缓存
     */
    public static void clearAll(){
        try {
            CacheManager manager = CacheManager.getInstance();
            manager.removalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args){
        Member member1=new Member();
        member1.setUsername("member1");
        Member member2=new Member();
        member2.setUsername("member2");
        List<Member> list=new ArrayList<Member>();
        list.add(member1);
        list.add(member2);
        
        EhCacheUtil cache = new EhCacheUtil("aaa");
    	cache.put("test",list);
    	List<Member> cacheList=(List<Member>) cache.get("test");
    	for (Member member : cacheList) {
            System.out.println(member.getUsername());
        }
//    	System.out.println(cache.get("test"));
	}
	
}
