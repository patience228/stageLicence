package com.id2real.gevisco.utilisateurs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.utilisateurs.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, String>, QuerydslPredicateExecutor<User>{

}
