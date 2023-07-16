package com.paypalpaymentassignment.paypalpayment.resources;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.paypalpaymentassignment.paypalpayment.domain.Product;
import com.paypalpaymentassignment.paypalpayment.dto.Order;
import com.paypalpaymentassignment.paypalpayment.repositories.ProductRepository;
import com.paypalpaymentassignment.paypalpayment.services.PaypalService;

@Controller
public class PaypalController {
    
    @Autowired
    PaypalService paypalService;

    @Autowired
    ProductRepository productRepository;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @GetMapping("/")
    public String home(Model model) {
        ArrayList <Product> products = (ArrayList <Product>) productRepository.findAll();
        model.addAttribute("products", products);
		return "home";
	}

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order")Order order){
        try{
        Payment payment = paypalService.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
         order.getIntent(), order.getDescription(), "http://localhost:9090/"+CANCEL_URL,
           "http://localhost:9090/"+SUCCESS_URL);
        
        for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return "redirect:"+link.getHref();
				}
            }
        }catch(PayPalRESTException e){
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay(){
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
	    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
	        try {
	            Payment payment = paypalService.executePayment(paymentId, payerId);
                System.out.println(payment.getTransactions().get(0).getPurchaseUnitReferenceId());
                System.out.println(payment.toJSON());
	            if (payment.getState().equals("approved")) {
	                return "success";
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return "redirect:/";
	    }

    
}
