package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Orders implements Serializable {
	@Id
	@GeneratedValue
	private Integer OrderID;
	@ManyToOne
	@JoinColumn(name = "CustomerID")
	private Customers customer;
	private Date OrderDate;
	private Date RequiredDate;
	private Date ShippedDate;
	private String ShipAddress;
	private Boolean OrderStatus;
	private String note;
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@OneToMany(mappedBy = "order",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	Collection<OrderDetails> orderdetails;
	public Integer getOrderID() {
		return OrderID;
	}
	public void setOrderID(Integer orderID) {
		OrderID = orderID;
	}
	public Customers getCustomer() {
		return customer;
	}
	public void setCustomer(Customers customer) {
		this.customer = customer;
	}
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	public Date getRequiredDate() {
		return RequiredDate;
	}
	public void setRequiredDate(Date requiredDate) {
		RequiredDate = requiredDate;
	}
	public Date getShippedDate() {
		return ShippedDate;
	}
	public void setShippedDate(Date shippedDate) {
		ShippedDate = shippedDate;
	}
	public String getShipAddress() {
		return ShipAddress;
	}
	public void setShipAddress(String shipAddress) {
		ShipAddress = shipAddress;
	}

	public Boolean getOrderStatus() {
		return OrderStatus;
	}
	public void setOrderStatus(Boolean orderStatus) {
		OrderStatus = orderStatus;
	}
	public Collection<OrderDetails> getOrderdetails() {
		return orderdetails;
	}
	public void setOrderdetails(Collection<OrderDetails> orderdetails) {
		this.orderdetails = orderdetails;
	}
    
	
}
