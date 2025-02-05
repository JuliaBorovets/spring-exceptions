package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role readById(long id) throws EntityNotFoundException {
        Optional<Role> optional = roleRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Role with id=" + id + " does not exist!"));
    }

    @Override
    public Role update(Role role) throws EntityNotFoundException {
        Role oldRole = readById(role.getId());
        return roleRepository.save(role);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        Role role = readById(id);
        roleRepository.delete(role);
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }
}
