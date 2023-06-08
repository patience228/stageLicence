package com.id2real.gevisco.etudes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.etudes.dtos.AffectationDTO;
import com.id2real.gevisco.etudes.entities.Affectation;



@Repository
public interface AffectationRepository extends JpaRepository<Affectation, String>, QuerydslPredicateExecutor<Affectation> {
	@Query("SELECT cc,a,an,c FROM Eleve cc,Annee_scolaire an, Classe c,Affectation a "
			+ "WHERE a.eleve=cc.id AND a.annee=an.id AND a.classe=c.id ")
	public List<Affectation> findEleveBYAffectation();
	
	@Query("SELECT count(cc) FROM Affectation cc WHERE cc.classe.id =:classe_id GROUP BY cc.annee")
	public long CountEleveByClasseAndAnnee(@Param("classe_id") String classe_id);
	
	//effectif d'une clase par annee
	@Query("SELECT new map (count(cc) as nbre , cc.annee as annee) FROM Affectation cc WHERE cc.classe.id =:classe_id GROUP BY cc.annee")
	public List<?>  EleveByClasseAndAnnee(@Param("classe_id") String classe_id);
	
	//liste par annee et classe
	@Query("SELECT cc FROM Affectation cc WHERE cc.classe.id =:classe_id AND cc.annee.id=:annee_id")
	public List<Affectation>  findAffectationByClasseAndAnnee(@Param("classe_id") String classe_id, @Param("annee_id") String annee_id);
	
	//classe par eleve et annee 
		@Query("SELECT cc FROM Affectation cc WHERE cc.annee.id=:annee_id AND cc.eleve.id=:eleve_id")
		public List<Affectation>  findAffectationByAnnee( @Param("annee_id") String annee_id, @Param("eleve_id") String eleve_id);
	
	@Query("SELECT count(cc) FROM Affectation cc WHERE cc.classe.id =:classe_id AND cc.annee.id=:annee_id")
	public long CountEleveByClasse(@Param("classe_id") String classe_id, @Param("annee_id") String annee_id);
	    
}
