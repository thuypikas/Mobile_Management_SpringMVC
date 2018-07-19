package admin_controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import entities.Categories;
import entities.Products;

@Controller
@Transactional
@RequestMapping("admin/productmgt")
public class ProductsMgmt {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	
	@RequestMapping("")
	public String showcategories(ModelMap model, HttpServletRequest request) {
		int page = 1;int supid=0;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (request.getParameter("supid") != null) {
			supid = Integer.parseInt(request.getParameter("supid"));
		}
		Session session = factory.getCurrentSession();
		String hql = "FROM Products where supplier.supplierid=:supid";
		Query query = session.createQuery(hql);
		if (supid != 0)
			query.setParameter("supid", supid);
		else {
			hql = "FROM Products";
			query = session.createQuery(hql);
		}
		List<Products> list1 = query.list();
		int soluong = list1.size();
		query.setFirstResult((page - 1) * 9 );
		query.setMaxResults(9);
		List<Products> list2 = query.list();
		model.addAttribute("products", list2);
		model.addAttribute("soluong", soluong);
		model.addAttribute("paging", page);
		model.addAttribute("supid", supid);
		return "admin/productmgt";
	}

	// show edit
	@RequestMapping(params = "edit")
	public String showedit(ModelMap model, @ModelAttribute("product") Products product) {
		return "admin/editproduct";
	}

	// update product
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(ModelMap model, @RequestParam("upfile") MultipartFile upfile,
			@Validated @ModelAttribute("product") Products product, BindingResult errors) {
		String filename = upfile.getOriginalFilename();
		if (!filename.equals(""))
			try {
				product.setPicture(filename);
				String photoPath = context.getRealPath("/WEB-INF/images/" + upfile.getOriginalFilename());
				System.out.println(photoPath);
				upfile.transferTo(new File(photoPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(product);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công");
			System.out.println("thanh cong");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập nhật thất bại");
			System.out.println("that bai");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return "admin/editproduct";
	}

	// show new
	@RequestMapping(params = "new")
	public String shownew(ModelMap model) {
		model.addAttribute("product", new Products());
		return "admin/newproduct";
	}

	// add product
	@RequestMapping(params = "new", method = RequestMethod.POST)
	public String addproduct(ModelMap model, @RequestParam("upfile") MultipartFile upfile,
			@Validated @ModelAttribute("product") Products product, BindingResult errors) {
		String filename = upfile.getOriginalFilename();
		if (!filename.equals(""))
			try {
				product.setPicture(filename);
				String photoPath = context.getRealPath("/WEB-INF/images/" + upfile.getOriginalFilename());
				System.out.println(photoPath);
				upfile.transferTo(new File(photoPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(product);
			t.commit();
			model.addAttribute("message", "Thêm sản phẩm thành công");
			System.out.println("thanh cong");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm sản phẩm thất bại");
			System.out.println("that bai");
		} finally {
			session.close();
		}
		return "admin/newproduct";
	}

	@RequestMapping(params = "remove3")
	public void remove(Products products, HttpServletRequest request, HttpServletResponse response) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(products);
			t.commit();
			System.out.println("thanh cong");
		} catch (Exception e) {
			System.out.println("that bai");
			t.rollback();
		} finally {
			session.close();
		}
		try {
			response.sendRedirect(request.getContextPath() + "/admin/productmgt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@ModelAttribute("cats")
	public List<Categories> getlist() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Categories";
		Query query = session.createQuery(hql);
		List<Categories> list = query.list();
		return list;
	}
}
