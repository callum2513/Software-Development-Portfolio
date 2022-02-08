package Assignment;


import java.time.LocalDate;
import java.time.LocalDateTime;


public class Application 
{
		private String forename, surname, telNum, email;
		private int ucasPoints;
		private boolean placeConfirmed;
		private LocalDate currDate;
		
		public Application()
		{
			this.forename = "";
			this.surname = "";
			this.telNum = "";
			this.email = "";
			this.currDate =  LocalDate.now();
			this.ucasPoints = 0;
			this.placeConfirmed = false;	
		}
		public Application (String forename, String surname, String telNum, String email, LocalDate currDate, int ucasPoints, boolean placeConfirmed)
		{
			this.forename = forename;
			this.surname = surname;
			this.telNum = telNum;
			this.email = email;
			this.currDate = currDate;
			this.ucasPoints = ucasPoints;
			this.placeConfirmed = placeConfirmed;	
		}
		// Accessors //
		public String getForename() 
		{
			return forename;
		}
		public void setForename(String forename) 
		{
			this.forename = forename;
		}
		public String getSurname() 
		{
			return surname;
		}
		public void setSurname(String surname) 
		{
			this.surname = surname;
		}
		public String getTelNum()
		{
			return telNum;
		}
		public void setTelNum(String telNum) 
		{
			this.telNum = telNum;
		}
		public String getEmail() 
		{
			return email;
		}
		public void setEmail(String email) 
		{
			this.email = email;
		}
		public LocalDate getDateOfApplication()
	{
			return currDate;
		}
		public void setDateOfApplication(LocalDate currDate) 
		{
			this.currDate = currDate;
		}
		public int getUcasPoints() 
		{
			return ucasPoints;
		}
		public void setUcasPoints(int ucasPoints) 
		{
			this.ucasPoints = ucasPoints;
		}
		public boolean isPlaceConfirmed()
		{
			return placeConfirmed;
		}
		public void setPlaceConfirmed(boolean placeConfirmed) 
		{
			this.placeConfirmed = placeConfirmed;
		}
		public int priorityLevel()
		{
			int priority = 0;
			
			if (ucasPoints >= 120)
			{
				priority = 1; 
			}
			else if (ucasPoints >= 100)
			{
				priority = 2;
			}
			else 
			{
				priority = 3;
			}
			return priority;	
		}
		
	
	}




