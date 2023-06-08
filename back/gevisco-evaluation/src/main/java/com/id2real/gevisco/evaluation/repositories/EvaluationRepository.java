package com.id2real.gevisco.evaluation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.evaluation.entities.Evaluation;


@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, String>, QuerydslPredicateExecutor<Evaluation> {
	
	 @Query("SELECT cc  FROM Evaluation cc WHERE cc.annee.id= :annee_id  " )
	 public List<Evaluation> findByAnnee(@Param("annee_id") String annee_id);
}
