package ie.cit.patrickrobertson.BMI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;

public class ResultsManager {
	AssetManager am;
	ArrayList<Result> results;
	FileWriter BMIresults;
	BufferedReader reader;
	private Context context;

	ResultsManager(Context context) {
		this.context = context;
		results = convertFileToResults();
	}

	ResultsManager(Context context, String input) {
		this.context = context;
		results = convertFileToResults();
		writeToFile(input);

	}

	public ArrayList<Result> getResults() {
		return results;
	}

	public void setResults(ArrayList<Result> results) {
		this.results = results;
	}

	private void writeToFile(String input) {
		//convert existing results back into Strings and add to new result to keep newest to top
		for(Result x : results){
			input = input.concat(x.toString());
		}

		//add total string to file
		try {
			FileOutputStream fos = context.openFileOutput("BMIresults.bmic",context.MODE_PRIVATE);
			fos.write(input.getBytes());
			fos.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}

	}

	private ArrayList<Result> convertFileToResults() {
		ArrayList<Result> lines = new ArrayList<Result>();
		try {
			InputStreamReader iSR = new InputStreamReader(context.openFileInput("BMIresults.bmic"));
			BufferedReader reader = new BufferedReader(iSR);
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(mapLinetoResult(line));
			}
			reader.close();
		} catch (FileNotFoundException fNFE) {
			System.out.print(fNFE.getMessage());
		} catch (IOException iOE) {
			System.out.print(iOE.getMessage());
		}
		return lines;
	}

	private Result mapLinetoResult(String line) {

		String[] parts = line.split("-");
		try {
			Result result = new Result(Double.parseDouble(parts[1]),
					Double.parseDouble(parts[2]),parts[0]);
			return result;
		} catch (NumberFormatException nFE) {
			nFE.printStackTrace();
		}
		return null;
	}

	public void deleteHistory(){
		String blank = "";
		//add total string to file
		try {
			FileOutputStream fos = context.openFileOutput("BMIresults.bmic",context.MODE_PRIVATE);
			fos.write(blank.getBytes());
			fos.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
	}
	
		
	
	
	
}
