package library.management;

public class Punishment
{
	private int loanId;
	private int readerId;
	private String fullname;
	private int storyId;
	private String story;
	private int loanNo;
	private int daysLate;
	private int total;
	private String status;
	
	
	public Punishment() 
	{
		
	}

	

	public Punishment(int loanId, int readerId, String fullname, int storyId, String story, int loanNo, int daysLate,
			int total, String status)
	{
		this.loanId = loanId;
		this.readerId = readerId;
		this.fullname = fullname;
		this.storyId = storyId;
		this.story = story;
		this.loanNo = loanNo;
		this.daysLate = daysLate;
		this.total = total;
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

	public int getReaderId() 
	{
		return readerId;
	}

	public void setReaderId(int readerId) 
	{
		this.readerId = readerId;
	}

	public String getFullname() 
	{
		return fullname;
	}

	public void setFullname(String fullname)
	{
		this.fullname = fullname;
	}

	

	public int getStoryId() {
		return storyId;
	}



	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}



	public String getStory() {
		return story;
	}



	public void setStory(String story) {
		this.story = story;
	}



	public int getLoanNo() 
	{
		return loanNo;
	}

	public void setLoanNo(int loanNo) 
	{
		this.loanNo = loanNo;
	}

	public int getDaysLate()
	{
		return daysLate;
	}

	public void setDaysLate(int daysLate) 
	{
		this.daysLate = daysLate;
	}

	public int getTotal() 
	{
		return total;
	}

	public void setTotal(int total) 
	{
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	


	
	
}
