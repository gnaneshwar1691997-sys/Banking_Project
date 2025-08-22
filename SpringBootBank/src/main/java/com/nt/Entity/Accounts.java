package com.nt.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accounts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer accountId;

	@Column(name = "account_holder_name", nullable = false, length = 100)
	private String accountHolderName;

	@Column(name = "balance", nullable = false)
	private Double balance;

	@Column(name = "status", nullable = false, length = 10)
	private String status;
}

/*
 * package com.nt.Entity;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.Id;
 * 
 * @Entity public class Accounts {
 * 
 * @Id private int accountId; private String accountHolderName; private double
 * balance; private String status;
 * 
 * public String getStatus() { return status; }
 * 
 * public void setStatus(String status) { this.status = status; }
 * 
 * // ✅ Add Getters and Setters public int getAccountId() { return accountId; }
 * 
 * public void setAccountId(int accountId) { this.accountId = accountId; }
 * 
 * public int getAccountId() { return accountId; }
 * 
 * public String getAccountHolderName() { return accountHolderName; }
 * 
 * public void setAccountHolderName(String accountHolderName) {
 * this.accountHolderName = accountHolderName; }
 * 
 * public double getBalance() { return balance; }
 * 
 * public void setBalance(double balance) { this.balance = balance; }
 * 
 * @Override public String toString() { return "Accounts [accountId=" +
 * accountId + ", accountHolderName=" + accountHolderName + ", balance=" +
 * balance + ", status=" + status + "]"; }
 * 
 * // ✅ Optional: toString() for logging
 * 
 * 
 * }
 */