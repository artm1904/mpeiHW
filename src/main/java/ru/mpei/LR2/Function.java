package ru.mpei.LR2;


/**
 *  В данном классе задаем вид функиий расчета каждого из агентов
 */
public class Function {


	public double doCalculation(String name, double x) {

		double result = 0;

		switch (name) {
			case "first":
				result = (-0.5 * (Math.pow(x, 2)) - 4);
				break;
			case "second":
				double power = -0.1 * x;
				result = ((Math.pow(2, power)));
				break;
			case "third":
				result = Math.cos(x);
				break;
			default:
				break;
		}

		return result;
	}
}
