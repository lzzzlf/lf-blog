package com.lzzz.blog.service;

import com.lzzz.blog.entity.User;

public interface UserService {

    User findUserByUsernameAndPassword(String username, String password);

    User getUserInfo(Long id);

}
