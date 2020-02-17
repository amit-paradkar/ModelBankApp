package com.model.bank.util;

import java.math.BigDecimal;

public class UserAction {

	private String actionSelected;
	private String transactionDescription;
	private BigDecimal transactionAmount;

	public UserAction(String action) {
		this.actionSelected = action;
	}

	public UserAction(String action, String description, BigDecimal amount) {
		this.actionSelected = action;
		this.transactionDescription = description;
		this.transactionAmount = amount;
	}

	public String getActionSelected() {
		return actionSelected;
	}

	public void setActionSelected(String actionSelected) {
		this.actionSelected = actionSelected;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
}
