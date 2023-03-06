package com.softeem.dao;

import com.softeem.pojo.Role;
import java.util.Set;

public interface RoleDao {
    public Set<Role> findByUserId(int id);
}