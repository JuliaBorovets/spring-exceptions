package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import com.softserve.itacademy.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (!user.getPassword().isEmpty()) {
            return userRepository.save(user);
        }
        throw new NullEntityReferenceException("User can't be 'null'!");
    }

    @Override
    public User readById(long id) throws EntityNotFoundException {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("User with id=" + id + " does not exist!"));
    }

    @Override
    public User update(User user) throws EntityNotFoundException {
        if (!user.getPassword().isEmpty()) {

            Optional.ofNullable(userRepository.getUserByEmail(user.getEmail()))
                    .orElseThrow(() -> new EntityNotFoundException("User with email=" + user.getEmail() + " does not exist!"));

            return userRepository.save(user);
        }
        throw new NullEntityReferenceException("User can't be 'null'!");
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        User user = readById(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

}
