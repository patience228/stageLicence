package com.id2real.gevisco.preinscription.repositpories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.preinscription.entities.Preinscription;


@Repository
public interface PreinscriptionRepository extends JpaRepository<Preinscription, String>, QuerydslPredicateExecutor<Preinscription> {

	@Query("SELECT cc FROM Preinscription	cc WHERE cc.etat =:etat")
	public List<Preinscription> findByEtat(@Param("etat") int etat);
	
	
	@Query("SELECT new map (count(cc) as nbre , cc.annee as annee) FROM Preinscription cc GROUP BY cc.annee")
	public List<?> CountByAnnee();
	
	@Query("SELECT count(cc) FROM Preinscription cc WHERE cc.etat =:etat GROUP BY cc.annee")
	public long CountNewByAnnee(@Param("etat") int etat);
	    
//@Query("SELECT cc.preinscription	FROM Preinscription cc WHERE cc.etat =:etat")
//  public List<Preinscription>  findByEtat(@Param("etat") int etat);
}
