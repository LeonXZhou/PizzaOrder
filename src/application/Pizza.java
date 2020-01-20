package application;
/**
 * The Pizza class is an object used to store information on a Pizza to be ordered. It stores information about
 * the size, the amount of cheese, pepperoni, and ham. It implements java.io.Serializable so it can be stored as a file 
 * 
 *
 * @author  Leon Zhou (10177892)
 * @version 1.0
 * @since   2017-03-10 
 */
public class Pizza implements java.io.Serializable
{
	private static final long serialVersionUID = 5789335549554632519L; // generate serial tag
	private String size;											   // size of pizza
	private int cheese = 1;   										   // amount of cheese
	private int ham = 0;											   // amount of ham
	private int pepperoni = 0;										   // amount of pepperoni
	private double cost = 0;										   // cost of pizza

	/**
	 * This constructor creates a default pizza of a certain size. It uses the pizzaCost() method
	 * to calculate the total cost of the pizza 
	 * @param s this parameter represents the size of the desired pizza
	 * @throws IllegalPizza On incorrect input. 
	 */
	public Pizza(String s) throws IllegalPizza
	{
		if (s==null)
		{
			throw new IllegalPizza("null object entered");
		}//end if
		size = s;
		cheese = 1;
		pepperoni = 1;
		if (!s.equalsIgnoreCase("small")&&!s.equalsIgnoreCase("medium")&&!s.equalsIgnoreCase("large"))
		{
			throw new IllegalPizza("Illegal pizza size:" + s);
		}//end if
		cost = pizzaCost(s,1,0,1); // calculates the cost of the pizza with pizzaCost
	}//end Pizza(String s)

	/**
	 * This constructor creates a pizza of desired size, cheese amount, ham amount, and pepperoni amount. 
	 * It uses the pizzaCost method() to calculate the total cost of the pizza 
	 * @param s this parameter represents the size of the desired pizza
	 * @param c this parameter represents the amount of cheese. c must be 1, 2, or 3
	 * @param h this parameter represents the amount of ham. h must be 0, 1, 2, or 3	
	 * @param p this parameter represents the amount of pepperoni. p must be  0, 1, 2, or 3
	 * @throws IllegalPizza On incorrect input. 
	 */
	public Pizza(String s, int c, int h, int p) throws IllegalPizza
	{
		if (s==null)
		{
			throw new IllegalPizza("null object entered");
		}// end if
		if (!s.equalsIgnoreCase("small")&&!s.equalsIgnoreCase("medium")&&!s.equalsIgnoreCase("large")||(c<1||c>3||h<0||h>3||p<0||p>3)||(p+h>3))
		{
			throw new IllegalPizza("Illegal pizza parameter: Size:" + s + " cheese:" + c + " ham:" + h + " pizza:" + p);
		}// end if
		size = s;
		cheese = c;
		ham = h;
		pepperoni = p;

		cost = pizzaCost(s,c,h,p);
	}// end Pizza(String s, int c, int h, int p)

	/**
	 * This constructor creates a pizza of desired size, cheese amount, ham amount, and default pepperoni. 
	 * It uses the pizzaCost() method to calculate the total cost of the pizza 
	 * @param s this parameter represents the size of the desired pizza
	 * @param c this parameter represents the amount of cheese. c must be 1, 2, or 3
	 * @param h this parameter represents the amount of ham. h must be 0, 1, 2, or 3	
	 * @throws IllegalPizza On incorrect input. 
	 */
	public Pizza(String s, int c, int h) throws IllegalPizza
	{
		if (s==null)
		{
			throw new IllegalPizza("null object entered");
		}// end if
		if (!s.equalsIgnoreCase("small")&&!s.equalsIgnoreCase("medium")&&!s.equalsIgnoreCase("large")||(c<1||c>3||h<0||h>3)||(1+h>3))
		{
			throw new IllegalPizza("Illegal pizza size:" + s);
		}// end if
		size = s;
		cheese = c;
		ham = h;
		pepperoni = 1;
		cost = pizzaCost(s,c,h,1);
	}// end Pizza(String s, int c, int h)

