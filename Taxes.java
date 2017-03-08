// Matthew Wright
// March 7 2017
// Federal Tax Calculator

package taxes;

import java.util.Scanner;

public class Taxes {
	
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		boolean runAgain = true;
		
		System.out.println("Welcome to the Federal Tax Calculator");
		System.out.println("This program will tell you how much Federal Tax you will pay\n");
		
		do
		{
			TaxCalculator calc = new TaxCalculator();

			calc.setIncome();
			calc.setCapitalGains(); 
			calc.show();
			
			runAgain = checkIfRunAgain();
		
		} while (runAgain == true);
		
		System.out.println("Happy Taxes!");
		keyboard.close();

	}
	
	public static boolean checkIfRunAgain()
	{
		Scanner keyboard = new Scanner(System.in);
		
		String answer = null;
		boolean validInput;
		boolean runAgain = false;
		
		do{
			validInput = false;
			
			System.out.println("Would you like to calculate a new value? (yes or no)");
			answer = keyboard.next();
			if(answer.equalsIgnoreCase("no"))
			{
				runAgain = false;
				return runAgain;
			}
			else if (answer.equalsIgnoreCase("yes"))
			{
				runAgain = true;
				return runAgain;
			}
			else
			{
				System.out.println("That is not a valid respond, please try again");
			}
			} while(validInput == false);
		return runAgain;
	}
	

}
