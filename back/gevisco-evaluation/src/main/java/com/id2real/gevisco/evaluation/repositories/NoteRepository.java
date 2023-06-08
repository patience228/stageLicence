package com.id2real.gevisco.evaluation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.evaluation.entities.Note;


@Repository
public interface NoteRepository extends JpaRepository<Note, String>, QuerydslPredicateExecutor<Note> {
	
	@Query("SELECT new map ((cc.notes*cc.matiere.coefficient) as coef , cc.matiere as mat, cc.evaluation as eval, cc.eleve as eleve) FROM Note cc WHERE cc.evaluation.libelle =:libelle")
	public List<?>  NoteByLibelle(@Param("libelle") String libelle);
	
}
