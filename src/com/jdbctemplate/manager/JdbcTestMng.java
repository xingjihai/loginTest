package com.jdbctemplate.manager;

import java.util.List;

import com.jdbctemplate.Member;

public interface JdbcTestMng {
    /**
     * 查询list
     */
    public List<Member> getList();
    /**
     * 查询object
     */
    public Member getMember(Integer id);
    
    /**
     * 插入对象
     */
    public Member insertMember();
    /**
     * 删除对象
     */
    public Member deleteMember(Integer id);
    
    /**
     * 更新对象
     * @return
     */
    public Member updateMember();
}
