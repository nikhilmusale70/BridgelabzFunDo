package com.bridgelabz.fundonotes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundonotes.dto.UserDto;
import com.bridgelabz.fundonotes.entity.User;

@Repository
public interface RepositoryLayer extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
