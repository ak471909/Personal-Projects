import java.util.Scanner;
import java.util.Random;

public class Random_Password_generator {
	
	public static boolean check_sp(char c) { //returns true if character is a special character
		if (!(Character.isAlphabetic(c) || Character.isDigit(c))){ // chararcter is not an alphabet or a digit
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	public static void generate_pass() {
		System.out.println("Recommended: Enable atleast 2 character types and choose a length between 8 and 64 characters");
		System.out.println();
		Scanner in = new Scanner(System.in);
		System.out.print("Do you wish to have lowercase letters in your password (abc) (Y/N) ");
		String lc = in.next();
		
		System.out.print("Do you wish to have uppercase letters in your password (ABC)(Y/N) ");
		String uc = in.next();
		
		System.out.print("Do you wish to have numbers in your password (0-9) (Y/N) ");
		String dg = in.next();
		
		System.out.print("Do you wish to have symbols in your password (!@#) (Y/N) ");
		String sym = in.next();
		
		System.out.print("Enter the length of the password: ");
		int n = in.nextInt();
		
		if (uc.equalsIgnoreCase("N") && lc.equalsIgnoreCase("n") && dg.equalsIgnoreCase("n") && sym.equalsIgnoreCase("n")) {
			System.out.println("Please select yes for atleast one option");
		}
		
		else {
			
			StringBuilder np = new StringBuilder();
			
			int min2 = 33;
		    int max2 = 126;
		    
		    while (np.length() < n) {
		    	int random_int1 = (int)Math.floor(Math.random() * (max2 - min2 + 1) + min2);
		    	char ch = (char)random_int1;
		    	
		    	if (lc.equalsIgnoreCase("y")) {
		    		if (Character.isLowerCase(ch)) {
		    			np.append(ch);
		    		}
		    	}
		    	
		        if (uc.equalsIgnoreCase("y")) {
		    		if (Character.isUpperCase(ch)) {
		    			np.append(ch);
		    		}
		    	}
		    	
		    	if (dg.equalsIgnoreCase("y")) {
		    		if (Character.isDigit(ch)) {
		    			np.append(ch);
		    		}
		    	}
		    	
		    	if (sym.equalsIgnoreCase("y")) {
		    		if (check_sp(ch)) {
		    			np.append(ch);
		    		}
		    	}
		    	
		    }
		    
		    System.out.println("Password generated: "+np);
		  
		}
		System.out.println();
	}
	
	public static boolean has_lc(String pass) {
		for (int n = 0; n < pass.length(); n++) {
			char ch = pass.charAt(n);
			
			if (Character.isLowerCase(ch)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public static boolean has_uc(String pass) {
		for (int n = 0; n < pass.length(); n++) {
			char ch = pass.charAt(n);
			
			if (Character.isUpperCase(ch)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean has_dg(String pass) {
		for (int n = 0; n < pass.length(); n++) {
			char ch = pass.charAt(n);
			
			if (Character.isDigit(ch)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean has_sp(String pass) {
		for (int n = 0; n < pass.length(); n++) {
			char ch = pass.charAt(n);
			
			if (check_sp(ch)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public static void strength_checker() { // Checks strength of a password
		Scanner in = new Scanner(System.in);
		System.out.print("Password: ");
		String pass = in.next();
		
		boolean strong = false;
		
		if (pass.length() > 7 && has_lc(pass) && has_uc(pass) && has_dg(pass) && has_sp(pass)) {
			strong = true;
		}
		
		String strength;
		
		if (strong == true) {
			strength = "strong";
		}
		else {
			strength = "weak";
		}
		
		System.out.println("The entered password is "+strength);
		if (strong == false) {
			System.out.println("Recommended measures\n"
					+ "- Include atlest 8 characters\n"
					+ "- Include atleast one lowercase character\n"
					+ "- Include atleast one uppercase character\n"
					+ "- Include atleast one digit\n"
					+ "- Include atleast one symbol");
		}
		
		System.out.println();
	}
	
	public static void password()
	{
		Scanner in = new Scanner (System.in);
		
		int cont = 1; 
		while (cont == 1) {
			System.out.println("Enter 1 to generate a password");
			System.out.println("Enter 2 to check password strength");
			System.out.println("Enter 3 to exit");
			int choice = in.nextInt();
			
			switch(choice){
			case 1:
				generate_pass();
				break;
			case 2:
				strength_checker();
				break;
			case 3:
				cont = 0;
				break;
			default:
				System.out.println("Please enter a valid option");
			
			}
		}
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("-------------------------------------------PASSWORD GENERATOR-------------------------------------------");
		
		System.out.println();
		System.out.println("Generate a strong password");
		   
		password();
	    
	}

}
