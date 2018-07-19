package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entities.Products;
import entities.Suppliers;

@Controller
@Transactional
public class SearchController {
	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "search")
	public String searchprod(ModelMap model, @RequestParam("pname") String pname, @RequestParam("catid") int catid,HttpServletRequest request) {
		String sortby = "";
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("sortby") != null) {
			sortby = (String) httpSession.getAttribute("sortby");
		}
		if (request.getParameter("sortby") != null) {
			sortby = request.getParameter("sortby");
			httpSession.setAttribute("sortby", sortby);
		}
		String sql = "select * from products where CategoryID=" + catid + "and ProductName like '%" + pname + "%'";
		switch(sortby){
		case "name-asc":sql = "select * from products where CategoryID=" + catid + "and ProductName like '%" + pname + "%' order by productname";break;
		case "name-desc":sql = "select * from products where CategoryID=" + catid + "and ProductName like '%" + pname + "%' order by productname desc";break;
		case "price-asc":sql = "select * from products where CategoryID=" + catid + "and ProductName like '%" + pname + "%' order by unitprice";break;
		case "price-desc":sql = "select * from products where CategoryID=" + catid + "and ProductName like '%" + pname + "%' order by unitprice desc";break;
		}
		Session session = factory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(Products.class);
		List<Products> list2 = sqlQuery.list();
		model.addAttribute("products", list2);
		return "products";
	}
	@RequestMapping(value = "filter")
	public String filter(ModelMap model, HttpServletRequest request) {
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		String sortby = "";
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("sortby") != null) {
			sortby = (String) httpSession.getAttribute("sortby");
		}
		if (request.getParameter("sortby") != null) {
			sortby = request.getParameter("sortby");
			httpSession.setAttribute("sortby", sortby);
		}
		String scsize="";
		String os="";
		String pricelv="";
		if(request.getParameter("scsize")!=null)scsize=request.getParameter("scsize");
		if(request.getParameter("os")!=null)os=request.getParameter("os");
		if(request.getParameter("pricelv")!=null)pricelv=request.getParameter("pricelv");
		System.out.println(scsize+"..."+os+"..."+pricelv);
		String sql="select * from Products where OperatingSystem like '%"+os+"%' and ScreenSize like '%"+scsize+"%'";
		switch(pricelv){
		case "1": sql=sql+"and UnitPrice<3000";break;
		case "2":sql=sql+"and UnitPrice between 3000 and 7000";break;
		case "3":sql=sql+"and UnitPrice between 7000 and 12000";break;
		case "4":sql=sql+"and UnitPrice>12000";break;
		}
		switch(sortby){
		case "name-asc":sql = sql+"order by productname";break;
		case "name-desc":sql = sql+"order by productname desc";break;
		case "price-asc":sql = sql+"order by unitprice";break;
		case "price-desc":sql = sql+"order by unitprice desc";break;
		}
		Session session = factory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(Products.class);
		List<Products> list1 = sqlQuery.list();
		int soluong = list1.size();
		sqlQuery.setFirstResult((page - 1) * 9);
		sqlQuery.setMaxResults(9);
		List<Products> list2 = sqlQuery.list();
		model.addAttribute("products", list2);
		model.addAttribute("scsize", scsize);
		model.addAttribute("os", os);
		model.addAttribute("pricelv", pricelv);
		model.addAttribute("sortby", sortby);
		model.addAttribute("soluong", soluong);
		model.addAttribute("paging", page);
		return "products2";
	}
}
