package Assignment;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class OrderedList 
{
	ListNode start = null;
	ListNode end = null;
	ListNode appFound = null;
	
	OrderedList()
	{
		start = null;
		end = null;
	}
	
	public void add (Application applicationList)
	{
		ListNode next =  new ListNode (applicationList);
		if (start == null)
		{
			start = next; 
			end = next;
		}
		else
		{
			if (next.getApp().priorityLevel() < start.getApp().priorityLevel())
			{
				next.setNext(start); // puts it first
				start = next; // start pointer now goes to the new item
			}
			else
			{
				if (next.getApp().priorityLevel() >= end.getApp().priorityLevel())
				{
					end.setNext(next); // link the last one to the new one
					end = next; 	   // Make the new one the last
				}
				else
				{
					ListNode prev;
					ListNode curr;
					prev = start; // prev refers to the first item
					curr = start.getNext(); // curr refers to the second
					
					while(curr.getApp().priorityLevel() < next.getApp().priorityLevel())
					{
						prev = curr;
						curr = curr.getNext();
					}
					
					prev.setNext(next);
					next.setNext(curr);
				}
			}
		}
	}
	public boolean remove (Application appFound)
	{
		ListNode prev;
		ListNode curr;
		boolean removed = false;
		
		if (appFound == start.getApp())
		{
			curr = start;
			start = start.getNext();
			curr = null;
			removed = true;
		}
		else
		{
			prev = start;
			curr = start.getNext();
			
			while(curr.getApp() != appFound && curr != null)
			{
				prev = curr;
				curr = curr.getNext();
			}
			
			if (curr != null)
			{
				prev.setNext(curr.getNext());
				removed = true;
			}	
		}
		return removed;
	}
	public int getTop()
	{
		return start.getApp().getUcasPoints();
	}
	public String Display()
	{
		String s = "";
		ListNode current = start;
		
		while(current != null)
		{
			s += String.format("   %-11s %-15s %-26s %-18s %-15s %-12s %-3s", current.getApp().getForename(), current.getApp().getSurname(), current.getApp().getEmail() 
			,current.getApp().getTelNum(), current.getApp().getDateOfApplication(), current.getApp().getUcasPoints(), current.getApp().priorityLevel());
			current = current.getNext();
			s += "\n";
		}
		
		return s;
	}
	public String displayAllocated(int allocationNum)
	{
		String s = "";
		ListNode current = start;
		
		for (int count = 0; count < allocationNum; count ++)
		{
			s += String.format("   %-11s %-15s %-26s %-18s %-15s %-12s %-3s", current.getApp().getForename(), current.getApp().getSurname(), current.getApp().getEmail() 
			,current.getApp().getTelNum(), current.getApp().getDateOfApplication(), current.getApp().getUcasPoints(), current.getApp().priorityLevel());
			current = current.getNext();
			s += "\n";
		}
		
		return s;
	}
	public String sortByUcasPoints()
	{
		// Creating a string, current variable (start of list) and a temp application //
		String s = "";
		ListNode current = start;
		Application temp = new Application();
		
		// Array of Applications //
		Application[] appIndex = new Application[appCount()];
		
		// Filling out Array with Applications //
		for (int count = 0; count < appCount(); count ++)
		{
			appIndex[count] = current.getApp();
			current = current.getNext();
		}
		// loops through array and orders applications by ucas points descending //
		for (int count = 0; count < appCount() - 1; count ++)
		{
			for (int count2 = count + 1; count2 < appCount(); count2 ++)
			{
			if (appIndex[count2].getUcasPoints() > appIndex[count].getUcasPoints())
			{
				temp = appIndex[count];
				appIndex[count] = appIndex[count2];
				appIndex[count2] = temp;
			}
			}
		}
		
		for (int count = 0; count < appCount(); count++)
		{	
			s += String.format("   %-11s %-15s %-26s %-18s %-15s %-12s %-3s", appIndex[count].getForename(), appIndex[count].getSurname(), appIndex[count].getEmail() 
			,appIndex[count].getTelNum(), appIndex[count].getDateOfApplication(), appIndex[count].getUcasPoints(), appIndex[count].priorityLevel());
			s += "\n";	
		}

		return s;
	}
	public String sortBySurnameAZ()
	{
		// Creating a string, current variable (start of list) and a temp application //
		String s = "";
		ListNode current = start;
		Application temp = new Application();
		
		// Array of Applications //
		Application[] appIndex = new Application[appCount()];
		
		// Filling out Array with Applications //
		for (int count = 0; count < appCount(); count ++)
		{
			appIndex[count] = current.getApp();
			current = current.getNext();
		}
		// loops through array and orders applications based on surname A-Z //
		for (int count = 0; count < appCount() - 1; count ++)
		{
			for (int count2 = count + 1; count2 < appCount(); count2 ++)
			{
			if (appIndex[count2].getSurname().compareTo(appIndex[count].getSurname()) < 0)
			{
				temp = appIndex[count];
				appIndex[count] = appIndex[count2];
				appIndex[count2] = temp;
			}
			}
		}
		
		for (int count = 0; count < appCount(); count++)
		{	
			s += String.format("   %-11s %-15s %-26s %-18s %-15s %-12s %-3s", appIndex[count].getForename(), appIndex[count].getSurname(), appIndex[count].getEmail() 
			,appIndex[count].getTelNum(), appIndex[count].getDateOfApplication(), appIndex[count].getUcasPoints(), appIndex[count].priorityLevel());
			s += "\n";	
		}

		return s;
	}
	public String sortBySurnameZA()
	{
		// Creating a string, current variable (start of list) and a temp application //
		String s = "";
		ListNode current = start;
		Application temp = new Application();
		
		// Array of Applications //
		Application[] appIndex = new Application[appCount()];
		
		// Filling out Array with Applications //
		for (int count = 0; count < appCount(); count ++)
		{
			appIndex[count] = current.getApp();
			current = current.getNext();
		}
		// loops through array and orders applications based on surname Z-A //
		for (int count = 0; count < appCount() - 1; count ++)
		{
			for (int count2 = count + 1; count2 < appCount(); count2 ++)
			{
			if (appIndex[count2].getSurname().compareTo(appIndex[count].getSurname()) > 0)
			{
				temp = appIndex[count];
				appIndex[count] = appIndex[count2];
				appIndex[count2] = temp;
			}
			}
		}
		
		for (int count = 0; count < appCount(); count++)
		{	
			s += String.format("   %-11s %-15s %-26s %-18s %-15s %-12s %-3s", appIndex[count].getForename(), appIndex[count].getSurname(), appIndex[count].getEmail() 
			,appIndex[count].getTelNum(), appIndex[count].getDateOfApplication(), appIndex[count].getUcasPoints(), appIndex[count].priorityLevel());
			s += "\n";	
		}

		return s;
	}
	public String sortByEmailAZ()
	{
		
		String s = "";
		ListNode current = start;
		Application temp = new Application();
		
		// Array of Applications //
		Application[] appIndex = new Application[appCount()];
		
		// Filling out Array with Applications //
		for (int count = 0; count < appCount(); count ++)
		{
			appIndex[count] = current.getApp();
			current = current.getNext();
		}
		
		// loops through array and orders applications based on email A-Z //
		for (int count = 0; count < appCount() - 1; count ++)
		{
			for (int count2 = count + 1; count2 < appCount(); count2 ++)
			{
			if (appIndex[count2].getEmail().compareTo(appIndex[count].getEmail()) < 0)
			{
				temp = appIndex[count];
				appIndex[count] = appIndex[count2];
				appIndex[count2] = temp;
			}
			}
		}
		
		for (int count = 0; count < appCount(); count++)
		{	
			s += String.format("   %-11s %-15s %-26s %-18s %-15s %-12s %-3s", appIndex[count].getForename(), appIndex[count].getSurname(), appIndex[count].getEmail() 
			,appIndex[count].getTelNum(), appIndex[count].getDateOfApplication(), appIndex[count].getUcasPoints(), appIndex[count].priorityLevel());
			s += "\n";	
		}

		return s;
	}
	public String sortByEmailZA()
	{
		// Creating a string, current variable (start of list) and a temp application //
		String s = "";
		ListNode current = start;
		Application temp = new Application();
		
		// Array of Applications //
		Application[] appIndex = new Application[appCount()];
		
		// Filling out Array with Applications //
		for (int count = 0; count < appCount(); count ++)
		{
			appIndex[count] = current.getApp();
			current = current.getNext();
		}
		// loops through array and orders applications based on email Z-A //
		for (int count = 0; count < appCount() - 1; count ++)
		{
			for (int count2 = count + 1; count2 < appCount(); count2 ++)
			{
			if (appIndex[count2].getEmail().compareTo(appIndex[count].getEmail()) > 0)
			{
				temp = appIndex[count];
				appIndex[count] = appIndex[count2];
				appIndex[count2] = temp;
			}
			}
		}
		
		for (int count = 0; count < appCount(); count++)
		{	
			s += String.format("   %-11s %-15s %-26s %-18s %-15s %-12s %-3s", appIndex[count].getForename(), appIndex[count].getSurname(), appIndex[count].getEmail() 
			,appIndex[count].getTelNum(), appIndex[count].getDateOfApplication(), appIndex[count].getUcasPoints(), appIndex[count].priorityLevel());
			s += "\n";	
		}

		return s;
	}
	public String sortByPriority()
	{
		// Creating a string, num, current variable (start of list) and a temp application //
		String s = "";
		int num = 0;
		ListNode current = start;
		Application temp = new Application();
		
		// Array of Applications //
		Application[] appIndex = new Application[appCount()];
		
		// Filling out Array with Applications //
		for (int count = 0; count < appCount(); count ++)
		{
			appIndex[count] = current.getApp();
			current = current.getNext();
		}
		// loops through array and orders applications based on priority level ascending //
		for (int count = 0; count < appCount() - 1; count ++)
		{
			for (int count2 = count + 1; count2 < appCount(); count2 ++)
			{
			if (appIndex[count2].priorityLevel() < appIndex[count].priorityLevel())
			{
				temp = appIndex[count];
				appIndex[count] = appIndex[count2];
				appIndex[count2] = temp;
			}
			}
		}
		
		for (int count = 0; count < appCount(); count++)
		{	
			s += String.format("   %-11s %-15s %-26s %-18s %-15s %-12s %-3s", appIndex[count].getForename(), appIndex[count].getSurname(), appIndex[count].getEmail() 
			,appIndex[count].getTelNum(), appIndex[count].getDateOfApplication(), appIndex[count].getUcasPoints(), appIndex[count].priorityLevel());
			s += "\n";	
		}

		return s;
	}
	public boolean sameEmail(String email)
	{
		boolean dupEmail = false;
		ListNode current = start;
		
		// Array of Applications //
		Application[] appIndex = new Application[appCount()];
		
		// Filling out Array with Applications //
		for (int count = 0; count < appCount(); count ++)
		{
			appIndex[count] = current.getApp();
			current = current.getNext();
		}
		// loops to check if any email for any application matches the input, if it does, returns true //
			for (int count = 0; count < appCount(); count ++)
			{
				if (email.equals(appIndex[count].getEmail()))
				{
					dupEmail = true;
				}
		}
		return dupEmail;
	}
	public boolean Isempty()
	{
		if (start == null)
		{
			return true;
		}
			else
			{
				return false;
			}
	}
	public Application search (String email)
	{
		
		ListNode current = start;
		while(current != null && current.getApp().getEmail().equals(email) != true)
		{
			current = current.getNext();
		}
		if (current != null)
			return current.getApp();
		else
			JOptionPane.showMessageDialog(null, "ERROR: Candidate Email not found - Please Try Again");
			return null;
			
	}
	public int appCount()
	{
		ListNode current = start;
		int appNum = 0;
		
		while (current != null)
		{
			appNum++;
			current = current.getNext();
		}
		
		return appNum;
	}

}

