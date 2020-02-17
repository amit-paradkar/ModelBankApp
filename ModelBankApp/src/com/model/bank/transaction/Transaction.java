package com.model.bank.transaction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Random;

import com.model.bank.util.AccountConstants;

public abstract class Transaction {
	protected BigInteger transactionId;
	protected LocalDateTime transactionDate;
	protected BigDecimal tranactionAmount;
	protected String transactionComment;
	protected String transactionType;

	public Transaction (BigDecimal amount, String comment ) {
		this.transactionId = generateTransactionId();
		this.transactionDate = LocalDateTime.now();
		this.tranactionAmount = amount;
		this.transactionComment = comment;
	}
	
	public BigDecimal getAmount() {
		return this.tranactionAmount;
	}

	public abstract BigDecimal calculateNewBalance(BigDecimal currentBalance);
	
	private BigInteger generateTransactionId() {
		BigInteger upperLimit = new BigInteger(AccountConstants.TRANSACTION_ID_UPPER_LIMIT);
	    BigInteger lowerLimit = new BigInteger(AccountConstants.TRANSACTION_ID_LOWER_LIMIT);
	    
	    BigInteger limitDifference = upperLimit.subtract(lowerLimit);
	      Random randNum = new Random();
	      BigInteger transactionId = new BigInteger(upperLimit.bitLength(), randNum);
	      if (transactionId.compareTo(lowerLimit) < 0)
	    	  transactionId = transactionId.add(lowerLimit);
	      if (transactionId.compareTo(limitDifference) >= 0)
	    	  transactionId = transactionId.mod(limitDifference).add(lowerLimit);
	         	      
	      return transactionId;
	}
}