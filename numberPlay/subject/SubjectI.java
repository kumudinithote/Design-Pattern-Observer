package numberPlay.subject;

import java.io.IOException;

import numberPlay.observer.ObserverI;

public interface SubjectI{
	void Attach(ObserverI o);
	void Detach(ObserverI o);
	void Notify(Object num, String filter) throws IOException;
}