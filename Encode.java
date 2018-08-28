// This code will take message from the user and write it in file
// using One time Pad, the written message will be encrypted
/*
 *  one-time pad (OTP) is an encryption technique that cannot be cracked if used correctly. 
 *  In this technique, a plain text is paired with random, secret key (or pad). 
 *  Then, each bit or character of the plain text is encrypted by combining it with the corresponding bit or character 
 *  from the pad using modular addition. 
 *  If the key is truly random, and at least as long as the plain text, and 
 *  never reused in whole or in part, and kept completely secret, 
 *  then the resulting cipher text will be impossible to decrypt or break.
 */
//########## LIBRARY ####################
import java.io.*;
import java.util.*;
// ######################################

// ************** CLASS ****************
public class Encode {
// ************************************
	
	public static void main(String[] args) throws IOException {
		// What do we need?
		String message = null;
		String key = null;
		String stringNoSpace;
		File plainText, encryptedMessage, createKey;
		Scanner scan;
		FileWriter writesOnPlainTextFile, writeOnCreateKey, writeOnCipherText;
		String[] spaceSeperatedMessage; // holds elements seperated by "," or " space"
		char[] cipherText;
		
		
		
		
		//+++++++++++++++ Get the input from User ++++++++++++++
		System.out.print("Please, enter the message you want to encode");
		scan = new Scanner(System.in); // initalize the scanner class
		message = scan.nextLine();
		//This will cause any number of consecutive spaces to split your string into tokens.
	    spaceSeperatedMessage = message.trim().split("\\s+"); // extract message only and throw space.
	    
	    // converting String Array to String
	    StringBuilder strBuilder = new StringBuilder();
	    for (int i = 0; i < spaceSeperatedMessage.length; i++) {
	       strBuilder.append(spaceSeperatedMessage[i]);
	    }
	    stringNoSpace = strBuilder.toString();
	    
	    System.out.println("The Length of your message is " + stringNoSpace.length());
	    
		// Ask user to create their own key
		
		do {
		System.out.println("Please Enter your secret key of same or greater size than your message length");
		key = scan.nextLine();
		System.out.println("The length of your key is " + key.length() + ". It should be equals to or greater in size than you message");
		
		if(key.length() >= stringNoSpace.length()) {
			System.out.println("Perfect! Your secret key is enough to encrypt your message");
			break;
		}
		}
		while(true);
		
		scan.close();
		
		
		// ------------------------------------------------------
		
		
		
		
		// Get the path of the current working directory
		String currentWorkingDirectory = System.getProperty("user.dir"); 
		
		
		
		
		
		// ++++++++++++++ CREATE files +++++++++
			// create a file to store user's message in the specified Location
		plainText = new File(currentWorkingDirectory + "//message.txt"); // creates file in the current working directory and name it message.txt
			// create new file to store the key in the specified Location
		createKey = new File(currentWorkingDirectory + "//oneTimePad.txt");
			// create new file to store the cipher text
		encryptedMessage = new File(currentWorkingDirectory + "//cipher.txt");
		// -----------------------------------------------------
		
		
		
	
		
		// +++++++++++++++ Write on the file ++++++++++++
			// where to write: plainText File
		writesOnPlainTextFile = new FileWriter(plainText);
		
		 // write on plain text file that was created earlier
		 for(String textMessage : spaceSeperatedMessage) {
			 writesOnPlainTextFile.write(textMessage);
			
				}
		
		 	writesOnPlainTextFile.close();
		
		// where to write: createKey File
		 writeOnCreateKey = new FileWriter(createKey);
		// Writing on the oneTimePad File
		    writeOnCreateKey.write(key);
		    writeOnCreateKey.close();
		//----------------------------------------------
		    
		    
		    
		
		// ++++++++++++++ Encrypt The message ++++++++++++
		
		//-----------------Read from the files -------------
		BufferedReader readPlainText = new BufferedReader(new FileReader(plainText)); // reads from plainText File
			String getText = (String) readPlainText.readLine(); // since the spaces were removed from messages, readLine() returns only 1 string
		BufferedReader readCreateKey = new BufferedReader(new FileReader(createKey)); // reads from createKey file
		 	String getKey = (String) readCreateKey.readLine();
		 	
		 	// Initialize the length of chipherText Array before using
		 	 cipherText = new char[getText.length()];
		 	 
		try {
	for(int i = 0; i < getText.length(); i++ ) {
		    int asciiPlainText = (int) getText.charAt(i); // we have ASCII value of each Character
		    int asciiKey = (int) getKey.charAt(i); 
		    
		    int min = 65;  // 65 is UpperCase A
		    int max = 91; // 90 is UpperCase Z
		    
		    int cipherNumber = (asciiPlainText +asciiKey); // sum of; each character of message and each character of key
		    
		    // This loop will give value between 65 and 90 which is A-Z in ASCII
		    while(cipherNumber >=91 || cipherNumber <=64) {
		    	cipherNumber = (cipherNumber % max) + min;
		    	
		    }
		   
		    // convert the ASCII Back to character
		    char backCharacter = (char) cipherNumber; 
		    
		    // put each character in char Array
		    // iNITIALIZE CIPHERTEXT array
		   
		   cipherText[i] = backCharacter;
		   
		    
		
}
		} catch (ArrayIndexOutOfBoundsException exception) {
		   System.out.println("Index Out of Bound");
		}
		
		//-------- WRITE THE CIPHER TEXT IN FILE ------------
		writeOnCipherText = new FileWriter(encryptedMessage);
		// Writing on the oneTimePad File
		    writeOnCipherText.write(String.valueOf(cipherText));
		    writeOnCreateKey.close();
		    
		    
		// Prints the cipher text in console
	System.out.println(String.valueOf(cipherText));
	
	
	
		
	//+++++++++++++++Check if the file has been created ++++++++++++
	
		/*
		try {
			if (userMessage.createNewFile())
			{
			    System.out.println("File is created!");
			} else {
			    System.out.println("File already exists.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //-----------------------------------
		*/
		
		
	}

}

