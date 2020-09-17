package numberPlay.observer;

import java.io.IOException;
import java.util.HashSet;

import numberPlay.util.EventFilter;
import numberPlay.util.Tags;

public interface ObserverI{
	void Update(Object num) throws IOException;
	public HashSet<Tags> getFilter();
}