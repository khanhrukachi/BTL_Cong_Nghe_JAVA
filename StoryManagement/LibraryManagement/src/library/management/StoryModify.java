package library.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class StoryModify
{
	Connection conn = null;
	public StoryModify()
	{
		
	}
	
	public Vector<Story> getStoryList()
	{
		Vector<Story> storyList = new Vector();
		conn = ConnectDB.getConnection();
		String sql = "call sp_findAllStory";
		Statement stmt = null;
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Story story = new Story(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
									rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getInt(11));
				storyList.add(story);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally 
		{
			try
			{
				stmt.close();
				conn.close();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return storyList;
	}
	
	public void addStory(Story story)
	{
		conn = ConnectDB.getConnection();
		String sql = "call sp_insertStory(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, story.getStoryName());
			stmt.setString(2, story.getPageNo());
			stmt.setString(3, story.getLanguage());
			stmt.setInt(4, story.getPrice());
			stmt.setInt(5, story.getAmount());
			stmt.setString(6, story.getPublishYear());
			stmt.setString(7, story.getType());
			stmt.setString(8, story.getAuthor());
			stmt.setString(9, story.getPublisher());
			stmt.setInt(10,story.getChapterNo());
			stmt.execute();
		} catch (Exception e) {

			e.printStackTrace();
		} finally
		{
			try
			{
				stmt.close();
				conn.close();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void deleteStory(int storyId)
	{
		conn = ConnectDB.getConnection();
		String sql = "call sp_deleteStory(?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, storyId);
			stmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			try
			{
				stmt.close();
				conn.close();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void updateStory(Story story)
	{
		conn = ConnectDB.getConnection();
		String sql = "call sp_updateStory(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, story.getStoryName());
			stmt.setString(2, story.getPageNo());
			stmt.setString(3, story.getLanguage());
			stmt.setInt(4, story.getPrice());
			stmt.setInt(5, story.getAmount());
			stmt.setString(6, story.getPublishYear());
			stmt.setString(7, story.getType());
			stmt.setString(8, story.getAuthor());
			stmt.setString(9, story.getPublisher());
			stmt.setInt(10, story.getChapterNo());
			stmt.setInt(11, story.getStoryId());
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			try
			{
				stmt.close();
				conn.close();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	

//	
	public Vector<Story> findStoryBy(String sql, String parameter)
	{
		Vector<Story> storyList = new Vector();
		conn = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+parameter+"%");
			stmt.execute();
			rs = stmt.executeQuery();
			while(rs.next())
			{
				Story story = new Story(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getInt(11));
				storyList.add(story);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally 
		{
			try
			{
				stmt.close();
				conn.close();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return storyList;
	}
	
	public Vector<Story> sort(String sql)
	{
		Vector<Story> storyList = new Vector();
		String sql2 = sql;
		conn = ConnectDB.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);
			while(rs.next())
			{
				Story story = new Story(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getInt(11));
				storyList.add(story);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				stmt.close();
				conn.close();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return storyList;
		
	}
	
}

