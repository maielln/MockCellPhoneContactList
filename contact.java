public class contact
{
	private String fname;
	private String lname;
	private String number;
	private String email;

	public contact(String f, String l, String n, String e)
	{
		fname = f;
		lname = l;
		number = n;
		email = e;
	}

	public String getFName()
	{
		return fname;
	}

	public String getLName()
	{
		return lname;
	}

	public String getNumber()
	{
		return number;
	}

	public String getEmail()
	{
		return email;
	}

	public void setFName(String f)
	{
		fname = f;
	}

	public void setLName(String l)
	{
		lname = l;
	}

	public void setNumber(String n)
	{
		number = n;
	}

	public void setEmail(String e)
	{
		email = e;
	}
}