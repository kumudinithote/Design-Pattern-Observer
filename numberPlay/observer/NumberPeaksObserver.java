package numberPlay.observer;

import java.io.IOException;
import java.util.HashSet;

import numberPlay.util.NumberPeaksData;
import numberPlay.util.Tags;


public class NumberPeaksObserver implements ObserverI{
	
	private HashSet<Tags> filter;
	
	public HashSet<Tags> getFilter() {
		return filter;
	}
	private NumberPeaksData numberPeaksData;

	public NumberPeaksObserver(HashSet<Tags> filter, NumberPeaksData numberPeaksData){
		this.filter = filter;
		this.numberPeaksData = numberPeaksData;
	}
	
	@Override
	public void Update(Object o) throws IOException {
		if(o != null){
			double num = (double)o;
			if(num < this.numberPeaksData.getPreviosNumber()){
				numberPeaksData.store(this.numberPeaksData.getPreviosNumber());
			}
			this.numberPeaksData.setPreviosNumber(num);
		}else{
			this.numberPeaksData.writeToFile();
			this.numberPeaksData.close();
		}

	}

}
