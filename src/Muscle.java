package main;

public class Muscle {

	public boolean checkInput(String email, String password) {
		// TODO Auto-generated method stub
		
		if(email == "howardytompson@gmail.com" && password == "SuperCool09!")
			return true;
		
		return false;
	}

	public String Storage() 
	{
		// TODO Auto-generated method stub
		return "Exist";
		
	}

	public String List(String job, int jobtype) {
		// TODO Auto-generated method stub
		
		switch(jobtype)
		{
		case 1: return "Cooking";
		case 2: return "Mover";
		case 3: return "Gardening";
		case 4: return "Lifting";
		case 5: return "Building";
		}
		return null;
	}

	public String File(String button, String user) {
		// TODO Auto-generated method stub
		
		
		String arr[] = {"Mohawk: 30", "Uber: 20", "Joey: 45"};
		
		for(int i = 0; i < 3; i++)
		{
			if(user == arr[i])
				return arr[i];
			
			else
				continue;
		}
		
		return null;
		
	}


	

}
