package pr2.pr2Hw1;

public class Test {
	public static void main(String[] args) {
		checkNum(1234321);
	}

	static private void  checkNum (int n){
		CheckPalindrome n1 = new CheckPalindrome(n);
		System.out.println("getNumber: "+ n1.getNumber());
		System.out.println("isPalindrome: "+ n1.isPalindrome());
		System.out.println("sumOfNumbers: "+ n1.sumOfNumbers());
		System.out.println("-----------------------------");
	}
}

