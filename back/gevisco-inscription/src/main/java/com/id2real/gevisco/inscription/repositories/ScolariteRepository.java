package com.id2real.gevisco.inscription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.inscription.entities.Scolarite;


@Repository
public interface ScolariteRepository extends JpaRepository<Scolarite, String>, QuerydslPredicateExecutor<Scolarite>{

}
