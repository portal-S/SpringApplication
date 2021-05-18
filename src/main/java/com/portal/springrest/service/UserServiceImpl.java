package com.portal.springrest.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.portal.springrest.model.User;
import com.portal.springrest.repository.interfaces.UserRepository;
import com.portal.springrest.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;


    @Override
    public User getBuId(int id) {
        return repository.findOne(id);
    }

    @Override
    public List<User> readAll() {
        return repository.findAll();
    }

    @Override
    public void create(User user) {
        repository.save(user);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
