package com.explore.canada.database;

//import org.apache.log4j.Logger;

import java.sql.*;

public class CallStoredProcedure implements ICallStoredProcedure
{
	private String storedProcedureName;
	private Connection connection;
	private CallableStatement statement;
	//private Logger logger = Logger.getLogger(this.getClass());
	
	public CallStoredProcedure(String storedProcedureName) throws SQLException
	{
		this.storedProcedureName = storedProcedureName;
		connection = null;
		statement = null;
		openConnection();
		createStatement();
	}
	
	private void createStatement() throws SQLException
	{
		statement = connection.prepareCall("{call " + storedProcedureName + "}");
	}
	
	private void openConnection()throws SQLException
	{
		connection = ConnectionManager.instance().getDBConnection();
	}
	
	public void cleanup()
	{
		try
		{
			if (null != statement)
			{
				statement.close();
			}
			if (null != connection)
			{
				if (!connection.isClosed())
				{
					connection.close();
				}
			}
		}
		catch (SQLException ex){
			//logger.error(ex);
		}
		catch (Exception e)
		{
			//logger.error(e);
		}
	}
	
	public void setParameter(int paramIndex, String value) throws SQLException
	{
		statement.setString(paramIndex, value);
	}
	
	public void registerOutputParameterString(int paramIndex) throws SQLException
	{
		statement.registerOutParameter(paramIndex, Types.VARCHAR);
	}

	public void setParameter(int paramIndex, long value) throws SQLException
	{
		statement.setLong(paramIndex, value);
	}

	public void registerOutputParameterLong(int paramIndex) throws SQLException
	{
		statement.registerOutParameter(paramIndex, Types.BIGINT);
	}
	
	public ResultSet executeWithResults() throws SQLException
	{
		if (statement.execute())
		{
			return statement.getResultSet();
		}
		return null;
	}
	
	public void execute() throws SQLException
	{
		statement.execute();
	}
}
