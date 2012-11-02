package ie.cit.patrickrobertson.BMI;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{

	double height;
	double weight;
	int BMI;
	boolean complete;

	public Person(int height, int weight) {
		super();
		this.height = height;
		this.weight = weight;
		setBMI(calculateBMI(height,weight));
	}

	public Person(String sHeight, String sWeight, boolean type) {
		if(!type){
			this.height = convertHeightToMetric(sHeight);
			this.weight = convertWeightToMetric(sWeight);
			setBMI(calculateBMI(height,weight));
			complete = true;
		}else if(validateDouble(sHeight)&&validateDouble(sWeight)){
			this.height = Double.parseDouble(sHeight);
			this.weight = Double.parseDouble(sWeight);
			setBMI(calculateBMI(height,weight));
			complete = true;
		}else{
			complete = false;
		}	

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

	private double convertHeightToMetric(String change) {

		String inches = "0";
		// take out the feet portion of the height
		String feet = change.substring(0, (change.indexOf("'")));
		
		// check if height is a round foot i.e. not inches
		if (!(change.indexOf("'") == change.length())) {
			inches = change.substring((change.indexOf("'") + 1),
					change.length()-1);
		}
		
		//add it all together and convert to cm
		if (validateDouble(feet) && validateDouble(inches)) {
			return ((Double.parseDouble(feet) * 12)
					+ Integer.parseInt(inches))*2.54;
		} else {
			return 0;
		}

	}

	private double convertWeightToMetric(String change){

		if(validateDouble(change)){
			return (Double.parseDouble(change))*0.453592;
		} else{
			return 0;
		}

	}
	
	private boolean validateDouble(String test) {
		try {
			Double.parseDouble(test);
		} catch (NumberFormatException nFE) {
			nFE.getMessage();
			return false;
		}
		return true;
	}

	private int calculateBMI(double height, double weight){
		
		double BMI = (weight/(height/100))/1.75;
		
		int output = (int)Math.round(BMI);
		
		return output;

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
	
}
