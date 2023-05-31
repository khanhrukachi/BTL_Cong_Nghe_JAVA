package library.management;

import java.sql.*;
import java.util.Vector;

public class StatisticModify {
	Connection conn = null;
	public StatisticModify()
	{

	}
	
	public int getStatisticTotalStory()
	{
		int statisticTotalStory = 0;
		conn = ConnectDB.getConnection();
		String sql = "select fc_getStatisticTotalStory()";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				statisticTotalStory = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try
			{
				stmt.close();
				conn.close();
			} catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return statisticTotalStory; 
	}
	
	public int getStatisticTotalLoan()
	{
		int statisticTotalLoan = 0;;
		conn = ConnectDB.getConnection();
		String sql = "select fc_getStatisticTotalLoan()";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				statisticTotalLoan = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try
			{
				stmt.close();
				conn.close();
			} catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return statisticTotalLoan; 
	}
	
	public int getStatisticLoan()
	{
		int statisticLoan = 0;;
		conn = ConnectDB.getConnection();
		String sql = "select fc_getStatisticLoan()";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				statisticLoan = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try
			{
				stmt.close();
				conn.close();
			} catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return statisticLoan; 
	}
	
	public int getStatisticPunish()
	{
		int statisticPunish = 0;;
		conn = ConnectDB.getConnection();
		String sql = "select fc_getStatisticPunish()";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				statisticPunish = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try
			{
				stmt.close();
				conn.close();
			} catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return statisticPunish; 
	}
}
