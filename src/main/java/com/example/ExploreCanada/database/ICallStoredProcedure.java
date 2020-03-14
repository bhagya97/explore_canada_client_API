package com.example.ExploreCanada.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ICallStoredProcedure {
	
	public void cleanup();
	public void setParameter(int paramIndex, String value) throws SQLException;
	public void registerOutputParameterString(int paramIndex) throws SQLException;
	public void setParameter(int paramIndex, long value) throws SQLException;
	public void registerOutputParameterLong(int paramIndex) throws SQLException;
	public ResultSet executeWithResults() throws SQLException;
	public void execute() throws SQLException;
}
