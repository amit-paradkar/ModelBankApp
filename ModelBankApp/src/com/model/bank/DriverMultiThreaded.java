package com.model.bank;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.model.bank.util.AccountConstants;
import com.model.bank.util.UserAction;

/**
 * @author paradkar_a
 *         /*****************************************************************
 **         This is the main class for Multithreaded Application. It uses
 *         ExecutorService to create a threadpool of 5 threads. Four threads
 *         executes either a Credit/Debit opeation. The fifth thread Sleeps for
 *         5 seconds and then prints the list of all transactions.
 ******************************************************************
 **/

public class DriverMultiThreaded {

	private static final int MYTHREADS = 5;

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);

		UserAction userAction1 = new UserAction(AccountConstants.CREDIT, "First Credit", new BigDecimal("100"));
		Runnable worker = new BankAppRunnable("1", userAction1);
		executor.execute(worker);

		UserAction userAction2 = new UserAction(AccountConstants.CREDIT, "Second Credit", new BigDecimal("100"));
		worker = new BankAppRunnable("2", userAction2);
		executor.execute(worker);

		UserAction userAction3 = new UserAction(AccountConstants.CREDIT, "Third Credit", new BigDecimal("100"));
		worker = new BankAppRunnable("3", userAction3);
		executor.execute(worker);

		UserAction userAction4 = new UserAction(AccountConstants.DEBIT, "First Debit", new BigDecimal("100"));
		worker = new BankAppRunnable("4", userAction4);
		executor.execute(worker);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		UserAction userAction5 = new UserAction(AccountConstants.GET_ALL_TRANSACTIONS, "", new BigDecimal("100"));
		worker = new BankAppRunnable("5", userAction5);
		executor.execute(worker);

		executor.shutdown();

		while (!executor.isTerminated()) {
		}

		System.out.println("Finished all threads\n");
	}
}