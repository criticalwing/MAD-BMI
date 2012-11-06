package ie.cit.patrickrobertson.BMI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Result {

	private double height;
	private double weight;
	private int BMI;
	private String date;
	private int backColour;

	public Result(double height, double weight) {
		super();
		this.height = height;
		this.weight = weight;
		setDate(getTodaysDate());
		setBMI(calculateBMI(height, weight));
		setBackColour(calculateBackColour(BMI));
	}
	
	public Result(double height, double weight, String date) {
		super();
		this.height = height;
		this.weight = weight;
		setDate(date);
		setBMI(calculateBMI(height, weight));
		setBackColour(calculateBackColour(BMI));
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getBMI() {
		return BMI;
	}

	public void setBMI(int bMI) {
		BMI = bMI;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getBackColour() {
		return backColour;
	}

	public void setBackColour(int backColour) {
		this.backColour = backColour;
	}

	// colour is return as int so that it can be processed by the display intent
	// this keeps the Result Class coherent
	private int calculateBackColour(int BMI) {

		if (BMI < 19) {
			// TODO need white result
			return 0;
		} else if (BMI < 26) {
			return 1;
		} else if (BMI < 30) {
			return 2;
		} else if (BMI < 40) {
			return 3;
		} else {
			return 4;
		}

	}

	private int calculateBMI(double height, double weight) {

		double BMI = (weight / Math.pow((height / 100), 2));

		int output = (int) Math.round(BMI);

		return output;

	}

	private String getTodaysDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	@Override
	public String toString() {
		return date + "-" + height + "-" + weight + "\n";
	}

	
	
	
	
}
