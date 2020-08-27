package com.proy.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.proy.pma.entities.UserAccount;

@RepositoryRestResource(collectionResourceRel = "apiaccounts", path ="apiaccounts")
public interface UserAccountRepository  extends PagingAndSortingRepository<UserAccount, Long>{
	
	
}
