package application;
/**
 * The line item class is an object used to store amount and type of pizzas to be ordered. 
 * It implements java.io.Serializable so it can be stored as a file. It also implements comparable<LineItem>
 * so it can be properly compared 
 * 
 * @author  Leon Zhou (10177892)
 * @version 1.0
 * @since   2017-03-10
 */
public class LineItem implements java.io.Serializable, Comparable<LineItem>
{

	private static final long serialVersionUID = 5723581601036010490L; //auto generated serial number
	private Pizza pOrder = null;									   //the pizza being ordered
	private int numberPizza = 1;									   //the amount of pizza pOrder that are peing ordered
	/**
	 * This constructor creates a default order of 1 pizza
	 * @param p is the type of pizza being ordered
	 * @throws IllegalPizza if p = null
	 */
	public LineItem(Pizza p) throws IllegalPizza
	{
		if (p == null)
		{
			throw new IllegalPizza("null object entered");
		}//end if
		pOrder = p;
	}// end LineItem(Pizza p)

	/**
	 * This constructor creates a order of a certain amount and type of pizza
	 * @param p is the type of pizza being ordered
	 * @param n is the number of p type pizza being ordered
	 * @throws IllegalPizza if p is null or n > 100 or n<100
	 */
	public LineItem(int n, Pizza p) throws IllegalPizza
	{
		if (p == null)
		{
			throw new IllegalPizza("null object entered");
		}// end if
		if(n < 1)
		{
			throw new IllegalPizza("you have to buy at least 1 pizza");
		}// end if
		if(n > 100)
		{
			throw new IllegalPizza("thats wayyyyyyy ttooooooooo maaannnnyyyy piiiizzzzaaaaas");
		}// end if
		pOrder = p;
		numberPizza = n;

	}// end LineItem(int n, Pizza p)

	/**
	 * this method returns the type of pizza in an order
	 * @return pOrder is the type of pizza being ordered
	 */
	public Pizza getPizza()
	{
		return pOrder;
	}// end getPizza

	/**
	 * this method returns the number of pizzas in an order
	 * @return numberPizza is the number of pizzas being ordered
	 */
	public int getNumber()
	{
		return numberPizza;
	}// end getNumber
	/**
	 * this method sets the number of pizzas in an order
	 * @param n is the number of desired pizzas
	 * @throws IllegalPizza if order size is not between 1-100 inclusive
	 */
	public void setNumber(int n) throws IllegalPizza
	{
		if (n >0 && n<101)
		{
			numberPizza = n;
		}// end if
		else
		{
			throw new IllegalPizza("Incorrect number of pizzas");
		}// end else
	}// end setNumber

	/**
	 * this method returns the total cost of an order
	 * @return numberPizza is total cost of an order
	 */
	public double getCost()
	{
		return pOrder.getCost()*numberPizza;
	}// end getCost

	/**
	 * this method returns the correct string representation of an order
	 * @return s is the correct string representation of an order
	 */
	public String toString()
	{
		String s;
		if (numberPizza <10)
		{
			s = " " + numberPizza + " " +pOrder.toString();
		}// end if
		else
		{
			s = numberPizza + " " +pOrder.toString();
		}// end else
		return s;
	}

	/**
	 * This method overrides the compareTo method. it compares two LineItem objects. returns 0 if the cost is within 1 dollar.
	 * returns a negative integer if the parameter LineItem costs less and positive if it costs more
	 * @param o is the LineItem to be compared to.
	 */
	public int compareTo(LineItem o) {
		if (Math.abs(o.getCost()-getCost()) < 1)
		{
			return 0;
		}// end if
		return (int)(o.getCost()-getCost());
	}

}
