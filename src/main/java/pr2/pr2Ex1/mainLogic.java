package pr2.pr2Ex1;

public class mainLogic {
	public static void main(String[] args) {
		BankAccount account = new BankAccount(1000);
		System.out.println("Аккаунт создан " + account.checkMoney());
		account.putMoney(5000);
		System.out.println("После пополнения " + account.checkMoney());
		System.out.println("В долларах " + account.checkInDollars());
		System.out.println("В евро " + account.checkInDEuros());
		int money = account.withdrawMoney();
		System.out.println("После снятия " + account.checkMoney());
		System.out.println("Количество снятых денег " + money);
	}

}
