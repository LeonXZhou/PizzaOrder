package application;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PizzaOrderController
{
	//GUI decelerations
	@FXML 
	private RadioButton pepperoniTriple;
	@FXML
	private RadioButton pepperoniDouble;
	@FXML
	private RadioButton hamTriple;
	@FXML
	private RadioButton hamDouble;
	@FXML
	private RadioButton pepperoniSingle;
	@FXML
	private RadioButton pepperoniNone;
	@FXML
	private RadioButton cheeseSingle;
	@FXML
	private RadioButton hamSingle;
	@FXML
	private RadioButton hamNone;
	@FXML
	private RadioButton sizeSmall;
	@FXML
	private ToggleGroup Cheese;
	@FXML
	private ToggleGroup Ham;
	@FXML
	private ToggleGroup Pepperoni;
	@FXML
	private ToggleGroup Size;
	@FXML
	private TextField orderNumber;
	@FXML 
	private TextField costTextT;
	@FXML 
	private TextField totalCost;
	@FXML 
	private TextField costTextP;
	@FXML
	private Button saveLineItem;
	@FXML
	private Button saveAll;
	@FXML
	private TextArea orderText;
	@FXML
	private ImageView pImage;
	private Image cI = new Image("Cheese.png");
	private Image sPI = new Image("singlePEP.png");
	private Image sHI = new Image("singleHAM.png");
	private Image dHI = new Image("doubleHAM.png");
	private Image tHI = new Image("tripleHAM.png");
	private Image dPI = new Image("doublePEP.png");
	private Image tPI = new Image("triplePEP.png");
	private Image sPsHI = new Image("singleHAMsinglePEP.png");
	private Image sPdHI = new Image("DoubleHAMsinglePEP.png");
	private Image dPsHI = new Image("singleHAMdoublePEP.png");
	//end of GUI Declarations
	
	//Pizza Variables
	private int pep = 1;
	private int ham = 0;
	private int cheese = 1;
	private String pSize = "Small";
	private int numberOfPizza = 1;
	private static ArrayList<LineItem> orders = new ArrayList<>();
	//end of Pizza Variables
	
	// This method sets the pepperoni value
	private void setPep(int p)
	{
		pep = p;
	}
	// This method sets the ham value
	private void setHam(int h)
	{
		ham = h;
	}
	// This method sets the cheese value
	private void setCheese(int c)
	{
		cheese = c;
	}
	
	// This method sets the size of the Pizza
	private void setSize(String s)
	{
		pSize = s;
	}
	
	// This method sets the number of Pizza
	private void setNumberOfPizza(int n)
	{
		numberOfPizza = n;
	}
	
	// This method sets the image of the Pizza
	private void setImage()
	{
		if ((pep == 0)&&(ham == 0))
		{
			pImage.setImage(cI);
		}
		if ((pep == 1)&&(ham == 0))
		{
			pImage.setImage(sPI);
		}
		if ((pep == 2)&&(ham == 0))
		{
			pImage.setImage(dPI);
		}
		if ((pep == 3)&&(ham == 0))
		{
			pImage.setImage(tPI);
		}
		if ((pep == 0)&&(ham == 1))
		{
			pImage.setImage(sHI);
		}
		if ((pep == 0)&&(ham == 2))
		{
			pImage.setImage(dHI);
		}
		if ((pep == 0)&&(ham == 3))
		{
			pImage.setImage(tHI);
		}
		if ((pep == 1)&&(ham == 1))
		{
			pImage.setImage(sPsHI);
		}
		if ((pep == 1)&&(ham == 2))
		{
			pImage.setImage(sPdHI);
		}
		if ((pep == 2)&&(ham == 1))
		{
			pImage.setImage(dPsHI);
		}
	}
	
	// This method displays the current price of the current pizza and current order
	private void displayPizza()
	{
		try
		{
			Pizza check = new Pizza(pSize,cheese,ham,pep);
			costTextT.setText("$"+String.format("%.2f", (check.getCost())*numberOfPizza));
			costTextP.setText("$"+String.format("%.2f", (check.getCost())));
		}
		catch (IllegalPizza ip) 
		{
			System.out.println(ip.getMessage());
		}
	}
	
	// This method returns the total cost of entire Order
	private double totalCost()
	{
		double totalCost = 0;
		if (orders.size() != 0)
		{
			for (LineItem order : orders) {
				totalCost += order.getCost();
			}
		}
		return totalCost;
	}
	
	
	// This method writes the Orders to a .dat file
	private static void writeOrders() {
    	String outputFile = "Orders.dat";
    	try (ObjectOutputStream binFileOut = new ObjectOutputStream(new FileOutputStream(outputFile))) {
            binFileOut.writeObject(orders);
        } catch (FileNotFoundException err) {
            System.out.println(err.getMessage());
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
	

	@FXML
	void initialize()
	{
		// Initializes all fields to their correct default values
		setImage();
		pepperoniSingle.selectedProperty().set(true);
		hamNone.selectedProperty().set(true);
		cheeseSingle.selectedProperty().set(true);
		sizeSmall.selectedProperty().set(true);
		displayPizza();
		
		// Interprets the selection of cheese amount inputed from radio buttons.
		Cheese.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> 
		{
			if (((RadioButton)new_toggle).getId().equals("cheeseSingle"))
			{
				setCheese(1);
				displayPizza();
			}
			if (((RadioButton)new_toggle).getId().equals("cheeseDouble"))
			{
				setCheese(2);
				displayPizza();
			}
			if (((RadioButton)new_toggle).getId().equals("cheeseTriple"))
			{
				setCheese(3);
				displayPizza();
			}
		});
		Pepperoni.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> 
		{
			if (((RadioButton)new_toggle).getId().equals("pepperoniTriple"))
			{
				setPep(3);
				hamTriple.setDisable(true);
				hamDouble.setDisable(true);
				hamSingle.setDisable(true);
				displayPizza();
				setImage();
			}
			if (((RadioButton)new_toggle).getId().equals("pepperoniDouble"))
			{
				setPep(2);
				hamTriple.setDisable(true);
				hamDouble.setDisable(true);
				hamSingle.setDisable(false);
				displayPizza();
				setImage();
			}
			if (((RadioButton)new_toggle).getId().equals("pepperoniSingle"))
			{
				setPep(1);
				hamTriple.setDisable(true);
				hamDouble.setDisable(false);
				hamSingle.setDisable(false);
				displayPizza();
				setImage();
			}
			if (((RadioButton)new_toggle).getId().equals("pepperoniNone"))
			{
				setPep(0);
				hamTriple.setDisable(false);
				hamDouble.setDisable(false);
				hamSingle.setDisable(false);
				displayPizza();
				setImage();
			}
		});

		Ham.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> 
		{
			if (((RadioButton)new_toggle).getId().equals("hamTriple"))
			{
				setHam(3);
				pepperoniTriple.setDisable(true);
				pepperoniDouble.setDisable(true);
				pepperoniSingle.setDisable(true);
				pepperoniNone.selectedProperty().set(true);
				setPep(0);
				displayPizza();
				setImage();
			}
			if (((RadioButton)new_toggle).getId().equals("hamDouble"))
			{
				setHam(2);
				pepperoniTriple.setDisable(true);
				pepperoniDouble.setDisable(true);
				pepperoniSingle.setDisable(false);
				displayPizza();
				setImage();
			}
			if (((RadioButton)new_toggle).getId().equals("hamSingle"))
			{
				setHam(1);
				pepperoniTriple.setDisable(true);
				pepperoniDouble.setDisable(false);
				pepperoniSingle.setDisable(false);
				displayPizza();
				setImage();
			}
			if (((RadioButton)new_toggle).getId().equals("hamNone"))
			{
				setHam(0);
				pepperoniTriple.setDisable(false);
				pepperoniDouble.setDisable(false);
				pepperoniSingle.setDisable(false);
				displayPizza();
				setImage();
			}
		});

		Size.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> 
		{
			if (((RadioButton)new_toggle).getId().equals("sizeSmall"))
			{
				setSize("Small");
				displayPizza();
			}
			if (((RadioButton)new_toggle).getId().equals("sizeMedium"))
			{
				setSize("Medium");
				displayPizza();
			}
			if (((RadioButton)new_toggle).getId().equals("sizeLarge"))
			{
				setSize("Large");
				displayPizza();
			}
		});

		orderNumber.textProperty().addListener((observableValue, oldText, newText) ->
		{
			if (newText != null && !newText.isEmpty()) 
			{
				try 
				{
					int aVal = Integer.parseInt(newText);
					if(aVal>100 || aVal <1)
					{
						((StringProperty)observableValue).setValue(oldText);
					}
					else
					{
						setNumberOfPizza(aVal);
						displayPizza();
					}
				}
				catch (NumberFormatException e)
				{
					((StringProperty)observableValue).setValue(oldText);
				}

			}
		});

		saveLineItem.setOnAction(event -> 
		{
			try
			{
				Pizza p = new Pizza(pSize,cheese,ham,pep);
				LineItem i = new LineItem(numberOfPizza,p);
				orders.add(i);
				orderText.setText(orderText.getText()+i.toString()+"\n");
				totalCost.setText("$"+String.format("%.2f", totalCost()));
			}
			catch (IllegalPizza ip) 
			{
				System.out.println(ip.getMessage());
			}
		});
		
		saveAll.setOnAction(event -> 
		{
			writeOrders();
		});
		
	}

}
