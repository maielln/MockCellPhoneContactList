import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
//-----------------------------------------------------------------------------------------------
//Name: Nicole Maiello \_/(ouo)\_/
//Date: 2/24/16
//Program: Contact Program with Binary Search Modification - 2B
//Decription: Storing a database of contacts using the modified binary search method
//------------------------------------------------------------------------------------------------

public class ContactTest
{
	public static void main(String[] args) throws IOException
	{
		cellPhone = importContacts();

		int input;

		do
		{
			input = options();
			if(input == 1)
			{
				makeContact();
			}
			else if(input == 2)
			{
				editContact();
			}
			else if(input == 3)
			{
				deleteContact();
			}
			else if(input ==4)
			{
				printContacts();
			}
			else if(input == 5)
			{
				return;
			}
			else
			{
				System.out.println("Please enter an option to do.");
			}
			System.out.println();
		}
		while (input != 5);
		System.out.println();
	}

	//--------------------------------------------------------------------------------
	//Postcondtion: Displays the options of the contact list onto the screen
	//--------------------------------------------------------------------------------
	public static int options() throws IOException
	{
			System.out.println("Choose one of the following options below:");
			System.out.println("------------------------------------------");
			System.out.println("1. Make a new contact");
			System.out.println("2. Edit a current contact");
			System.out.println("3. Delete a current contact");
			System.out.println("4. Print Contacts");
			System.out.println("5. End program");
			System.out.println();
			String input = console.nextLine();
			if ("12345".indexOf(input) >= 0)
			{
				int num = Integer.parseInt(input);
				return num;
			}
			else return -1;

	}

	//--------------------------------------------------------------------------------
	//Precondition: Checks to verify that the name isn't duplicated for a contact. If
	//				so, the contact is added to the array list otherwise, void.
	//Postcondition: Array list has new contacts
	//@param: myCellPhoneList - holds the contacts
	//--------------------------------------------------------------------------------
	public static void makeContact() throws IOException
	{
		System.out.println("Please enter the first name:");
		String first = console.nextLine();
		System.out.println();

		System.out.println("Please enter the last name:");
		String last = console.nextLine();

		int found1 = mySearcher.searchL(last);

		int found2 = mySearcher.searchF(first);

		if(found1 < 0 || found2 < 0)
		{
			System.out.println("That name is available.");
			System.out.println("What is the cell phone number of the contact? (xxx-xxx-xxxx)");
			String number = console.nextLine();

			System.out.println("What is the email address of the contact?");
			String email = console.nextLine();

			if(found2 < 0)
				cellPhone.add((-found2 - 1), new contact(first, last, number, email));
			else
				cellPhone.add(found2, new contact(first, last, number, email));
		}
		else
		{
			System.out.println("That contact is already in the phone.");
		}
		System.out.println();
	}

	//--------------------------------------------------------------------------------
	//Precondition: Checks to verify that there are contacts to edit. If so, searches
	//				for the contact. If found, user is prompted as to what to edit
	//				Edit is made, contact is updated and stored back into the array list
	//Postcondition: Array list has new editted contact
	//@param: myCellPhoneList - holds the contact
	//--------------------------------------------------------------------------------

	public static void editContact() throws IOException
	{
		if(cellPhone.size() == 0)
			System.out.println("There are no contacts to edit");
		else
		{
			System.out.print("Enter the contact number you wish to edit: ");
			String input = console.nextLine();
			int value = Integer.parseInt(input);

			if (value > cellPhone.size() + 1 || value < 1)
				System.out.println("Sorry! You have entered an invalid contact");
			else
			{
				System.out.println("That contact has been found");
				System.out.println("What would you like to edit about this contact?");
				System.out.println("1. First Name");
				System.out.println("2. Last Name");
				System.out.println("3. Cell Phone Number");
				System.out.println("4. Email");
				String choice = console.nextLine();
				int selection = Integer.parseInt(choice);

				contact temp = (contact)cellPhone.get(value - 1);

				if(selection == 1)
				{
					System.out.println("Enter the new first name:");
					temp.setFName(console.nextLine());
				}
				else if(selection == 2)
				{
					System.out.println("Enter the new last name:");
					temp.setLName(console.nextLine());
				}
				else if (selection == 3)
				{
					System.out.println("Enter the new cell phone number:");
					temp.setNumber(console.nextLine());
				}
				else if (selection == 4)
				{
					System.out.println("Enter the new email:");
					temp.setEmail(console.nextLine());
				}
				else
					System.out.println();
			}
		}
		System.out.println();
	}
	//--------------------------------------------------------------------------------
	//Precondition: Checks to verify that a contact is available for removal. If
	//				so, the appointment is searched for. If found, element is removed form
	//				array list.
	//Postcondition: Array list has been updated and contact has been deleted
	//@param: myCellPhoneList - holds the contact
	//--------------------------------------------------------------------------------
	public static void deleteContact() throws IOException
	{
		if(cellPhone.size() == 0)
			System.out.println("There are no contacts to delete");
		else
		{
			System.out.println("Enter the contact number you wish to delete:");
			int value = console.nextInt();
			if (value > cellPhone.size() || value < 1)
				System.out.println("There is not a contact for that number.");
			else
			{
				cellPhone.remove(value-1);
				System.out.println("That contact has been removed successfully");
			}
		}
		System.out.println();
	}
	//--------------------------------------------------------------------------------
	//Postcondition: Prints all the contacts in the array list
	//@param: myCellPhoneList - holds the Contacts
	//--------------------------------------------------------------------------------
	public static void printContacts()
	{
		if (cellPhone.size() == 0)
		{
			System.out.println("There are no contacts scheduled");
		}
		else
		{
			System.out.println("Below are your Contacts that are scheduled");
			System.out.println("----------------------------------------------");
			for(int i = 0; i < cellPhone.size(); i++)
			{
				contact c = cellPhone.get(i);
				System.out.println((i+1) + "." + "\n" + c.getFName() + " " + c.getLName() + "\n" + c.getNumber() + "\n" + c.getEmail() + "\n");
			}
		}
		System.out.println();
	}

	//--------------------------------------------------------------------------------------
	//Postcondition: Fills the array list with Contacts in sorted order from a text file
	//
	//--------------------------------------------------------------------------------------
	public static ArrayList<contact> importContacts()
	{
		try
		{
			FileReader reader = new FileReader("Contacts.txt");
			BufferedReader in = new BufferedReader(reader);
			boolean done = false;

			while(!done)
			{
				String line = in.readLine();
				if(line == null)
					done = true;
				else
				{
					StringTokenizer tokenizer = new StringTokenizer(line);

					while(tokenizer.hasMoreElements())
					{
						String fname = tokenizer.nextToken();
						String lname = tokenizer.nextToken();
						String phone = tokenizer.nextToken();
						String email = tokenizer.nextToken();

						contact Contact = new contact(fname, lname, phone, email);

						int found = mySearcher.searchL(lname);
						if (found < 0)
						{
							found = mySearcher.searchF(fname);
							if (found < 0)
							{
								cellPhone.add(-found - 1, Contact);
							}
						}
					}
				}
			}
			reader.close();
		}
		catch(IOException x)
		{
			System.out.println("Error reading in Contacts");
		}
		return cellPhone;
	}

	//-------------------------------------------------------------------------------------
	//Global Variables that are used throughout the entire program
	//-------------------------------------------------------------------------------------
	public static ArrayList<contact> cellPhone = new ArrayList<contact>();
	public static Scanner console = new Scanner(System.in);
	public static BinarySearcher mySearcher = new BinarySearcher(cellPhone);




}