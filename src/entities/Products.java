package entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
@Entity
@Table(name="Products")
public class Products implements Serializable {
	@Id @GeneratedValue
	private Integer productid;
	@NotBlank(message="Nhập tên sản phẩm")
	@Length(min=5,max=50,message="Tên sản phẩm từ 5 đến 50 kí tự")
	private String productname;
	@NotNull(message="Nhập giá sản phẩm")
	@DecimalMin(value="100",message="Giá không hợp lệ")
	@DecimalMax(value="100000000", message="Giá không hợp lệ")
	private Double unitprice;
	private String operatingsystem;
	private String screensize;
	private String description;
	private String picture;
	private transient Integer soluong;
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Categories category;
	@ManyToOne
	@JoinColumn(name="supplierid")
	private Suppliers supplier;
	@OneToMany(mappedBy="product",fetch=FetchType.LAZY)
	private Collection<Carts> cart;
	@OneToMany(mappedBy="product",fetch=FetchType.LAZY)
	private Collection<OrderDetails> orderdetails;
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}
	public String getOperatingsystem() {
		return operatingsystem;
	}
	public void setOperatingsystem(String operatingsystem) {
		this.operatingsystem = operatingsystem;
	}
	public String getScreensize() {
		return screensize;
	}
	public void setScreensize(String screensize) {
		this.screensize = screensize;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getSoluong() {
		return soluong;
	}
	public void setSoluong(Integer soluong) {
		this.soluong = soluong;
	}
	public Categories getCategory() {
		return category;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
	public Suppliers getSupplier() {
		return supplier;
	}
	public void setSupplier(Suppliers supplier) {
		this.supplier = supplier;
	}
	public Collection<Carts> getCart() {
		return cart;
	}
	public void setCart(Collection<Carts> cart) {
		this.cart = cart;
	}
	public Collection<OrderDetails> getOrderdetails() {
		return orderdetails;
	}
	public void setOrderdetails(Collection<OrderDetails> orderdetails) {
		this.orderdetails = orderdetails;
	}
	
}
