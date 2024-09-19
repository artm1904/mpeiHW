package pr2.Vector;

public class Test {
	public static void main(String[] args) {
		VectorEntity v1 = new VectorEntity(4,1,6);
		VectorEntity v2= new VectorEntity(22,6,8);

		double v = v1.scalarMultiply(v2);
		System.out.println("scalarMultiply is: "+ v);

		double[] doubles = v1.vectorMultiply(v2);
		System.out.println("vectorMultiply is: "+ doubles[0]+"i "+doubles[1]+ "j "+ doubles[2]+ "k");

		double v3 = v1.vectorGrad(v2);
		System.out.println("vectorGrad is: "+ v3);


	}
}
