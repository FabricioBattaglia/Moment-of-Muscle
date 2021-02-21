package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Muscle;

public class TestMuscle 
{

	@Test
	public void test_LoginCheck() 
	{
		String email = "howardytompson@gmail.com";
		String password = "SuperCool09!";
		
		Muscle muscle = new Muscle();
		
		boolean correct = muscle.checkInput(email, password);
		
		assertTrue(correct = true);
	}
	
	@Test
	public void account_creation()
	{
		Muscle muscle = new Muscle();
		
		
		String username = "Different";
		
		String word = muscle.Storage();
		
		assertNotSame(word, username);
		
	}
	
	@Test
	public void view_profile()
	{
		Muscle muscle = new Muscle();
		
		String button = "Profile";
		
		String user = "Joey: 45";
	
		
		String view = muscle.File(button, user);
		
		assertSame(view, user);
		
	}
	
	@Test
	public void job_category()
	{
		Muscle muscle = new Muscle();
		
		String job = "Building";
		int jobtype = 5;
		
		String occupation = muscle.List(job, jobtype);
		
		assertTrue(occupation == job);
		
	}

	
	

}
