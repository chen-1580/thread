package com.ckx.thread.unsynch;

public class UnsynchBankTest {
	public static final int NACCOUNTS = 100;
	public static final double INITAL_BALANCE = 1000;
	
	public static void main(String[] args) {
		//构造100家银行
		Bank b = new Bank(NACCOUNTS,INITAL_BALANCE);
		//在这100家银行间洗钱
		int i ;
		for (i = 0; i < NACCOUNTS; i++) {
			TransferRunnable r = new TransferRunnable(b, i, INITAL_BALANCE);
			new Thread(r).start();
		}
	}
}
