package com.id2real.gevisco.preinscription.repositpories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.preinscription.entities.Niveau;


@Repository
public interface NiveauRepository extends JpaRepository<Niveau, String>, QuerydslPredicateExecutor<Niveau> {

}
