package com.nt.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nt.Entity.Accounts;
import com.nt.Repo.Repo;

@Service
public class AccountService {

	@Autowired
	public Repo repo;

	public List<Accounts> getAccounts() {
		return repo.findAll();
	}

	public Accounts setAccount(Accounts account) {
		Accounts saved = null;
		try {
			Optional<Accounts> existingAccount = repo.findByAccountHolderNameIgnoreCase(account.getAccountHolderName());

			if (existingAccount.isPresent()) {
				throw new IllegalArgumentException(
						"Account holder name already exists: " + account.getAccountHolderName());
			}

			saved = repo.save(account);

		} catch (IllegalArgumentException e) {
			throw e; // Re-throw specific business exception
		} catch (Exception e) {
			throw new RuntimeException("Error saving account: " + e.getMessage(), e);
		}
		return saved;
	}

	public Accounts updateAccount(int id, Accounts updatedAccount) {
		try {
			Optional<Accounts> optional = repo.findById(id);
			if (optional.isEmpty()) {
				throw new IllegalArgumentException("Account with ID " + id + " not found.");
			}

			Accounts existing = optional.get();

			// Check for duplicate account holder name (case-insensitive)
			Optional<Accounts> duplicate = repo
					.findByAccountHolderNameIgnoreCase(updatedAccount.getAccountHolderName());
			Optional<Accounts> duplicate1 = repo.findByAccountId(updatedAccount.getAccountId());
			if (duplicate.isPresent() && duplicate1.get().getAccountId() != id) {
				throw new IllegalArgumentException("Account holder name already taken by another account.");
			}
			// Update fields

			existing.setAccountHolderName(updatedAccount.getAccountHolderName());
			existing.setBalance(updatedAccount.getBalance());

			return repo.save(existing);

		} catch (IllegalArgumentException e) {
			throw e; // Business logic exception
		} catch (Exception e) {
			throw new RuntimeException("Error while updating account: " + e.getMessage(), e);
		}
	}

	public Accounts deleteAccount(int id) {

		try {
			Optional<Accounts> existingAccount = repo.findById(id);

			if (!existingAccount.isPresent()) {
				throw new IllegalArgumentException("Account with ID " + id + " does not exist.");
			}
			Accounts deletedAccount = existingAccount.get();
			repo.deleteById(id);
			return deletedAccount;

		} catch (IllegalArgumentException e) {
			throw e; // Re-throw specific business exception
		} catch (Exception e) {
			throw new RuntimeException("Error saving account: " + e.getMessage(), e);
		}

	}

	public Accounts updateAccountBalance(int id, Accounts updatedAccount) {
		try {
			// Your logic to update the account balance, for example:
			Optional<Accounts> existing = repo.findById(id);
			if (!existing.isPresent()) {
				throw new IllegalArgumentException("Account with ID " + id + " not found.");
			}

			Accounts account = existing.get();
			account.setBalance(updatedAccount.getBalance()); // Assuming getter/setter present
			return repo.save(account);

		} catch (IllegalArgumentException e) {
			throw e; // Re-throw specific business exception
		} catch (Exception e) {
			throw new RuntimeException("Error saving account: " + e.getMessage(), e);
		}
	}

	/*
	 * public Optional<Accounts> accountStatus(int id) { Optional<Accounts> existing
	 * = repo.findById(id);
	 * 
	 * if (!existing.isPresent()) { throw new
	 * IllegalArgumentException("Account with ID " + id + " not found."); } String
	 * status = existing.get().getStatus(); System.out.println("Account status: " +
	 * status);
	 * 
	 * // No update is being made, so no need to save return existing; }
	 */
	/*-----------------------------------------------------------------*/
	/*
	 * public String accountStatus(int id) { return repo.findById(id) .map(account
	 * -> { System.out.println("Account status: " + account.getStatus()); return
	 * account.getStatus(); }) .orElseThrow(() -> new
	 * IllegalArgumentException("Account with ID " + id + " not found.")); }
	 */
	public String accountStatus(int id) {
	    Optional<Accounts> optionalAccount = repo.findById(id);

	    if (optionalAccount.isPresent()) {
	        Accounts account = optionalAccount.get();
	        System.out.println("Account status: " + account.getStatus());
	        return account.getStatus();
	    } else {
	        throw new IllegalArgumentException("Account with ID " + id + " not found.");
	    }
	}


}
