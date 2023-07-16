package com.paypalpaymentassignment.paypalpayment.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.paypalpaymentassignment.paypalpayment.domain.Client;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class AddressDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty (message = "The addressLine1 field is obligatory, try again")
    private String addressLine1;
    @NotEmpty (message = "The addressLine2 field is obligatory, try again")
    private String addressLine2;
    @NotEmpty (message = "The state field is obligatory, try again")
    private String state;
    @NotEmpty (message = "The postalCode field is obligatory, try again")
    private String postalCode;
    @NotEmpty (message = "The countryCode field is obligatory, try again")
    private String countryCode;

    private Client client;

    public AddressDTO(Integer id, String addressLine1, String addressLine2, String state, String postalCode,
            String countryCode,Client client) {
        this.id = id;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.state = state;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
