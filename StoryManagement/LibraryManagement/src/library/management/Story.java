package library.management;

public class Story
{
	private int storyId;
	private String storyName;
	private String pageNo;
	private String language;
	private int price;
	private int amount;
	private String publishYear;
	private String type;
	private String author;
	private String publisher;
	private int chapterNo;
	public Story() 
	{
		
	}
	
	public Story(int storyId, String storyName, String pageNo, String language, int price, int amount, String publishYear,
			String type, String author, String publisher, int ChapterNo) {
		super();
		this.storyId = storyId;
		this.storyName = storyName;
		this.pageNo = pageNo;
		this.language = language;
		this.price = price;
		this.amount = amount;
		this.publishYear = publishYear;
		this.type = type;
		this.author = author;
		this.publisher = publisher;
		this.chapterNo = ChapterNo;
	}
	
	

	public Story(int storyId, String storyName, String pageNo, String language, int price, int amount,
			String publishYear,int ChapterNo) {
		super();
		this.storyId = storyId;
		this.storyName = storyName;
		this.pageNo = pageNo;
		this.language = language;
		this.price = price;
		this.amount = amount;
		this.publishYear = publishYear;
		this.chapterNo = ChapterNo;
	}

	public int getStoryId() 
	{
		return storyId;
	}

	public void setStoryId(int storyId)
	{
		this.storyId = storyId;
	}

	public String getStoryName()
	{
		return storyName;
	}

	public void setStoryName(String storyName) 
	{
		this.storyName = storyName;
	}

	public String getPageNo() 
	{
		return pageNo;
	}

	public void setPageNo(String pageNo) 
	{
		this.pageNo = pageNo;
	}

	public String getLanguage() 
	{
		return language;
	}

	public void setLanguage(String language) 
	{
		this.language = language;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price) 
	{
		this.price = price;
	}

	public int getAmount() 
	{
		return amount;
	}

	public void setAmount(int amount) 
	{
		this.amount = amount;
	}

	public String getPublishYear() 
	{
		return publishYear;
	}

	public void setPublishYear(String publishYear) 
	{
		this.publishYear = publishYear;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author) 
	{
		this.author = author;
	}

	public String getPublisher() 
	{
		return publisher;
	}

	public void setPublisher(String publisher) 
	{
		this.publisher = publisher;
	}

	public int getChapterNo() {
		return chapterNo;
	}

	public void setChapterNo(int chapterNo) {
		this.chapterNo = chapterNo;
	}

	
	
	
	
}
