package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import entities.Accounts;
import entities.Customers;
import models.Mailer;
import models.Serialization;

@Controller
@Transactional
public class RegisterController {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer2;
	@RequestMapping(value="register")
	public String register(@ModelAttribute("customers") Customers cus) {
		return "register";
	}
	
	@RequestMapping("register/save")
	public String register(HttpServletRequest request, ModelMap model,
			Accounts account,
			@Validated @ModelAttribute("customers") Customers cus,
			BindingResult errors) {
		if(request.getParameter("username")==null||request.getParameter("password")==null){
			model.addAttribute("message","Không được để trống");
			return "register";
		}
		String user = (String) request.getParameter("username");
		String pass = (String) request.getParameter("password");
		String password=null;
		try {
			 password=new Serialization().toString(pass);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// check trung user
		Session ss = factory.getCurrentSession();
		String hql = "FROM Accounts where UserName=:username";
		Query query = ss.createQuery(hql);
		query.setParameter("username", user);
		
		List<Accounts> listacc = query.list();
		if (listacc.size() > 0) {
			model.addAttribute("message", "Tài khoản đã tồn tại!");
			return "register";
		}
	
		cus.setCustomerID(user);
		account.setCustomerID(user);
		account.setPassWord(password);
		account.setUserName(user);
		cus.setAccount(account);
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.save(cus);			
			t.commit();
			mailer2.send2(cus.getEmail());
			model.addAttribute("message", "Bạn đã đăng ký tài khoản mới thành công");
			return "login";
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return "register";
	}

}
