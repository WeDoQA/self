package test.java.com.ch.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.ds.PGConnectionPoolDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.java.com.ch.beans.CustomerListPageValues;



public class DBUtil {
	final static Logger logger = LoggerFactory.getLogger(DBUtil.class);

	static String url = "jdbc:postgresql://localhost:5432/mydb";
	static String user = "user";
	static String password = "password";
	PGConnectionPoolDataSource source;

	/**
	 * Finds all the values for the given query
	 * 
	 * @param query
	 *            - The SQL query that will be used to get data from the DB
	 * @return Returns a list of strings that represent the data in the DB
	 */
	public  List<String> getAllValues(String query) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			// use connection

			PreparedStatement pst = null;
			ResultSet rs = null;

			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			List<String> res = new ArrayList<String>();
			while (rs.next()) {
				res.add(rs.getString(1));
			}

			rs.close();
			pst.close();

			logger.debug("For Query: {}",query);
			logger.debug("From DB: {}",res);
			return res;
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (con != null) {
				try { con.close(); } catch (SQLException e) {}
			}
		}

		return null;



	}
	/**
	 * Query gets CustomerListPage values from database based on Page display. 
	 * @param dbValues
	 * @return
	 */
	
	public static List<CustomerListPageValues> getQueryValues(String dbValues){
		List<CustomerListPageValues> values = null;
		Connection con = null;
		     try {
		    	 con = DriverManager.getConnection(url, user, password);
		    	 PreparedStatement pst = null;
					ResultSet rs = null;
					pst = con.prepareStatement(dbValues);
					rs = pst.executeQuery();
					while (rs.next()) {
						if(values == null)
							values = new ArrayList<CustomerListPageValues>();
						CustomerListPageValues pageValues = new CustomerListPageValues();
						pageValues.setId(rs.getString(1));
						pageValues.setCustomerName(rs.getString(2));
						pageValues.setExpirationDate(rs.getString(3));
						pageValues.setUsers(rs.getString(4));
						pageValues.setMonitorLimit(rs.getString(5));
						pageValues.setBuildUserLimit(rs.getString(6));
						values.add(pageValues);	 
		     }
		      rs.close();
              pst.close();
              
              logger.debug("For Query: {}",dbValues);
  			logger.debug("From DB: {}",rs);
             return values;
		     } catch(Exception ex){
		    	 logger.error(ex.getMessage(), ex);
		     }
		     
		     finally {
					if (con != null) {
						try { con.close(); 
						} catch (SQLException e) {}
					}
				}

				return null;
	}

	/**
	 * Get a single value from the DB
	 * 
	 * @param query
	 *            - The SQL query that will be used to get data from the DB
	 * @return Returns the first hit from the DB otherwise returns an empty string.
	 */
	public String getFirstValue(String query) {
		String retVal = "";
		List<String> resultSet = getAllValues(query);
		if( resultSet != null ) {
			if( !resultSet.isEmpty() )
				retVal = resultSet.get(0);
		}

		if(null==retVal || retVal.equals("") ) {
			logger.debug("DBUtil.getAllValues() is returning empty result set.");
		}
		return retVal;
	}


	/**
	 * Updates the DB
	 * 
	 * @param query
	 *            - The SQL query that will update the DB
	 * @return true if update performed successfully
	 */
	public boolean update(String query) {
		return insert( query);
	}

	/**
	 * Insert method
	 * @param query
	 * 			- SQL query that will be passed in. It will be insert query
	 * @return
	 * 			- true if update has been performed successfully
	 */
	public  boolean insert(String query) {

		boolean success = false;

		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			pst = null;

			pst = con.prepareStatement(query);
		
			int rowsAffected = pst.executeUpdate();
			success = rowsAffected > 0;

		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);

		} finally {

			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		return success;
	}

	/**
	 * Deletes from DB
	 * 
	 * @param query
	 *            - The SQL query that will delete from DB
	 * @return true if update performed successfully
	 */
	public boolean delete(String query) {
		return update(query);
	}

}
