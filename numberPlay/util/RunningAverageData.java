package numberPlay.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RunningAverageData implements PersisterI, RunningAverageResultsI {
	
	private String outputFileName;
	private int windowSize;
	private BufferedWriter writer;
	public int getWindowSize() {
		return windowSize;
	}

	public Queue<Double> getQueue() {
		return queue;
	}

	private Queue<Double> queue;
	private ArrayList<String> result;
	
	public RunningAverageData(String outputFileName, String windowSize){
		this.outputFileName = outputFileName;
		this.windowSize = Integer.parseInt(windowSize);
		queue = new LinkedList<Double>();
		result = new ArrayList<String>();
	}

	@Override
	public void store(Double d) {
		DecimalFormat formatter = new DecimalFormat("0.00");
		//System.out.println("average : "+formatter.format(d));
		result.add(formatter.format(d));
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
