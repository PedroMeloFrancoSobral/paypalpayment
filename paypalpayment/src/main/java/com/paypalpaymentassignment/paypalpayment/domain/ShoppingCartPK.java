package com.paypalpaymentassignment.paypalpayment.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ShoppingCartPK implements Serializable{

	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="order_id")
	private Orders order;
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
    public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCartPK other = (ShoppingCartPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
}
