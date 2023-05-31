package library.management;

public class Loan
{
	private int loanId;
	private int storyId;
	private int readerId;
	private int loanNo;
	private String loanDate;
	private String storyReturnAppointmentDate;
	private String storyReturnDate;
	private String status;
	
	public Loan()
	{
		
	}
	
	public Loan(int loanId, int storyId, int readerId, int loanNo, String loanDate, String storyReturnAppointmentDate,
			String storyReturnDate, String status)
	{
		this.loanId = loanId;
		this.storyId = storyId;
		this.readerId = readerId;
		this.loanNo = loanNo;
		this.loanDate = loanDate;
		this.storyReturnAppointmentDate = storyReturnAppointmentDate;
		this.storyReturnDate = storyReturnDate;
		this.status = status;
	}

	public int getLoanId()
	{
		return loanId;
	}
	
	public void setLoanId(int loanId)
	{
		this.loanId = loanId;
	}
	public int getStoryId() 
	{
		return storyId;
	}

	public void setStoryId(int storyId) 
	{
		this.storyId = storyId;
	}

	public int getReaderId() 
	{
		return readerId;
	}

	public void setReaderId(int readerId)
	{
		this.readerId = readerId;
	}

	public int getLoanNo()
	{
		return loanNo;
	}

	public void setLoanNo(int loanNo) 
	{
		this.loanNo = loanNo;
	}

	public String getLoanDate()
	{
		return loanDate;
	}

	public void setLoanDate(String loanDate) 
	{
		this.loanDate = loanDate;
	}

	public String getStoryReturnAppointmentDate()
	{
		return storyReturnAppointmentDate;
	}

	public void setStoryReturnAppointmentDate(String storyReturnAppointmentDate) 
	{
		this.storyReturnAppointmentDate = storyReturnAppointmentDate;
	}

	public String getStoryReturnDate()
	{
		return storyReturnDate;
	}

	public void setStoryReturnDate(String storyReturnDate) 
	{
		this.storyReturnDate = storyReturnDate;
	}	
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
}