	/**
	 * this accessor method retrieves the cost of a pizza
	 * @return cost is the total cost of a pizza
	 */
	public double getCost()
	{
		return cost;
	}// end getCost
	/**
	 *  This method overrides the toString method and gives the string representation of a given pizza.
	 *  It uses the numberString() method to convert integers to desired strings.
	 *  @return pizzaString is the string representation of a given pizza
	 */
	public String toString()
	{
		String p;					//String representation of amount of pepperoni
		String h;   				//String representation of amount of ham
		String c;					//String representation of amount of cheese
		String pizzaString = "";	
		c = numberString(cheese);   //Converts integer to desired string
		h = numberString(ham);
		p = numberString(pepperoni);
		//The following four if statements concatenates the correct pizzaString depending on the pizza. 
		if (pepperoni == 0 && ham != 0)
		{
			pizzaString = size.toLowerCase()+ " pizza," + c + "cheese," + h + "ham. Cost: $" + String.format("%.2f", cost) +	" each.";
		}// end if
		if (pepperoni != 0 && ham == 0)
		{
			pizzaString = size.toLowerCase() + " pizza," + c + "cheese," + p + "pepperoni. Cost: $" + String.format("%.2f", cost) + " each.";
		}// end if
		if (pepperoni != 0 && ham != 0)
		{
			pizzaString = size.toLowerCase() + " pizza," + c + "cheese," + h + "ham," + p + "pepperoni. Cost: $" +String.format("%.2f", cost) + " each.";
		}// end if
		if (pepperoni == 0 && ham == 0)
		{
			pizzaString = size.toLowerCase() + " pizza," + c + "cheese only. Cost: $" +String.format("%.2f", cost) + " each.";
		}//end if

		return pizzaString;
	}
	/**
	 *  This method overrides the equals method and compares a given pizza object to another to check for equality. 
	 *  Returns a boolean
	 *  @param p this the object to be compared to the pizza
	 */
	@Override
	public boolean equals(Object p)
	{
		if (p instanceof Pizza)
		{
			//compares all attributes of pizza and cheks for equality. 
			if(((Pizza)p).size.equalsIgnoreCase(size)&&((Pizza)p).cost == this.cost && ((Pizza)p).ham == this.ham && ((Pizza)p).cheese == this.cheese && ((Pizza)p).pepperoni == this.pepperoni)
			{
				return true;
			}// end if
		}// end if
		return false;
	}// end equals
	@Override
	/**
	 *  This method creates a deep copy of a pizza.
	 *  @return pCopy this is copied pizza.
	 */
	public Pizza clone()
	{
		Pizza pCopy = null;
		try 
		{
			pCopy = new Pizza(this.size,this.cheese,this.ham,this.pepperoni);
		}// end try
		catch(IllegalPizza i)
		{
			System.out.println("I'm sorry but something went horribly in the cloning process... your pizza is gluten free...");
		}// end catch
		return pCopy;
	}
	/**
	 *  This method returns the desired string representation of a number. 
	 *  @param i is the number to be converted
	 *  @return s returns correct string: 1=" ", 2=" double " 3=" triple "
	 */
	private String numberString(int i)
	{
		String s = "";
		if (i == 1)
		{
			s = " ";
		}// end if
		if (i == 2)
		{
			s = " double ";
		}// end if
		if (i == 3)
		{
			s = " triple ";
		}// end if
		return s;
	}
	/**
	 * This method calculates the total cost of the pizza
	 * @param s this parameter represents the size of the desired pizza
	 * @param c this parameter represents the amount of cheese. c must be 1, 2, or 3
	 * @param h this parameter represents the amount of ham. h must be 0, 1, 2, or 3	
	 * @param p this parameter represents the amount of pepperoni. p must be 0, 1, 2, or 3
	 * @return cost this is the calculated cost of a given pizza. 
	 */
	private double pizzaCost(String s, int c, int h, int p)
	{
		if(s.equalsIgnoreCase("small"))
		{
			cost = 7.00+1.50*(c-1)+1.50*h+p*1.50;
		}// end if
		if(s.equalsIgnoreCase("medium"))
		{
			cost = 9.00+1.50*(c-1)+1.50*h+p*1.50;
		}// end if
		if(s.equalsIgnoreCase("large"))
		{
			cost = 11.00+1.50*(c-1)+1.50*h+p*1.50;
		}// end if
		return cost;
	}
}

