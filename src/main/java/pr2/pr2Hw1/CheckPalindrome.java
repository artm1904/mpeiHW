package pr2.pr2Hw1;

public class CheckPalindrome {

	private int number;

	public CheckPalindrome(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isPalindrome() {

		if (this.number<0)
			return false;
		int check = 0;
		int n = this.number;
		while (n > 0) {
			check = check * 10 + n % 10;
			n /= 10;
		}
		return (this.number == check);
	}

	public int sumOfNumbers() {
		int sum = 0;
		int n = this.number;
		while (n > 0) {
			sum += n % 10;
			n /= 10;
		}
		return sum;
	}

}
