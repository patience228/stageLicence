package com.id2real.gevisco.evaluation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.id2real.gevisco.evaluation.entities.Detail_bulletin;


@Repository
public interface DetailBulletinRepository extends JpaRepository<Detail_bulletin, String>, QuerydslPredicateExecutor<Detail_bulletin>{

}
