package com.id2real.gevisco.preinscription.repositpories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.preinscription.entities.Eleve;




@Repository
public interface EleveRepository extends JpaRepository<Eleve, String>, QuerydslPredicateExecutor<Eleve>{

	@Query("SELECT cc FROM Eleve	cc WHERE cc.id IN (SELECT p.eleve FROM Inscription p )")
	public List<Eleve> findEleveInInscription();

	

}
