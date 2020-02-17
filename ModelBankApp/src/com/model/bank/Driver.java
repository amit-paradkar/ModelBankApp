package com.model.bank;

import java.math.BigDecimal;
import java.util.Scanner;

import com.model.bank.account.Account;
import com.model.bank.transaction.Transaction;
import com.model.bank.transaction.TransactionFactory;
import com.model.bank.util.AccountConstants;
import com.model.bank.util.UserAction;

/**
 * @author paradkar_a
 *         /*****************************************************************
 *         This is the main class for Console Application. Upon execution the
 *         following menu is displayed:
 *************************************************
 *         Welcome to Awesome Bank App! Please choose action from the following:
 *         Press 1 to credit to your account Press 2 to debit from your account:
 *         Press 3 to get current Balance: Press 4 to get all transactions:
 *         Press 5 to exit app
 **************************************************
 *         User has the option to choose any option and proceed with execution
 * 
 ******************************************************************/

public class Driver {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Account myaccount = Account.getInstance();

		for (;;) {
			UserAction userAction = getUserAction(input);
			processUserAction(myaccount, userAction, input);
		}
	}

	private static void waitForEnterKeyToContinue() {
		System.out.println(AccountConstants.RETURN_TO_MAIN_MENU_MSG);
		try {
			System.in.read();
		} catch (Exception e) {
			System.out.println("Exception in waitForEnterKeyToContinue" + e.getMessage());
		}
	}

	private static void displayTotalBalanceMessage(Account account) {
		System.out.println(AccountConstants.TOTAL_BALANCE_MSG + account.getBalance().toString());
	}

	private static BigDecimal readTransactionAmount(Scanner in) {
		BigDecimal amount = BigDecimal.ZERO;
		try {
			amount = in.nextBigDecimal();
		} catch (Exception e) {
		} finally {
			if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
				System.out.println(AccountConstants.INVALID_TRANSACTION_AMOUNT_MSG);
				in.nextLine();
				amount = readTransactionAmount(in);
			}
		}
		return amount;
	}

	private static UserAction getUserAction(Scanner input) {

		String userAction = null, transactionDescription = null;
		BigDecimal transactionAmount = BigDecimal.ZERO;

		System.out.println(AccountConstants.WELCOME_MESSAGE);

		System.out.println(AccountConstants.CHOOSE_ACTIONS_MSG);
		System.out.println(AccountConstants.CREDIT_ACTION_MSG);
		System.out.println(AccountConstants.DEBIT_ACTION_MSG);
		System.out.println(AccountConstants.CURRENT_BALANCE_MSG);
		System.out.println(AccountConstants.GET_ALL_TRANSACTIONS_MSG);
		System.out.println(AccountConstants.EXIT_APP_MSG);

		userAction = input.next();
		if (userAction.equals(AccountConstants.CREDIT) || userAction.equals(AccountConstants.DEBIT)) {
			System.out.println(AccountConstants.TRANSACTION_AMOUNT_MSG);
			transactionAmount = readTransactionAmount(input);
			System.out.println(AccountConstants.TRANSACTION_DESCRIPTION_MSG);
			transactionDescription = input.next();
		}

		UserAction userAct = new UserAction(userAction);
		if (transactionAmount != null) {
			userAct.setTransactionAmount(transactionAmount);
		}

		if (transactionDescription != null) {
			userAct.setTransactionDescription(transactionDescription);
		}

		return userAct;
	}

	private static void processUserAction(Account myaccount, UserAction userAction, Scanner input) {
		TransactionFactory factory = new TransactionFactory();

		Transaction mytransaction = null;
		switch (userAction.getActionSelected()) {
		case AccountConstants.CREDIT:
			mytransaction = factory.createTransaction(userAction.getTransactionAmount(),
					userAction.getTransactionDescription(), AccountConstants.CREDIT);
			processTransaction(myaccount, mytransaction);
			break;
		case AccountConstants.DEBIT:
			mytransaction = factory.createTransaction(userAction.getTransactionAmount(),
					userAction.getTransactionDescription(), AccountConstants.DEBIT);
			processTransaction(myaccount, mytransaction);
			break;
		case AccountConstants.GET_CURRENT_BALANCE:
			displayTotalBalanceMessage(myaccount);
			waitForEnterKeyToContinue();
			break;
		case AccountConstants.GET_ALL_TRANSACTIONS:
			for (Transaction transaction : myaccount.getAllTransactions()) {
				System.out.println(transaction);
			}
			waitForEnterKeyToContinue();
			break;
		case AccountConstants.EXIT_APP:
			exitApp(input);
			break;
		default:
			System.out.println(AccountConstants.INVALID_ACTION_MSG);
			waitForEnterKeyToContinue();
		}

		return;
	}

	public static void processTransaction(Account account, Transaction transaction) {
		if (!account.execute(transaction)) {
			System.out.println("ALERT some problem with this account");
		} else {
			System.out.println("Transaction completed successfully");
		}
		displayTotalBalanceMessage(account);
		waitForEnterKeyToContinue();
	}

	public static void exitApp(Scanner input) {
		System.out.println(AccountConstants.GOOD_BYE_MSG);
		input.close();
		System.exit(0);
	}
}