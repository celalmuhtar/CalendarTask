package com.example.domain;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
	@Query("select a.role from UserRole a, User b where b.username=?1 and a.userid=b.userId")
    public List<String> findRoleByUsername(String username);
	
	@Query("select a.role from UserRole a, User b where b.email=?1 and a.userid=b.userId")
    public List<String> findRoleByEmail(String email);
}