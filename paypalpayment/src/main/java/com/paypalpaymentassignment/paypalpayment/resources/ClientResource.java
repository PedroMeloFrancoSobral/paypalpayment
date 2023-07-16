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

import com.paypalpaymentassignment.paypalpayment.domain.Client;
import com.paypalpaymentassignment.paypalpayment.dto.ClientDTO;
import com.paypalpaymentassignment.paypalpayment.services.ClientService;


@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
    @Autowired
    ClientService ClientService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> find(@PathVariable int id){
        Client obj = ClientService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert (@Valid @RequestBody ClientDTO objDto){
        Client obj = ClientService.fromDTO(objDto);
        obj = ClientService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id)").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDto,
    @PathVariable Integer id){
        Client obj = ClientService.fromDTO(objDto);
        obj.setId(id);
        obj = ClientService.update(obj);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Client> delete(@PathVariable int id){
        ClientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Client>> findAll(){
        List<Client> obj = ClientService.findAll();
        return ResponseEntity.ok().body(obj);
    }


    @RequestMapping(value="/page",method = RequestMethod.GET)
        public ResponseEntity<Page<Client>> findPage(
            @RequestParam(value="page", defaultValue = "0") Integer page, 
            @RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
            @RequestParam(value="direction", defaultValue = "ASC") String direction){
            Page<Client> obj = ClientService.findPage(page,linesPerPage,orderBy,direction);
            return ResponseEntity.ok().body(obj);
    }
}
