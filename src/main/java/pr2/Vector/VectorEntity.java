package pr2.Vector;

import java.util.Vector;

public class VectorEntity {
	private double x;
	private double y;
	private double z;

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public VectorEntity(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double length() {
		double a = this.x;
		double b = this.y;
		double c = this.z;
		double resalt = Math.sqrt((a * a) + (b * b) + (c * c));
		return resalt;
	}

	public double scalarMultiply(VectorEntity secondVect) {
		double scalarM = 0.0;
		scalarM = (this.x * secondVect.getX()) + (this.y * secondVect.getY()) + (this.z * secondVect.getZ());
		return scalarM;
	}

	public double[] vectorMultiply(VectorEntity secondVect) {
		double vectorMi = 0.0;
		double vectorMj = 0.0;
		double vectorMk = 0.0;

		vectorMi = (this.y * secondVect.getZ()) - (this.z * secondVect.getY());
		vectorMj = (this.z * secondVect.getX()) - (this.x * secondVect.getZ());
		vectorMk = (this.x * secondVect.getY()) - (this.y * secondVect.getX());

		double ijk[] = {vectorMi, vectorMj, vectorMk};
		return ijk;
	}

	public double vectorGrad(VectorEntity secondVect) {
		double v = scalarMultiply(secondVect);
		double length1 = this.length();
		double length2 = secondVect.length();
		double result = (v) / (length1 * length2);
		return result;
	}

	public VectorEntity addVectors(VectorEntity secondVect) {
		VectorEntity v = new VectorEntity(0, 0, 0);
		v.setX(this.x + secondVect.getX());
		v.setX(this.y + secondVect.getY());
		v.setX(this.z + secondVect.getZ());
		return v;
	}

	public VectorEntity subVectors(VectorEntity secondVect) {
		VectorEntity v = new VectorEntity(0, 0, 0);
		v.setX(this.x - secondVect.getX());
		v.setX(this.y - secondVect.getY());
		v.setX(this.z - secondVect.getZ());
		return v;
	}

}
