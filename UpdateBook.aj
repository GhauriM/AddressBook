import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/*
 * this aspect class logs any changes made to the address book in a file that is save separate from the program
 */
public aspect UpdateBook {
	pointcut checkUpdate(): call(public void recordChange(int, String));	//point cut at the method logs the change

	//after the point cut the record that was changed is saved to a file.
	after(): checkUpdate() {

		//field saves the last entered record into the change log.
		String record = (((Book)thisJoinPoint.getTarget()).changeLog.get(((Book)thisJoinPoint.getTarget()).changeLog.size() - 1) +"\n");
		System.out.println(record);
		//try to create the file to add the record to
		try {
			File file = new File("ChangeRecord.txt");	//file object to refrence the file
			//create the file if it does not exist
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("Created: " + file.getName());
			}
			FileWriter writer = new FileWriter(file.getName(),true);	//create a file writer to write to the file an open it
			BufferedWriter buffer = new BufferedWriter(writer);			//create a buffer so contents can be appended too
			buffer.append(record);		//write to the file
			buffer.close();		//close the buffer and file writer
			System.out.println("Successfully wrote to the file.");
			}
		catch (IOException e) {
			System.out.println("An error occured when adding to file");		//if error with opening or creating file occurs, print message
			}
		}
}
