package com.model.bank.util;

public class AccountConstants {

	public static final String CREDIT = "1";
	public static final String DEBIT = "2";
	public static final String GET_CURRENT_BALANCE = "3";
	public static final String GET_ALL_TRANSACTIONS = "4";
	public static final String EXIT_APP = "5";

	public static final String TRANSACTION_ID_UPPER_LIMIT = "1000";
	public static final String TRANSACTION_ID_LOWER_LIMIT = "100";

	public static final String WELCOME_MESSAGE = "Welcome to Awesome Bank App!";
	public static final String CHOOSE_ACTIONS_MSG = "Please choose action from the following:";

	public static final String CREDIT_ACTION_MSG = "Press " + AccountConstants.CREDIT + " to  credit to your account";
	public static final String DEBIT_ACTION_MSG = "Press " + AccountConstants.DEBIT + " to debit from your account:";
	public static final String CURRENT_BALANCE_MSG = "Press " + AccountConstants.GET_CURRENT_BALANCE
			+ " to get current Balance:";
	public static final String GET_ALL_TRANSACTIONS_MSG = "Press " + AccountConstants.GET_ALL_TRANSACTIONS
			+ " to get all transactions:";
	public static final String EXIT_APP_MSG = "Press " + AccountConstants.EXIT_APP + " to exit app";
	public static final String INVALID_ACTION_MSG = "Incorrect option selected";
	public static final String INVALID_TRANSACTION_AMOUNT_MSG = "Transaction amount must be greater than zero. Please enter valid transaction amount";

	public static final String TRANSACTION_AMOUNT_MSG = "Enter the transaction Amount";
	public static final String TRANSACTION_DESCRIPTION_MSG = "Enter the transaction description";

	public static final String TOTAL_BALANCE_MSG = "Total Balance: ";

	public static final String RETURN_TO_MAIN_MENU_MSG = "Please enter to go back to main menu";
	public static final String GOOD_BYE_MSG = "Thank you for using Awesome Bank App. Good bye!!";
}