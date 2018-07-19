package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderDetails")
public class OrderDetails implements Serializable{
	@ManyToOne
	@Id
	@JoinColumn(name = "productid")
	private Products product;
	@ManyToOne
	@Id
	@JoinColumn(name = "orderid")
	private Orders order;
	private Double unitprice;
	private Integer quantity;
	private Double discount=0.0;
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		discount = discount;
	}
}
