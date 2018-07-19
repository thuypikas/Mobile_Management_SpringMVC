package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import entities.Customers;

@Controller
@Transactional
public class UserController {	
	@Autowired
	SessionFactory factory; 
	
	@RequestMapping("usermodify")
	public String usermodify(HttpServletRequest request, ModelMap model, 
								@ModelAttribute("customers") Customers cus){
		//get Attribute "user"
		HttpSession httpSession = request.getSession();
		cus = (Customers) httpSession.getAttribute("user");		
		
		model.addAttribute("cus",cus);
		
		return "usermodify";
	}
	
	@RequestMapping("usermodify/save")
	public String save(HttpServletRequest request, ModelMap model,
							@Validated @ModelAttribute("customers") Customers cus){

		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();		
		
		
		try{
			session.update(cus);
			t.commit();
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("user", cus);
			model.addAttribute("message","Lưu thành công!");
		}catch(Exception e){
			t.rollback();			
		}finally{
			session.close();
		}
		return "usermodify";
	}
	
	
}
