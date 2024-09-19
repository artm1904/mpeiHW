package pr2.pr2Hw2;

public class Main {
	public static void main(String[] args) {
	WorkerProfile n1= new WorkerProfile("Семен", 32, 2222);
		WorkerProfile n2= new DriverProfile("Олег", 32, 2222, "C");
		printWorkerInfo(n1);
		printWorkerInfo(n2);
	}

	private  static void printWorkerInfo(WorkerProfile wp){
		String string = wp.profileInfo();
		System.out.println(string);
	}
}
