package pr2.pr2Hw2;

public class DriverProfile extends WorkerProfile{
	public DriverProfile(String name, int age, int salary,String drivingLicence) {
		super(name, age, salary);
		this.drivingLicence=drivingLicence;
	}

	private String drivingLicence;

	public String getDrivingLicence() {
		return drivingLicence;
	}

	public void setDrivingLicence(String drivingLicence) {
		if (drivingLicence.equals("A") || drivingLicence.equals("B") || drivingLicence.equals("C"))
			this.drivingLicence = drivingLicence;
		else{
			System.err.println("The wrong drivingLicence format!");
			this.drivingLicence = "Error";
		}
	}
	@Override
	protected String profileInfo(){
		String string = "Worker's name: " + this.getName() + ", age is:" + this.getAge() +
				", salary is:" + this.getSalary() + ", drDrivingLicence is: "+this.getDrivingLicence();
		return string;
	}
}
