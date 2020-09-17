package numberPlay.observer;

import java.io.IOException;
import java.util.HashSet;

import numberPlay.util.RunningAverageData;
import numberPlay.util.Tags;

public class RunningAverageObserver implements ObserverI{

	private HashSet<Tags> filter;
	
	public HashSet<Tags> getFilter() {
		return filter;
	}
	
	private RunningAverageData runningAverageData;

	public RunningAverageObserver(HashSet<Tags> filter, RunningAverageData runningAverageData){
		this.filter = filter;
		this.runningAverageData = runningAverageData;
	}
	
	@Override
	public void Update(Object o) throws IOException {
		if(o != null){
			double num = (double)o;
			if(this.runningAverageData.getQueue().size() == this.runningAverageData.getWindowSize()){
				this.runningAverageData.getQueue().poll();
			}
			this.runningAverageData.getQueue().add(num);
			double avg = 0;
			for(Double data : this.runningAverageData.getQueue()){
				avg += data;
			}
			avg = avg/this.runningAverageData.getQueue().size();
			
			runningAverageData.store(avg);
		}else{
			this.runningAverageData.writeToFile();
			this.runningAverageData.close();
		}	
	}

}
