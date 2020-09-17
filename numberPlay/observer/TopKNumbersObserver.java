package numberPlay.observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import numberPlay.util.EventFilter;
import numberPlay.util.Tags;
import numberPlay.util.TopKNumbersData;

public class TopKNumbersObserver implements ObserverI{

	private HashSet<Tags> filter;
	
	public HashSet<Tags> getFilter() {
		return filter;
	}
	
	private TopKNumbersData topKNumbersData;

	public TopKNumbersObserver(HashSet<Tags> filter, TopKNumbersData topKNumbersData){
		this.filter = filter;
		this.topKNumbersData = topKNumbersData;
	}
	
	@Override
	public void Update(Object o) throws IOException {
		
		if(o != null){
			double num = (double)o;
			if(this.topKNumbersData.getPq().size() == this.topKNumbersData.getK()){
				this.topKNumbersData.getPq().poll();
			}
			this.topKNumbersData.getPq().add(num);
			ArrayList<Double> data = new ArrayList<Double>();
			data.addAll(this.topKNumbersData.getPq());
		
			this.topKNumbersData.store(data);
		}else{
			this.topKNumbersData.writeToFile();
			this.topKNumbersData.close();
		}
		
		
	}

}
