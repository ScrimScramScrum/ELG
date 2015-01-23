package springmvc.service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;


public class FileService {
    
    public static int readFromFile() {
        int number = -1;
        String fromFile = "";

        File file = new File("/home/scripts/numberOfEmails.txt");


        try (FileInputStream fis = new FileInputStream(file)) {

            System.out.println("Total file size to read (in bytes) : "+ fis.available());

            int content;
            while ((content = fis.read()) != -1) {
                    // convert to char and display it
                    System.out.print((char) content);
                    fromFile+=(char)content+"";
            }

            number = Integer.parseInt(fromFile);

        } catch (Exception e) {
                e.printStackTrace();
        }



        return number;  
    }
    
    
    public static void WriteToFile(int numberOfEmailsToday) {
		try {

			String content = ""+(numberOfEmailsToday+1);
 
			File file = new File("/home/scripts/numberOfEmails.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
}
