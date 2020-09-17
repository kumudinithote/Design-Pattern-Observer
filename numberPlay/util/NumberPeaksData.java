package numberPlay.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class NumberPeaksData implements PersisterI, NumberPeaksResultsI {
	
	private String outputFileName;
	private double previosNumber = Double.MIN_VALUE;
	private BufferedWriter writer;
	ArrayList<String> result; 
	public void setPreviosNumber(double previosNumber) {
		this.previosNumber = previosNumber;
	}

	public NumberPeaksData(String outputFileName){
		this.outputFileName = outputFileName;
		result = new ArrayList<String>();
	}
	
	@Override
	public void store(Double d) {
		DecimalFormat formatter = new DecimalFormat("0.00");
		//System.out.println("Peak : "+formatter.format(previosNumber));
		result.add(formatter.format(d));
	}

	public double getPreviosNumber() {
		return previosNumber;
	}

	@Override
	public void writeToFile() throws IOException {
		writer = new BufferedWriter(new FileWriter(this.outputFileName));
		for(int i = 0; i < result.size(); i++){
			writer.write(result.get(i).toString().concat("\n"));
		}
	}

	@Override
	public void close() throws IOException {
		writer.close();
	}
}
