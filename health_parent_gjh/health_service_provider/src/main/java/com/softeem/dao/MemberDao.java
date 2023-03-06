package com.softeem.dao;

import com.github.pagehelper.Page;
import com.softeem.pojo.Member;

import java.util.List;

public interface MemberDao {
    public List<Member> findAll();
    public Page<Member> selectByCondition(String queryString);
    public void deleteById(Integer id);
    public Member findById(Integer id);
    public void add(Member member);
    public Member findByTelephone(String telephone);
    public Integer findMemberCountBeforeDate(String date);
    public void edit(Member member);
    public Integer findMemberCountByDate(String date);
    public Integer findMemberCountAfterDate(String date);
    public Integer findMemberTotalCount();
}
