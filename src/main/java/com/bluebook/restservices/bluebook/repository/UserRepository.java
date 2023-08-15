package com.bluebook.restservices.bluebook.repository;

import com.bluebook.restservices.bluebook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	User findByUserName(String username);
}
