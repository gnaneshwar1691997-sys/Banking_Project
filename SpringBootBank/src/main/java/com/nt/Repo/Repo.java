package com.nt.Repo;

import java.util.Optional;;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.Entity.Accounts;

@Repository
public interface Repo extends JpaRepository<Accounts, Integer>{

//	Optional<Accounts> existsByAccountHolderNameIgnoreCase(String accountHolderName);
	//Optional<Accounts> existsByAccountHolderNameIgnoreCase(String accountHolderName);
	Optional<Accounts> findByAccountHolderNameIgnoreCase(String name);

	Optional<Accounts> findByAccountId(int accountId);
	Optional<Accounts> findByBalance(int balance);
	Optional
	

    }


