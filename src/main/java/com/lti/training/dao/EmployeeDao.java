package com.lti.training.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.lti.training.dto.Employee;

//Data Access Object
public class EmployeeDao{
	//public void insert(int empno, String name, double salary)		// it is a bad design as if we had 10 columns in a table we shouldn't create a method accepting 10 parameters better accept data in the form of object
	public void store(Employee emp){
		Connection conn=null;		// will start connection
		PreparedStatement pstmt= null; 		//precompiled sql statements (help us fire sql statements)
		try {
			//why not FileInputStream is = new FileInputStream("dev-db.properties")
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("dev-db.properties");
			Properties dbProps = new Properties() ;
			dbProps.load(is);
			
			String driverName=dbProps.getProperty("driverClassName");
			String url=dbProps.getProperty("url");
			String username=dbProps.getProperty("username");
			String password=dbProps.getProperty("password");
			
			Class.forName(driverName);
			conn = DriverManager.getConnection(url,username,password);
			pstmt= conn.prepareStatement("insert into emp_data values(?,?,?)");
			pstmt.setString(1, emp.getEmpno());
			pstmt.setString(2, emp.getName());
			pstmt.setString(3, emp.getSalary());
			pstmt.executeUpdate();		//any DML statement
		}
		catch(ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {pstmt.close();} catch(Exception e) {}
			try {conn.close();} catch(Exception e) {}
		}
	}
	/*
	public void display() throws DataAccessException{
		Connection conn=null;		// will start connection
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			/*pstmt= conn.prepareStatement("insert into TBL_EMP values(?,?,?)");
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getName());
			pstmt.setDouble(3, emp.getSalary());
			pstmt.executeUpdate();		//any DML statement
			Statement stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from TBL_EMP");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3));
			}
		}
		catch(Exception msg) {
			throw new DataAccessException("Problem while inserting employee data",msg);
		}
		finally {
			try {rs.close();} catch(Exception msg) {}
			try {conn.close();} catch(Exception msg) {}
		}
	}
	public List<Employee> fetchAll() throws DataAccessException{
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			pstmt=conn.prepareStatement("select * from TBL_EMP");
			rs=pstmt.executeQuery();
			
			List<Employee> list = new ArrayList<>();
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setEmpno(rs.getInt("empno"));
				emp.setName(rs.getString("name"));
				emp.setSalary(rs.getDouble("salary"));
				list.add(emp);
			}
			return list;
			}
		catch(ClassNotFoundException | SQLException e) {
			throw new DataAccessException("Problem while inserting employee data",e);
		}
		finally{
			try {pstmt.close();} catch(Exception e) {}
			try {rs.close();} catch(Exception e) {}
			try {conn.close();} catch(Exception e) {}
		}
		}*/
	}

