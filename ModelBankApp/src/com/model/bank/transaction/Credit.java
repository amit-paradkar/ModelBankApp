package com.model.bank.transaction;

import java.math.BigDecimal;

public class Credit extends Transaction {

	public Credit(BigDecimal amount, String comment) {
		super(amount, comment);
	}

	@Override
	public BigDecimal calculateNewBalance(BigDecimal currentBalance) {

		BigDecimal newBalance = currentBalance.add(getAmount());
		return newBalance;
	}

	@Override
	public String toString() {
		return String.format("|" + this.transactionId.toString() + " |  Credit" + " |" + this.transactionDate + " |"
				+ this.transactionComment + " |" + this.tranactionAmount.toString() + " |");
	}

}
