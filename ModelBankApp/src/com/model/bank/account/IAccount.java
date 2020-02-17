package com.model.bank.account;

import java.math.BigDecimal;
import java.util.List;

import com.model.bank.transaction.Transaction;

public interface IAccount {
	public boolean execute(Transaction transaction);

	public BigDecimal getBalance();

	public List<Transaction> getAllTransactions();
}
