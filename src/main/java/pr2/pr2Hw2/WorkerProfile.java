package pr2.pr2Hw2;

public class WorkerProfile {
	protected String name;
	protected int age;
	protected int salary;

	public WorkerProfile(String name, int age, int salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	protected String profileInfo(){
		String string = "Worker's name: " + this.getName() + ", age is:" + this.getAge() +
				", salary is:" + this.getSalary() ;
		return string;
	}
}
