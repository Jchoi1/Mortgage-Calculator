import java.text.NumberFormat;
import java.util.Scanner;

import jdk.dynalink.beans.StaticClass;

public class Experiment3 {
    final static byte Percent = 100;
    final static byte Months_in_year = 12;
    public static void main(String[] args)  {


        int Principal = (int) readNumber("Principal: ", 1000, 1_000_000);
        
        float annualMonthlyInterest = (float) readNumber("Annual Interest Rate: ", 1, 30);
        
        byte years = (byte) readNumber("Period (Years): ", 1, 30);

        double mortgage = calculateMortgage(Principal, annualMonthlyInterest, years);


        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: "+ mortgageFormatted);

        for (short month = 1; month <= years * Months_in_year; month ++)    {
            double balance = fixedMonthlyBalance(annualMonthlyInterest, years, Principal);
            NumberFormat.getCurrencyInstance().format(balance);
            System.out.println(balance);
        }
    }
    public static double calculateMortgage(int Principal,float annualMonthlyInterest, byte years)   {


        short numberOfPayments = (short)(years * Months_in_year);

        float MonthlyInterest = annualMonthlyInterest / Percent / Months_in_year;

        double Calculate =  Principal*((MonthlyInterest * Math.pow((1+MonthlyInterest), numberOfPayments))
        /(Math.pow(1+MonthlyInterest, numberOfPayments)-1));
        return Calculate;
    }
    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true)    {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)  
                break;
            System.out.println("Enter a number between " + min + " and " + max);
        }
        return value;
    }
    public static double fixedMonthlyBalance(float annualMonthlyInterest, byte years, int Principal)  {
        float MonthlyInterest = annualMonthlyInterest / Percent / Months_in_year;
        short numberOfPayments = (short)(years * Months_in_year);

        double Calculate =  Principal*((MonthlyInterest * Math.pow((1+MonthlyInterest), numberOfPayments))
        /(Math.pow(1+MonthlyInterest, numberOfPayments)-1));
        
        double MonthlyBalance = Principal * Math.pow((1 + MonthlyInterest), numberOfPayments) - Math.pow(1 + MonthlyInterest, Calculate)
        /(Math.pow(1 + MonthlyInterest, numberOfPayments) - 1); 
        return MonthlyBalance;

    }
}
