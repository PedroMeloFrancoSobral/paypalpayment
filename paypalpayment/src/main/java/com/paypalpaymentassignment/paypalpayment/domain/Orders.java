package com.paypalpaymentassignment.paypalpayment.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Orders implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

    @ManyToOne
	@JoinColumn(name = "address_id")
	private Address enderecoDeEntrega;

    @OneToMany(mappedBy="id.order")
	private Set<ShoppingCart> shoppingCarts = new HashSet<>();

    public Orders(){
    }

    public Orders(Integer id, Client client, Address enderecoDeEntrega) {
        this.id = id;
        this.client = client;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Address enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Set<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return Objects.equals(id, other.id);
	}

}
