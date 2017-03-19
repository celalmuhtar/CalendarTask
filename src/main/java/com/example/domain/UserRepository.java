package com.example.domain;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
    public User findByEmail(String email);
    public List<User> findByEnabled(int enabled);
    public User findByUserId(Long userId);
    
}