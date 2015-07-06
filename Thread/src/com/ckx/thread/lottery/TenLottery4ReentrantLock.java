package com.ckx.thread.lottery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TenLottery4ReentrantLock {
	private final static Lock lotteryLock = new ReentrantLock();
	
	//奖品对象
	public Lottery[] getLotteryList(){
		return Lottery.getLotteries(25);
	}
	
 	//抽奖代码块
	public String getLottery(Lottery[] lt){
		List<Double> orignalRates = new ArrayList<>();
		Lottery[] lotteries = lt;
		for (int i = 0; i < lotteries.length; i++) {
			orignalRates.add(lotteries[i].getProbability());
		};
		int index = LotteryUtil.lottery(orignalRates);
		return lotteries[index].getPrizeName();
	}
	
	//减库存
	public static void main(String[] args) {
		final TenLottery4ReentrantLock tl = new TenLottery4ReentrantLock();
		final Lottery[] lt = tl.getLotteryList();
		for (int i = 0; i < 10; i++) {
			tl.getTenLottery(tl, lt);
		}
	}
	
	private final void getTenLottery(final TenLottery4ReentrantLock tl, final Lottery[] lt) {
		Thread t = new Thread() {
			public void run() {
				lotteryLock.lock();
				try {
					for (int i = 0; i < 10; i++) {
						String prizeName = tl.getLottery(lt);
						System.out.println(prizeName);
					}
				} catch (Exception e) {
				}finally{
					lotteryLock.unlock();
					System.out.println("一次10连抽抽完了");
				}
			};
		};
		t.start();
	}
}
