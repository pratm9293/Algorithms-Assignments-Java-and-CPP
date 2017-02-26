//============================================================================
// Name        : Project2.cpp
// Author      : Prateek Malli
//============================================================================

#include <iostream>


using namespace std;

class MyException{
private:
	string error;
public:
	MyException(string msg){error = msg;}
	void print(){cout << error << endl; }

};

class passwordChecker{
private:

	string password;
	string secondPassword;
	int    charCount[5];
	int    passwordLength=0;

public:

	passwordChecker(string s1){

		password = s1;
		passwordLength = password.length();

		if(passwordLength < 8 || passwordLength > 16) {

			throw MyException("\n**ERROR: The password you entered did not have a valid length.**");
		}


		for(int i=0; i<5; i++){charCount[i]=0;}
	}

	static void displayRules(){
		cout 	<< "\nThese are the rules to which your password must be set:\n"
				<<  "\n1) The password length: 8-16 characters\n"
				<<  "2) At least one numerical, i.e., 0, 1, 2,..\n"
				<<  "3) At least one upper case letter, i.e., A, B, C, ...\n"
				<<  "4) At least one lower case letter, i.e., a, b, c, ...\n"
				<<  "5) At least one of the special characters, but it must be\n"
				<< "   within the set:{ $ ^ & #  ( ) } at total of six (6).\n"
				<< "   no other special characters is allowed.\n";
	}

	void displaySuccess(){
		cout << "\nYour password will be updated in 2 minutes!";
	}

	void passArray(){
		char passArr[passwordLength];
		for(int i=0; i<passwordLength; i++) {
			passArr[i] = password[i];
			checkChar(passArr[i]);
		}
	}

	void checkChar (char ch){

		if(ch >= 48 && ch <= 57){ //do the numeric test here. numeric ascii is from 48-57
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

	int checker(){
		if(charCount[0] > 0){
			return 0;
		}
		for(int i=1;i<5; i++){
			if(charCount[i] < 1) {
				return 0;
			}
		}
		return 1;
	}

	int matching(string s1){
		if(s1.length() != passwordLength){
			return 0;
		}

		for(int i=0; i<passwordLength; i++){
			if(s1[i] != password[i]){
				return 0;
			}
		}
		return 1;
	}

};

int main() {

	int y=0;
	string pass;
	bool flag = true;
	passwordChecker *z;

while(flag){
	try{

		passwordChecker::displayRules();

		if(y==0){
			cout << "\nPlease enter your new password.";
		}else if(y>1){
			cout << "\nPlease try entering your password again.";
		}
			cin >> pass;
			passwordChecker x(pass);
			z = &x;
			z->passArray();
			int valid = z->checker();

			if(valid == 0) {
				throw MyException("Something went wrong... You either entered an illegal character, "
						"or didn't follow the criteria. Try Again.");
			}

			cout << "Please confirm your password.";
			cin  >> pass;
			int match = z->matching(pass);

			if(match == 0){
				throw MyException("\nPasswords didn't match...");
			}

			flag = false;
	}
	catch(MyException& e){
		y++;
		e.print();
		flag = true;
	}
  }
	z->displaySuccess();

	return 0;
}
