package application;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * The line item class is an input output utility class
 * 
 * @author  Leon Zhou (10177892)
 * @version 1.0
 * @since   2017-03-10 
 */
public class IOHelper {
	private static Scanner screenInput = new Scanner(System.in);
	/**
	 * This method prompts the user for a string and returns the user input
	 */
	public static String getString(String Prompt)
	{
		System.out.println(Prompt);
		return screenInput.nextLine();
	}// end getString
	
	/**
	 * This method continuously prompts the user for an integer until a correct integer is entered
	 * and returns the user inputed integer.
	 * @param i is the lower bound of input
	 * @param m is the upper bound of input
	 * @param promt is the string to be displayed to the user
	 * @return orderNumber is the user input
	 */
	public static int getInt(int i, String Prompt, int m)
	{
		System.out.println(Prompt);
		int orderNumber = -100;
		try
		{
			orderNumber = screenInput.nextInt();
			screenInput.nextLine();
		}// end try
		catch(InputMismatchException e)
		{
			String a = screenInput.nextLine();
			System.out.println(a + " is not an integer!");
		}// end catch
		// while loop runs until an input within the bounds is given. 
		while(orderNumber>m||orderNumber<i)
		{
			try
			{
				System.out.println("incorrect number try again");
				orderNumber = screenInput.nextInt();
				screenInput.nextLine();
			}// end try
			catch(InputMismatchException e)
			{
				String a = screenInput.nextLine();
				System.out.println(a + " is not an integer!");
			}// end catch
		}// end while
		return orderNumber;
	}
	/**
	 * This method continuously prompts the user for an integer until a correct integer is entered
	 * and returns the user inputed integer.
	 * @param i is the lower bound of input
	 * @param promt is the string to be displayed to the user
	 * @return orderNumber is the user input
	 */
	public static int getInt(int i,String Prompt)
	{
		int orderSize = 0;
		System.out.println(Prompt);
		try
		{
			orderSize = screenInput.nextInt();
			screenInput.nextLine();
		}// end try
		catch(InputMismatchException e)
		{
			String a = screenInput.nextLine();
			System.out.println(a + " is not an integer!");
		}// end catch
		
		// loops until an acceptable integer is entered
		while(orderSize<i||orderSize>100)
		{
			try
			{
				System.out.println("incorrect number try again");
				orderSize = screenInput.nextInt();
				screenInput.nextLine();
			}// end try
			catch(InputMismatchException e)
			{
				String a = screenInput.nextLine();
				System.out.println(a + " is not an integer!");
			}// end catch
		}// end while
		return orderSize;
	}
}

