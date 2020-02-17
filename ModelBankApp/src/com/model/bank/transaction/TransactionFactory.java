package com.model.bank.transaction;

import java.math.BigDecimal;

import com.model.bank.util.AccountConstants;

public class TransactionFactory {
	public Transaction createTransaction(BigDecimal amount, String comment, String transactionType) {
		Transaction transaction = null;

		if (transactionType.equals(AccountConstants.CREDIT)) {
			transaction = new Credit(amount, comment);
		} else if (transactionType.equals(AccountConstants.DEBIT)) {
			transaction = new Debit(amount, comment);
		}

		return transaction;
	}
}
