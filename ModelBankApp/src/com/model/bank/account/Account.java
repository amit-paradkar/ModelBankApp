package com.model.bank.account;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.model.bank.transaction.Credit;
import com.model.bank.transaction.Debit;
import com.model.bank.transaction.Transaction;

public class Account implements IAccount {

	private transient List<Transaction> transactions = new CopyOnWriteArrayList<Transaction>();
	private transient BigDecimal balance = BigDecimal.ZERO;

	private Account() {
	}

	private static class AccountSingleton {
		private static final Account INSTANCE = new Account();
	}

	public static Account getInstance() {
		return AccountSingleton.INSTANCE;
	}

	@Override
	public synchronized BigDecimal getBalance() {

		return this.balance;
	}

	@Override
	public synchronized List<Transaction> getAllTransactions() {
		return this.transactions;
	}

	public synchronized boolean execute(Transaction transaction) {
		transactions.add(transaction);
		BigDecimal newBalance = transaction.calculateNewBalance(this.balance);
		if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
			return false;
		}
		this.balance = newBalance;
		if (!reconcile())
			return false;
		else
			return true;
	}

	private boolean reconcile() {

		BigDecimal balance = getPostTransactionBalance();

		if (!balance.equals(this.balance))
			return false;

		return true;
	}

	private BigDecimal getPostTransactionBalance() {
		BigDecimal totalDebits = BigDecimal.ZERO;
		BigDecimal totalCredits = BigDecimal.ZERO;

		for (Transaction transaction : transactions) {
			if (transaction instanceof Debit) {
				totalDebits = totalDebits.add(transaction.getAmount());
			}
			if (transaction instanceof Credit) {
				totalCredits = totalCredits.add(transaction.getAmount());
			}
		}

		BigDecimal balance = totalCredits.subtract(totalDebits);

		return balance;
	}
}