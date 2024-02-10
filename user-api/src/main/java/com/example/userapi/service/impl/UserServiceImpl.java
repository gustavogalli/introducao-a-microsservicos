package com.example.userapi.service.impl;

import com.example.userapi.domain.User;
import com.example.userapi.repository.UserRepository;
import com.example.userapi.service.UserService;
import com.example.userapi.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
