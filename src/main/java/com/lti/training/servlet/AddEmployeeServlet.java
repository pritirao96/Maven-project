package com.lti.training.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lti.training.dto.Employee;
import com.lti.training.dao.EmployeeDao;

@WebServlet("/AddEmployee")
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empno=request.getParameter("empno");
		String name=request.getParameter("name");
		String salary=request.getParameter("salary");
		
		Employee emp=new Employee();
		emp.setEmpno(empno);
		emp.setName(name);
		emp.setSalary(salary);
		
		EmployeeDao dao=new EmployeeDao();
		dao.store(emp);
		
		HttpSession session = request.getSession();
		session.setAttribute("message", "Record added successfully");
		//alert("Data added successfully");
		response.sendRedirect("addEmployee.jsp");
	}

}
