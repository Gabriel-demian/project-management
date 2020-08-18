package com.proy.pma.dao;

import org.springframework.data.repository.CrudRepository;
import com.proy.pma.entities.UserAccount;

public interface UserAccountRepository  extends CrudRepository<UserAccount, Long>{
	
	
}
