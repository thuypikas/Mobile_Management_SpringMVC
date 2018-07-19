package interceptor;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import entities.Accounts;
import entities.Carts;
import entities.Categories;
import entities.Customers;
import entities.Suppliers;
import models.Serialization;

@Transactional
public class Interceptor extends HandlerInterceptorAdapter {
	@Autowired
	SessionFactory factory;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Session session = factory.getCurrentSession();
		String hql = "FROM Categories";
		Query query = session.createQuery(hql);
		List<Categories> list = query.list();
		for (Categories c : list) {
			String sql = "select * from Suppliers where SupplierID in (select Products.SupplierID from Products where Products.CategoryID="
					+ c.getCategoryid() + ")";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(Suppliers.class);
			List<Suppliers> list2 = sqlQuery.list();
			c.setSupplier(list2);
		}
		request.setAttribute("categories", list);
		request.setAttribute("suppliers", list.get(0).getSupplier());
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("user") != null) {
			Customers customer = (Customers) httpSession.getAttribute("user");
			String hql2 = "FROM Carts where customer.CustomerID=:cusid ";
			Query query2 = session.createQuery(hql2);
			query2.setParameter("cusid", customer.getCustomerID());
			List<Carts> cartlist = query2.list();
			httpSession.setAttribute("cartlist", cartlist);
		} else {
			Cookie[]cookies=request.getCookies();
			String cartsvalue="";
			if(cookies!=null)
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().equals("carts")){
					cartsvalue=cookies[i].getValue();break;
				}
			}
			if(!cartsvalue.equals("")){
				Serialization s=new Serialization();
				List<Carts> cartlist=(List<Carts>)s.fromString(cartsvalue);
				request.setAttribute("cartlist", cartlist);
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return true;
	}
}
