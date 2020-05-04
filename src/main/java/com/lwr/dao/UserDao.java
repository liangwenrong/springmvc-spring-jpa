package com.lwr.dao;

import org.springframework.data.repository.Repository;
import com.lwr.entity.User;

public interface UserDao extends Repository<User, String> {
    //根据jpa-api规范，get[]By[字段名]
    public User getUserById(String id);
}
