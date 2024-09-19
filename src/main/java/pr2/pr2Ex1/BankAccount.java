package pr2.pr2Ex1;

public class BankAccount {
	private int money;

	public BankAccount(int money) {
		this.money = money;
	}

	public int checkMoney() {
		return money;
	}

	public void putMoney(int quantity) {
		this.money += quantity;
	}


	public int withdrawMoney() {
		int cash = this.money;
		this.money = 0;
		return cash;
	}

	public double checkInDollars() {
		double check = this.money/ExchangeRates.getDollarRate();
		return check;
	}

	public double checkInDEuros() {
		double check = this.money/ExchangeRates.getEuroRate();
		return check;
	}
}

