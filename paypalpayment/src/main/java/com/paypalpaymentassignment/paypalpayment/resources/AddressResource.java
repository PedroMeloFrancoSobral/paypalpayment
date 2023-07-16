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

import com.paypalpaymentassignment.paypalpayment.domain.Address;
import com.paypalpaymentassignment.paypalpayment.dto.AddressDTO;
import com.paypalpaymentassignment.paypalpayment.services.AddressService;


@RestController
@RequestMapping(value = "/addresses")
public class AddressResource {
    @Autowired
    AddressService addressService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Address> find(@PathVariable int id){
        Address obj = addressService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert (@Valid @RequestBody AddressDTO objDto){
        Address obj = addressService.fromDTO(objDto);
        obj = addressService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id)").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody AddressDTO objDto,
    @PathVariable Integer id){
        Address obj = addressService.fromDTO(objDto);
        obj.setId(id);
        obj = addressService.update(obj);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Address> delete(@PathVariable int id){
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Address>> findAll(){
        List<Address> obj = addressService.findAll();
        return ResponseEntity.ok().body(obj);
    }


    @RequestMapping(value="/page",method = RequestMethod.GET)
        public ResponseEntity<Page<Address>> findPage(
            @RequestParam(value="page", defaultValue = "0") Integer page, 
            @RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
            @RequestParam(value="direction", defaultValue = "ASC") String direction){
            Page<Address> obj = addressService.findPage(page,linesPerPage,orderBy,direction);
            return ResponseEntity.ok().body(obj);
    }
}
