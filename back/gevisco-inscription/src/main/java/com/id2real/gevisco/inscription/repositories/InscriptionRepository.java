package com.id2real.gevisco.inscription.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.inscription.entities.Inscription;



@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, String>, QuerydslPredicateExecutor<Inscription> {
	
	@Query("SELECT cc,p,s,n,an FROM Eleve cc,Inscription p,Annee_scolaire an,Niveau n, Scolarite s "
			+ "WHERE cc.id=p.eleve AND p.annee=an.id AND n.id=p.niveau AND s.annee=an.id AND s.niveau=n.id ")
	public List<Inscription> findEleveBYInscription();
	
	@Query("SELECT new map (count(cc) as nbre , cc.annee as annee ,cc.montant_verse as mont)  FROM Inscription cc GROUP BY cc.annee")
	public List<?>  CountByAnnee();
}
