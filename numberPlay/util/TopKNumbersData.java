package numberPlay.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TopKNumbersData implements PersisterI, TopKNumbersResultsI {
	
	private String outputFileName;
	private int k;
	private ArrayList<String> result;
	private PriorityQueue<Double> pq;
	private BufferedWriter writer;
	
	public int getK() {
		return k;
	}

	public PriorityQueue<Double> getPq() {
		return pq;
	}

	public TopKNumbersData(String outputFileName, String k){
		this.outputFileName = outputFileName;
		this.k =  Integer.parseInt(k);
		result = new ArrayList<String>();
		pq = new PriorityQueue<Double>();
	}
	
	@Override
	public void store(List<Double> topK) {
		//System.out.println("topK : "+topK.toString());
		this.result.add(topK.toString());
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
