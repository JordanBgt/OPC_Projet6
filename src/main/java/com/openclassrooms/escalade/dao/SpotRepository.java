package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entity.Spot;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to handle Spot DAO operations
 */
@Repository
public interface SpotRepository extends CrudRepository<Spot, Long>, QuerydslPredicateExecutor<Spot> {
    List<Spot> findAllByUserId(Long userId);
}
