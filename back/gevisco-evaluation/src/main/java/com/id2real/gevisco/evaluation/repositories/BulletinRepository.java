package com.id2real.gevisco.evaluation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.evaluation.entities.Bulletin;

@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, String>, QuerydslPredicateExecutor<Bulletin> {

}
