package com.paypalpaymentassignment.paypalpayment.services;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.paypalpaymentassignment.paypalpayment.domain.Client;
import com.paypalpaymentassignment.paypalpayment.dto.ClientDTO;
import com.paypalpaymentassignment.paypalpayment.repositories.ClientRepository;
import com.paypalpaymentassignment.paypalpayment.services.exceptions.DataIntegrityException;
import com.paypalpaymentassignment.paypalpayment.services.exceptions.ObjectNotFoundException;


@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client find(int id){
        Optional<Client> obj = clientRepository.findById(id);
        return obj.orElseThrow(() ->  new ObjectNotFoundException("Client not found, try again"));
    }

    public Client insert(Client obj){
        obj.setId(null);
        Client Client = clientRepository.save(obj);
        return Client;
    }

    public Client update(Client obj){
        Client newObj = find(obj.getId());
        updateData(newObj,obj);
        return clientRepository.save(newObj);
    }

    public void updateData(Client newObj, Client obj){
        newObj.setFirstName(obj.getFirstName());
        newObj.setLastName(obj.getLastName());
        newObj.setEmail(obj.getEmail());
        newObj.setPhoneNumber(obj.getPhoneNumber());
    }
  public Client fromDTO(ClientDTO objDto){
        return new Client(objDto.getId(),objDto.getFirstName(),objDto.getLastName(),objDto.getEmail(),
        objDto.getPhoneNumber());
    }

    public void delete(Integer id){
        find(id);
        try{
            clientRepository.deleteById(id);
        } catch (DataIntegrityException  e){
            throw new ObjectNotFoundException("Não foi possível excluir o Client");
        }
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return clientRepository.findAll(pageRequest);
    }

}
