package com.nana.service;

import com.nana.model.User;

import java.util.List;

/**
 * 业务逻辑层接口。
 */
public interface UserService {

    List<User> listUsers();

    User getUser(Long id);

    User createUser(User user);

    void deleteUser(Long id);
}
