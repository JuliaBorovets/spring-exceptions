package com.softserve.itacademy.service;

import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User readById(long id) throws EntityNotFoundException;
    User update(User user) throws EntityNotFoundException;
    void delete(long id) throws EntityNotFoundException;
    List<User> getAll();

}
