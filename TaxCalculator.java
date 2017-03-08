// Matthew Wright
// March 7 2017
// Federal Tax Calculator

package taxes;

import java.util.Scanner;

public class TaxCalculator {
	
	private Scanner keyboard = new Scanner(System.in);
	
	private double totalIncome;
	private double taxableIncome;
	private double capitalGains;
	private double incomeTax;
	private double capitalGainsTax;
	private double totalTax;
	private String taxBracket;
	private double takeHome;
	private double marginalRate;
	private double averageRate;
	
	private static final double underFirstTaxBracketRate = .15;
	private static final double firstTaxBracket = 45916;
	private static final double underSecondTaxBracketRate = .205;
	private static final double secondTaxBracket = 91831;
	private static final double underThirdTaxBracketRate = .26;
	private static final double thirdTaxBracket = 142353;
	private static final double underLastTaxBracketRate = .29;
	private static final double lastTaxBracket = 202800;
	private static final double overLastTaxBracketRate = .33;
	
	TaxCalculator()
	{
		taxableIncome = 0;
		capitalGains = 0;
		taxBracket = null;
	}
	
	public void setIncome()
	{
		System.out.println("Enter the money you earned through employment:");
		totalIncome = keyboard.nextDouble();
		while(totalIncome < 0)
		{
			System.out.println("Please enter a number greater than zero:");
			totalIncome = keyboard.nextDouble();
		}
		taxableIncome = totalIncome - 11474; // Every citizen gets a general deduction of $11474
	}
	
	public void setCapitalGains()
	{
		System.out.println("Enter the money you earned through capital gains:");
		capitalGains = keyboard.nextDouble();
		while(capitalGains < 0)
		{
			System.out.println("Please enter a number greater than zero:");
			totalIncome = keyboard.nextDouble();
		}
		
	}
	
	private double calculateIncomeTax()
	{
		if(taxableIncome < firstTaxBracket)
		{
			incomeTax = taxableIncome*underFirstTaxBracketRate;
			taxBracket = "first";
			marginalRate = underFirstTaxBracketRate;
			return incomeTax;
		}
		else if(taxableIncome < secondTaxBracket)
		{
			incomeTax = (firstTaxBracket*underFirstTaxBracketRate) + 
					(taxableIncome - firstTaxBracket)*underSecondTaxBracketRate;
			taxBracket = "second";
			marginalRate = underSecondTaxBracketRate;
			return incomeTax;
		}
		else if(taxableIncome < thirdTaxBracket)
		{
			incomeTax = (firstTaxBracket*underFirstTaxBracketRate) + 
					((secondTaxBracket - firstTaxBracket)*underSecondTaxBracketRate) + 
					(taxableIncome - secondTaxBracket) * underThirdTaxBracketRate;
			taxBracket = "third";
			marginalRate = underThirdTaxBracketRate;
			return incomeTax;
		}
		else if(taxableIncome < lastTaxBracket)
		{
			incomeTax = (firstTaxBracket*underFirstTaxBracketRate) + 
					((secondTaxBracket - firstTaxBracket)* underSecondTaxBracketRate) + 
					((thirdTaxBracket - secondTaxBracket) * underThirdTaxBracketRate) +
					(taxableIncome - thirdTaxBracket) * underLastTaxBracketRate;
			taxBracket = "second highest";
			marginalRate = underLastTaxBracketRate;
			return incomeTax;
		}
		else
		{
			incomeTax = (firstTaxBracket*underFirstTaxBracketRate) + 
					((secondTaxBracket - firstTaxBracket)* underSecondTaxBracketRate) + 
					((thirdTaxBracket - secondTaxBracket)* underThirdTaxBracketRate) +
					((lastTaxBracket - thirdTaxBracket) *underLastTaxBracketRate) +
					(taxableIncome - lastTaxBracket) * overLastTaxBracketRate;
			taxBracket = "highest";
			marginalRate = overLastTaxBracketRate;
			return incomeTax;
		}
		
	}
	
	private double calculateCapitalGainsTax()
	{
		capitalGainsTax = (capitalGains/2.0 ) * marginalRate;
		return capitalGainsTax;
	}
	
	private void calculateTotalTax()
	{
		totalTax = this.calculateIncomeTax() + this.calculateCapitalGainsTax();
	}
	
	private void calculateTakeHome()
	{
		takeHome = totalIncome - totalTax;
	}
	
	private void calculateAverageRate()
	{
		averageRate = totalTax/totalIncome;
	}
	
	public void show()
	{
		this.calculateTotalTax();
		this.calculateTakeHome();
		this.calculateAverageRate();
		
		System.out.printf("You will pay %,.2f CAD in tax \n", totalTax);
		System.out.printf("You are in the %s tax bracket \n", taxBracket);
		System.out.printf("You will take home %,.2f CAD \n", takeHome);
		System.out.printf("Your marginal tax rate is %.1f percent\n",marginalRate*100);
		System.out.printf("Your average tax rate is %.1f percent\n",averageRate*100);
	}
	

}
