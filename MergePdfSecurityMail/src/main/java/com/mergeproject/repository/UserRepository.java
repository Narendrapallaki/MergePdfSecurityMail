package com.mergeproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mergeproject.entity.UserDetails;


@Repository
public interface UserRepository extends CrudRepository<UserDetails, Integer>{

}
