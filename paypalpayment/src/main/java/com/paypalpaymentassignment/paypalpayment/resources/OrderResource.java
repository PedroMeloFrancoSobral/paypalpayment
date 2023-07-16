package com.paypalpaymentassignment.paypalpayment.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paypalpaymentassignment.paypalpayment.domain.Orders;
import com.paypalpaymentassignment.paypalpayment.services.OrderService;


@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Orders> find(@PathVariable int id){
        Orders obj = orderService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert (@Valid @RequestBody Orders obj){
        obj = orderService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> findAll(){
        List<Orders> obj = orderService.findAll();
        return ResponseEntity.ok().body(obj);
    }


    @RequestMapping(value="/page",method = RequestMethod.GET)
        public ResponseEntity<Page<Orders>> findPage(
            @RequestParam(value="page", defaultValue = "0") Integer page, 
            @RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
            @RequestParam(value="direction", defaultValue = "ASC") String direction){
            Page<Orders> obj = orderService.findPage(page,linesPerPage,orderBy,direction);
            return ResponseEntity.ok().body(obj);
    }
}
