package com.nana.repository;

import com.nana.model.User;

import java.util.List;
import java.util.Optional;

/**
 * 数据访问层接口，后续可替换为 JPA / MyBatis 实现。
 */
public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long id);
}
