package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.StateRepository;
import com.softserve.itacademy.service.StateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {
    private StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State create(State state) {
            return stateRepository.save(state);
    }

    @Override
    public State readById(long id) throws EntityNotFoundException {
        Optional<State> optional = stateRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("State with id=" + id + " does not exist!"));
    }

    @Override
    public State update(State state) throws EntityNotFoundException {
            State oldState = readById(state.getId());
                return stateRepository.save(state);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        State state = readById(id);
        stateRepository.delete(state);
    }

    @Override
    public State getByName(String name) throws EntityNotFoundException {
        Optional<State> optional = Optional.ofNullable(stateRepository.getByName(name));
        return optional.orElseThrow(() -> new EntityNotFoundException("State with name=" + name + " does not exist!"));
    }

    @Override
    public List<State> getAll() {
        List<State> states = stateRepository.getAll();
        return states.isEmpty() ? new ArrayList<>() : states;
    }
}
