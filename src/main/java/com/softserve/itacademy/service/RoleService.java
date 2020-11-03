package com.softserve.itacademy.service;

import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.model.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);
    Role readById(long id) throws EntityNotFoundException;
    Role update(Role role) throws EntityNotFoundException;
    void delete(long id) throws EntityNotFoundException;
    List<Role> getAll();
}
