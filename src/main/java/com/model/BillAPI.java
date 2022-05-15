package com.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class BillAPI
 */
@WebServlet("/BillAPI")
public class BillAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Bill billObj = new Bill();
		String output = billObj.insertBillDetails(request.getParameter("billCode"),
		request.getParameter("cusID"),
		request.getParameter("units"),
		request.getParameter("kwhCharge"),
		request.getParameter("fixCharge"),
		request.getParameter("total"));
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		Bill billObj = new Bill();
		String output = billObj.updateBill(paras.get("hidbillIDSave").toString(),
		paras.get("billCode").toString(),
		paras.get("cusID").toString(),
		paras.get("units").toString(),
		paras.get("kwhCharge").toString(),
		paras.get("fixCharge").toString(),
		paras.get("total").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		Bill billObj = new Bill();
		String output = billObj.deleteBill(paras.get("billID").toString());
		response.getWriter().write(output);
		}
	
		// Convert request parameters to a Map
	
		private static Map getParasMap(HttpServletRequest request)
		{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
		Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		String queryString = scanner.hasNext() ?
		scanner.useDelimiter("\\A").next() : "";
		scanner.close();
		String[] params = queryString.split("&");
		for (String param : params)
		{
		String[] p = param.split("=");
		map.put(p[0], p[1]);
		}
		}
		catch (Exception e)
		{
		}
		return map;
		}
	}


