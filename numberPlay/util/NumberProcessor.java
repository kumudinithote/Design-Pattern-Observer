package numberPlay.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.regex.Pattern;

import numberPlay.subject.Subject;
import numberPlay.subject.SubjectI;

public class NumberProcessor {
	
	private FileProcessor fileProcessor;
	private Subject subject;
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	public NumberProcessor(String inputFileName, SubjectI subject) throws InvalidPathException, SecurityException, FileNotFoundException, IOException {
		this.fileProcessor = new FileProcessor(inputFileName);
		this.subject = (Subject)subject;
	}

	public void Process() throws NumberFormatException, IOException{
		
		String inputData;
	    do{
	    	inputData = fileProcessor.poll();
	    	
			if(inputData == null){
				subject.processingCompleteEvent();
			}else{
				if(!isNumeric(inputData)){
					System.err.printf("Error: string is not valid number : %s", inputData );
					System.exit(0);
				}
				double num = Double.parseDouble(inputData);
				if (num == Math.ceil(num)){
					subject.integerEvent(num);
				}else{
					subject.floatingPointEvent(num);
				}
			}		
            
	    }while(inputData != null);
		
		
	}
	
	public boolean isNumeric(String strNum) {
		return pattern.matcher(strNum).matches();
	}
}
