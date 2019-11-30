/**
 * 
 */
package com.mx.base.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.base.models.catalog.Profile;

/**
 * Repository for Profile.
 *
 */
//@Repository
public interface ProfileRepository {
//extends CrudRepository<Profile, Long> {
	
	List<Profile> findByStatus(String status);

}
