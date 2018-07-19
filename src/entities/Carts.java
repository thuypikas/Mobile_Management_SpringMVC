package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Carts implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name = "CustomerID")
	private Customers customer;
	@Id
	@ManyToOne
	@JoinColumn(name = "productid")
	private Products product;
	private Integer Quantity;
	public Customers getCustomer() {
		return customer;
	}
	public void setCustomer(Customers customer) {
		this.customer = customer;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return Quantity;
	}
	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	
}
