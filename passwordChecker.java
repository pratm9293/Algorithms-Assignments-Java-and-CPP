
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class passwordChecker {
	private String password;
	private int passwordLength = 0;
	private int[] charCount = new int[5];
	
	
	public passwordChecker(String s1) throws Exception{ 
		password = s1; 
		passwordLength = password.length();
		
		if(passwordLength < 8 || passwordLength > 16){
			throw new Exception("\nERROR:" +
					"\nThe password length must be"
					+ " at least 8 to 16 characters!\n");
		}
		
		for(int i=0; i<charCount.length; i++) {
			charCount[i] = 0;
		}
	}
	
	public void checkChar(char ch){

		if(ch >= 48 && ch <= 57){ 
			charCount[1]++;
		}else if(ch >= 97 &&  ch <= 122){ 
			charCount[2]++;
		}else if(ch >=65 && ch <=90 ) { 
			charCount[3]++;
		}else if(ch == '$' || ch == '^' || ch == '&' || ch == '#' || ch == '(' || ch ==')' ){
			charCount[4]++;
		}else if(ch <= 34 || ch == 37 || ch == 39 || (ch >= 42 && ch <=47) ||
				(ch >=58 && ch <=64) || (ch>=91 && ch <=93) || ch == 95 || ch == 96 || ch > 122) {
			charCount[0]++;
		}
		
	}
	
	public int checker(){
		if(charCount[0] > 0){
			return 0;
		}
		for(int i=1;i<charCount.length; i++){
			if(charCount[i] < 1) {
				return 0;
			}
		}
		return 1;
	}
	public int matching(String secondPass){
		
		char[] passArray = password.toCharArray();
		char[] pass2Array = secondPass.toCharArray();
		
		for(int i = 0; i<passwordLength; i++){
			if(passArray[i] != pass2Array[i]){
				return 0;
			}
		}
		
		return 1;
	}
	
	public static void displayRules(){
		
	System.out.println( "\nThe set of password rules is given below.\n" + 
	"\n"+
	"1) The password length: 8-16 characters\n" +
	"2) At least one numerical, i.e., 0, 1, 2,..\n" +
	"3) At least one upper case letter, i.e., A, B, C, ...\n" +
	"4) At least one lower case letter, i.e., a, b, c, ...\n" +
	"5) At least one of the special characters, but it must be\n" +
	   "   within the set:{ $ ^ & #  ( ) } at total of six (6).\n" +
	   "   no other special characters is allowed.");
	}
	
	public static void displaySuccess(){
		
		System.out.print("\nYour password will be updated in 2 minutes! ");
		
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		boolean flag = true;
		String input;
		int y = -1;
		
		
		while(flag){
			
		try{
	
			y++;
			passwordChecker.displayRules();
			
			if(y==0){
			System.out.print("\nPlease enter your new password:");
			}else{
				System.out.print("\nPlease try entering your password again:");
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			input = br.readLine();
			passwordChecker p1 = new passwordChecker(input);
			
			char[] passArray = input.toCharArray();
			for(int i=0; i<input.length(); i++){ p1.checkChar(passArray[i]); }
			
			int valid = p1.checker();
			
			if(valid == 0) {
				throw new Exception("\nERROR:\n"
						+ "Something went wrong! You either "
						+ "entered an illegal character or didn't follow the criteria\n"
						+ "Try Again!\n");
			}
			
			System.out.println("\nPlease confirm your password by entering it again:");
			
			br = new BufferedReader(new InputStreamReader(System.in));
			input = br.readLine();
			int match = p1.matching(input);
			if(match == 0) {
				throw new Exception("\nERROR:\n" + 
									"Your passwords did not match! Try Again!");
			}
			flag = false;
		
		}catch(Exception e){
				System.out.print(e.getMessage());
		   }
	
		}
		
		passwordChecker.displaySuccess();
	
	}

}
