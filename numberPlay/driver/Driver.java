package numberPlay.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import numberPlay.observer.NumberPeaksObserver;
import numberPlay.observer.ObserverI;
import numberPlay.observer.RunningAverageObserver;
import numberPlay.observer.TopKNumbersObserver;
import numberPlay.subject.Subject;
import numberPlay.subject.SubjectI;
import numberPlay.util.EventFilter;
import numberPlay.util.FileProcessor;
import numberPlay.util.NumberPeaksData;
import numberPlay.util.NumberProcessor;
import numberPlay.util.RunningAverageData;
import numberPlay.util.Tags;
import numberPlay.util.TopKNumbersData;

/**
 * @author John Doe
 * TODO update the author name.
 */
public class Driver {
	public static void main(String[] args) throws InvalidPathException, SecurityException, FileNotFoundException, IOException  {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 * FIXME Refactor commandline validation using the validation design taught in class.
		 */
		final int REQUIRED_NUMBER_OF_ARGS = 6;
		if ((args.length != REQUIRED_NUMBER_OF_ARGS) || 
				(args[0].equals("${inputNumStream}")) || 
				(args[1].equals("${runAvgWindowSize}")) || 
				(args[2].equals("${runAvgOutFile}")) ||
				(args[3].equals("${k}")) ||
				(args[4].equals("${topKNumOutFile}")) ||
				(args[5].equals("${numPeaksOutFile}"))) {

			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
			
			System.exit(0);
		}
		int windowSize = Integer.parseInt(args[3]);
		if(windowSize <= 0){
			System.err.printf("Error: runAvgWindowSize can't be zero or negative.");
			System.exit(0);
		}
		
		int k = Integer.parseInt(args[3]);
		if(k <= 0){
			System.err.printf("Error: k can't be zero or negative.");
			System.exit(0);
		}
	    
		// FIXME Create an instance of each of the classes implementing PersisterI and the 
		// corresponding ResultsI interface.
		// Observers use these objects to dump data to be stored and eventually persisted 
		// to the corresponding output file.
	    RunningAverageData runningAverageData = new RunningAverageData(args[2], args[1]);
	    NumberPeaksData numberPeaksData = new NumberPeaksData(args[5]);
	    TopKNumbersData topKNumbersData = new TopKNumbersData(args[4], args[3]);
	    
	    EventFilter intEventFilter = new EventFilter("intEventFilter");
	    EventFilter doubleEventFilter = new EventFilter("doubleEventFilter");
	    EventFilter completeEventFilter = new EventFilter("completeEventFilter");
	    
	    HashMap<EventFilter, ArrayList<ObserverI>> filter = new HashMap<EventFilter, ArrayList<ObserverI>>();
	    filter.put(intEventFilter, new ArrayList<ObserverI>());
	    filter.put(doubleEventFilter, new ArrayList<ObserverI>());
	    filter.put(completeEventFilter, new ArrayList<ObserverI>());
		// FIXME Instantiate the subject.
	    SubjectI subject = new Subject(filter);  

	    HashSet<Tags> tags1 = new HashSet<Tags>();
	    tags1.add(Tags.integer);
	    tags1.add(Tags.doubleVal);
	    tags1.add(Tags.complete);
	    
	    HashSet<Tags> tags2 = new HashSet<Tags>();
	    tags2.add(Tags.integer);
	    tags2.add(Tags.complete);
	    
		// FIXME Instantiate the observers, providing the necessary filter and the results object.
	    ObserverI runningAverageObserver = new RunningAverageObserver(tags2, runningAverageData);
	    ObserverI numberPeaksObserver =  new NumberPeaksObserver(tags1, numberPeaksData);
	    ObserverI topKNumbersObserver = new TopKNumbersObserver(tags1, topKNumbersData);

		// FIXME Register each observer with the subject for the necessary set of events.
	    subject.Attach(runningAverageObserver);
	    subject.Attach(numberPeaksObserver);
	    subject.Attach(topKNumbersObserver);

		// FIXME Delegate control to a separate utility class/method that provides numbers one at a time, from the FileProcessor,
		// to the subject.
		NumberProcessor processor = new NumberProcessor(args[0], subject);
		processor.Process();
		
	}
}
