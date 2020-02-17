package com.model.bank;
import com.model.bank.account.Account;
import com.model.bank.transaction.Transaction;
import com.model.bank.transaction.TransactionFactory;
import com.model.bank.util.AccountConstants;
import com.model.bank.util.UserAction;

public class BankAppRunnable implements Runnable {
	
	private final String threadId;
	private Account account;
	private UserAction userAction;

	BankAppRunnable(String id, UserAction userAction) {
		this.threadId = id;
		this.userAction = userAction;
		this.account = Account.getInstance();
	}
	
	@Override
	public void run() {
						
		initiate();
		
		displayTotalBalanceMessage();
	}
	
	public void initiate() {		    		
		processUserAction(userAction);
	}
		
	private void displayTotalBalanceMessage() {
		System.out.println("Thread Id: " + this.threadId + " " + AccountConstants.TOTAL_BALANCE_MSG + account.getBalance().toString());
	}
	
	private void processUserAction(UserAction userAction) {
		TransactionFactory factory = new TransactionFactory();
		Transaction mytransaction = null;
        switch (userAction.getActionSelected()) {
	        case AccountConstants.CREDIT:
	    		mytransaction = factory.createTransaction(
	    				userAction.getTransactionAmount(),
	    				userAction.getTransactionDescription(),  
	    				AccountConstants.CREDIT);
	    		System.out.println("Processing Credit Transaction: " + mytransaction);
	    		processTransaction(account,mytransaction);
	    		break;
	        case AccountConstants.DEBIT:
	    		mytransaction = factory.createTransaction(
	    				userAction.getTransactionAmount(),
	    				userAction.getTransactionDescription(), 
	    				AccountConstants.DEBIT);
	    		System.out.println("Processing Debit Transaction: " + mytransaction);
	    		processTransaction(account,mytransaction);
	    		break;
	        case AccountConstants.GET_ALL_TRANSACTIONS:
	        	for (Transaction transaction : account.getAllTransactions()) {
	    		    System.out.println(transaction);
	    		}
	           	break;
	        default:
	        	System.out.println(AccountConstants.INVALID_ACTION_MSG);
        }
	}
	
	private void processTransaction(Account account, Transaction transaction) {
		if(!account.execute(transaction)) {
			System.out.println("Thread Id: " + this.threadId + " ALERT some problem with this account");
		} else {
			System.out.println("Thread Id: " + this.threadId + " Transaction completed successfully");
		}
	}
}