package com.id2real.gevisco.preinscription.repositpories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.preinscription.entities.Annee_scolaire;


@Repository
public interface AnneeScolaireRepository extends JpaRepository<Annee_scolaire, String>, QuerydslPredicateExecutor<Annee_scolaire>{

}
