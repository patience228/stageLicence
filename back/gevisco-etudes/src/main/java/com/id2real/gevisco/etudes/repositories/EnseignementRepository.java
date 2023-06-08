package com.id2real.gevisco.etudes.repositories;

import java.util.Set;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.etudes.entities.Classe;
import com.id2real.gevisco.etudes.entities.Enseignement;
import com.id2real.gevisco.etudes.entities.Matiere;


@Repository
public interface EnseignementRepository extends JpaRepository<Enseignement, String>, QuerydslPredicateExecutor<Enseignement> {

    @Query("SELECT cc.classe FROM Enseignement cc WHERE cc.professeur.id =:professeur_id AND cc.matiere.id=:matiere_id")
    public Set<Classe> findClasseByProfesseurIDAndMatiereID(@Param("professeur_id") String professeur_id, @Param("matiere_id") String matiere_id);
    
    @Query("SELECT cc.matiere FROM Enseignement cc WHERE cc.professeur.id =:professeur_id")
    public Set<Matiere> findMatiereByProfesseurID(@Param("professeur_id") String professeur_id);
    
    

    @Query("SELECT c FROM Classe c WHERE c NOT IN (SELECT cc.classe FROM Enseignement cc WHERE cc.professeur.id  =:professeur_id)")
    public Set<Classe> findClasseNotInProfesseurID(@Param("professeur_id") String professeur_id);
    
    @Query("SELECT c FROM Matiere c WHERE c NOT IN (SELECT cc.matiere FROM Enseignement cc WHERE cc.professeur.id  =:professeur_id)")
    public Set<Matiere> findMatiereNotInProfesseurID(@Param("professeur_id") String professeur_id);
    
    
    
	// @Query("SELECT cc  FROM Enseignement cc WHERE cc.classe.id = :classe_id AND cc.professeur.id = :professeur_id")
	// public Enseignement findByClasseIDAndProfesseurID(@Param("classe_id") String classe_id, @Param("professeur_id") String professeur_id);
	 
	 @Query("SELECT cc  FROM Enseignement cc WHERE cc.matiere.id = :matiere_id AND cc.professeur.id = :professeur_id AND cc.classe.id = :classe_id " )
	 public Enseignement findByMatiereIDAndProfesseurIDAndClasseID(@Param("matiere_id") String matiere_id, @Param("professeur_id") String professeur_id, @Param("classe_id") String classe_id);
	 
	 
}
