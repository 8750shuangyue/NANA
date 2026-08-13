package com.nana.service.impl;

import com.nana.common.exception.BusinessException;
import com.nana.common.exception.ResultCode;
import com.nana.model.User;
import com.nana.repository.UserRepository;
import com.nana.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link UserService} 实现。
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ResultCode.NOT_FOUND));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }
        userRepository.deleteById(id);
    }
}
