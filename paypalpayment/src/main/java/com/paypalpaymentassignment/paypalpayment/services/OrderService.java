package com.paypalpaymentassignment.paypalpayment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.paypalpaymentassignment.paypalpayment.domain.Orders;
import com.paypalpaymentassignment.paypalpayment.domain.ShoppingCart;
import com.paypalpaymentassignment.paypalpayment.repositories.OrderRepository;
import com.paypalpaymentassignment.paypalpayment.repositories.ShoppingCartRepository;
import com.paypalpaymentassignment.paypalpayment.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

    @Autowired
	private OrderRepository orderRepository;
	
    @Autowired
	private ShoppingCartRepository shoppingCartRepository;
    
    @Autowired
    private ProductService productService;
    @Autowired
    private ClientService clientService;
    
    public Orders find(Integer id) {
		Optional<Orders> obj = orderRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Order not found! Id: " ));
	}
	
	public Orders insert(Orders obj) {
		obj.setId(null);
		obj.setClient(clientService.find(obj.getClient().getId()));
		obj = orderRepository.save(obj);

		for (ShoppingCart ip : obj.getShoppingCarts()) {
			ip.setProduct(productService.find(ip.getProduct().getId()));
			ip.setPrice(ip.getProduct().getPrice());
			ip.setOrder(obj);
		}
		shoppingCartRepository.saveAll(obj.getShoppingCarts());
		return obj;
	}


    
    public List<Orders> findAll(){
        return orderRepository.findAll();
    }

    public Page<Orders> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return orderRepository.findAll(pageRequest);
    }
}
