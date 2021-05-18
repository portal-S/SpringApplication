package com.portal.springrest.service.interfaces;

import com.portal.springrest.model.User;

import java.util.List;

public interface UserService {

    User getBuId(int id);

    List<User> readAll();

    void create(User user);

    void delete(int id);

}
