package com.ckx.thread.unsynch;

public class TransferRunnable implements Runnable{
	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 5000;

	public TransferRunnable(Bank b, int from, double max) {
		bank = b;
		fromAccount = from;
		maxAmount = max;
	}

	public void run() {
		while (true) {
			try {
				int toAccount = (int) (bank.size() * Math.random());
				double amount = maxAmount * Math.random();
				bank.transfer(fromAccount, toAccount, amount);
				//睡眠DELAY * Math.random()毫秒，放大线程竞争概率
				Thread.sleep((int) (DELAY * Math.random()));
			} catch (InterruptedException e) {
			}
		}
	}
}
