package com.id2real.gevisco.etudes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.etudes.entities.Classe;


@Repository
public interface ClasseRepository extends JpaRepository<Classe, String>, QuerydslPredicateExecutor<Classe> {

}
