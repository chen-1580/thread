package com.ckx.thread.lottery;

public class Lottery {
	private String prizeName;
	private double probability;
	private int stock;
	
	public Lottery() {
		super();
	}
	
	public Lottery(String prizeName, double probability, int stock) {
		super();
		this.prizeName = prizeName;
		this.probability = probability;
		this.stock = stock;
	}

	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		return "Lottery [prizeName=" + prizeName + ", probability="
				+ probability + ", stock=" + stock + "]";
	}

	public static Lottery[] getLotteries(int counts){
		Lottery[] lotteries = new Lottery[counts];
		for (int i = 0; i < counts; i++) {
			lotteries[i] = new Lottery("小辛" + i , Math.random(), (int)(Math.random()*100));
		}
		return lotteries;
	}
	public static void main(String[] args) {
		Lottery[] ls = getLotteries(5);
		for (int i = 0; i < ls.length; i++) {
			System.out.println(ls[i].toString());
		};
	}
}
