package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import entities.Carts;
import entities.Customers;
import entities.OrderDetails;
import entities.Orders;
import models.Mailer;
import models.Serialization;

@Controller
@Transactional
@RequestMapping(value = "payment")
public class PaymentController {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer2;
	@RequestMapping(value = "")
	public String show() {
		return "payment";
	}

	@RequestMapping(params = "payment_method", method = RequestMethod.GET)
	public String payment_method(ModelMap model, @RequestParam(value = "payment_method") String payment_method) {
		model.addAttribute("payment_method", payment_method);
		return "payment2";
	}

	@RequestMapping(params = "accept", method = RequestMethod.POST)
	public String payment(@CookieValue(value = "carts", defaultValue = "") String cookievalue,ModelMap model, HttpServletRequest request) {
		String address = request.getParameter("address");
		String payment_method = request.getParameter("payment_method");
		HttpSession httpSession = request.getSession();
		Customers customer = new Customers();
		List<Carts> carts = new ArrayList();
		Orders order = new Orders();
		String note=null;
		if (httpSession.getAttribute("user") != null) {
			customer = (Customers) httpSession.getAttribute("user");
			carts = (List<Carts>) httpSession.getAttribute("cartlist");
		} else {
			customer.setCustomerID("notuser");
			String cusname=request.getParameter("cusname");
			String phone=request.getParameter("phone");
			note="KH:"+cusname+" SDT:"+phone;
			Serialization s = new Serialization();
			try {
				carts = (List<Carts>) s.fromString(cookievalue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		order.setCustomer(customer);
		order.setShipAddress(address);
		Date date=new Date();
		Date date2=new Date();
		date2.setTime(date.getTime()+1000*60*60*24*3);
		order.setOrderDate(date);
		order.setRequiredDate(date2);
		if (payment_method.equals("1"))
			order.setOrderStatus(false);
		else
			order.setOrderStatus(true);
		Collection<OrderDetails> details = new ArrayList<OrderDetails>();
		for (Carts c : carts) {
			OrderDetails orderDetail = new OrderDetails();
			orderDetail.setOrder(order);
			orderDetail.setProduct(c.getProduct());
			orderDetail.setUnitprice(c.getProduct().getUnitprice());
			orderDetail.setQuantity(c.getQuantity());
			details.add(orderDetail);
		}
		order.setOrderdetails(details);
		order.setNote(note);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(order);
			t.commit();
			model.addAttribute("message", "Đặt hàng thành công");

		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message2", "Lỗi thanh toán");
			e.printStackTrace();
		} finally {
			session.close();
		}
		if(!order.getCustomer().getCustomerID().endsWith("notuser")&&order.getCustomer().getEmail()!=null)
			mailer2.send(order.getCustomer().getEmail());
		return "payment3";
	}
}
