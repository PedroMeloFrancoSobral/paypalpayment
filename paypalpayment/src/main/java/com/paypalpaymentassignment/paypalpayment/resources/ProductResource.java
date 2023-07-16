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

import com.paypalpaymentassignment.paypalpayment.domain.Product;
import com.paypalpaymentassignment.paypalpayment.dto.ProductDTO;
import com.paypalpaymentassignment.paypalpayment.services.ProductService;


@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    @Autowired
    ProductService ProductService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable int id){
        Product obj = ProductService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert (@Valid @RequestBody ProductDTO objDto){
        Product obj = ProductService.fromDTO(objDto);
        obj = ProductService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id)").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ProductDTO objDto,
    @PathVariable Integer id){
        Product obj = ProductService.fromDTO(objDto);
        obj.setId(id);
        obj = ProductService.update(obj);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> delete(@PathVariable int id){
        ProductService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll(){
        List<Product> obj = ProductService.findAll();
        return ResponseEntity.ok().body(obj);
    }


    @RequestMapping(value="/page",method = RequestMethod.GET)
        public ResponseEntity<Page<Product>> findPage(
            @RequestParam(value="page", defaultValue = "0") Integer page, 
            @RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
            @RequestParam(value="direction", defaultValue = "ASC") String direction){
            Page<Product> obj = ProductService.findPage(page,linesPerPage,orderBy,direction);
            return ResponseEntity.ok().body(obj);
    }
}
