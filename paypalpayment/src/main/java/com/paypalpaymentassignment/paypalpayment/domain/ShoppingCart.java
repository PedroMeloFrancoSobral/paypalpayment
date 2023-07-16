package com.paypalpaymentassignment.paypalpayment.domain;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
@Entity
public class ShoppingCart implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ShoppingCartPK id = new ShoppingCartPK();

	private Integer quantity;
	private Double price;

    public ShoppingCart(){

    }

    public ShoppingCart(Orders order,Product product,
    Integer quantity, Double price) {
        id.setOrder(order);
		id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    public ShoppingCartPK getId() {
        return id;
    }

    public void setId(ShoppingCartPK id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
	public int hashCode() {
		return Objects.hash(id);
	}

    @JsonIgnore
	public Orders getOrder() {
		return id.getOrder();
	}


	public Product getProduct() {
		return id.getProduct();
	}

    public void setOrder(Orders order) {
		id.setOrder(order);
	}

    public void setProduct(Product product) {
		id.setProduct(product);
	}

    public double getSubTotal() {
		return price * quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		return Objects.equals(id, other.id);
	}
}
