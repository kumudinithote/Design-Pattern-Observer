package numberPlay.subject;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import numberPlay.observer.ObserverI;
import numberPlay.util.EventFilter;
import numberPlay.util.Tags;

/**
 * Implements basic functionalities for observers patter like notify(), attach(), detach(). Along with it also have filter based
 * on events and registers respective observers. This is then used to filter observers while notifying.
 *
 */

public class Subject implements SubjectI{
	
	ArrayList<ObserverI> observers = new ArrayList<ObserverI>();
	HashMap<EventFilter, ArrayList<ObserverI>> filter;
	
	public Subject(HashMap<EventFilter, ArrayList<ObserverI>> filter){
		this.filter = filter;
	}
	/**
	 * Method Attach, register observers with the subject and also register observer with respective filter based on tags.
	 *
	 * @param	o is the object of observer Interface.
	 */
	@Override
	public void Attach(ObserverI o) {
		observers.add(o);
		
		o.getFilter().forEach(tag -> {
			if(tag.equals(Tags.integer)){
				this.filter.forEach((k,v) -> {
					if(k.getFilterName().equals("intEventFilter")){
						v.add(o);
					}
						
					
				});
			}
			if(tag.equals(Tags.doubleVal)){
				this.filter.forEach((k,v) -> {
					if(k.getFilterName().equals("doubleEventFilter")){
						v.add(o);
					}
						
				});
			}
			if(tag.equals(Tags.complete)){
				this.filter.forEach((k,v) -> {
					if(k.getFilterName().equals("completeEventFilter")){
						v.add(o);
					}
						
				});
			}
		});
		
		
	}
	
	/**
	 * Method Detach, unregister the observer with the subject. 
	 *
	 * @param	o is the object of observer Interface.
	 */
	
	@Override
	public void Detach(ObserverI o) {
		observers.remove(o);
	}

	
	/**
	 * Method Notify will get the respective filter based on the event it is called from. It will get all the observer for that filter
	 * and call their update method.
	 *
	 * @param	num is the event data passed from respective event.
	 * @param	filter is filter name for that respective event.
	 */
	
	@Override
	public void Notify(Object num, String filter) throws IOException {
		this.filter.forEach((k,v) -> {
			if(k.getFilterName().equals(filter)){
				for(ObserverI obs : v){
					try {
						obs.Update(num);
					} catch (IOException e) {
						System.err.println("No observer for this event");
					}
				}
			}
				
		});
		
		
	}
	
	
	public void integerEvent(double num) throws IOException{
		String filter = "intEventFilter";
		Notify(num, filter);
	}
	
	public void floatingPointEvent(double num) throws IOException{
		String filter = "doubleEventFilter";
		Notify(num, filter);
	}
	
	public void processingCompleteEvent() throws IOException{
		String filter = "completeEventFilter";
		Notify(null, filter);
	}
}
