package library.management;

public class Statistic {
	private int totalStory;
	private int totalLoan;
	private int loan;
	private int punish;
	
	public Statistic()
	{
		
	}
	
	public Statistic(int totalStory, int totalLoan, int loan, int punish)
	{
		this.totalStory= totalStory;
		this.totalLoan = totalLoan;
		this.loan = loan;
		this.punish = punish;
	}

	

	public int getTotalStory() {
		return totalStory;
	}

	public void setTotalStory(int totalStory) {
		this.totalStory = totalStory;
	}

	public int getTotalLoan() {
		return totalLoan;
	}

	public void setTotalLoan(int totalLoan) {
		this.totalLoan = totalLoan;
	}

	public int getLoan() {
		return loan;
	}

	public void setLoan(int loan) {
		this.loan = loan;
	}

	public int getPunish() {
		return punish;
	}

	public void setPunish(int punish) {
		this.punish = punish;
	}
}
