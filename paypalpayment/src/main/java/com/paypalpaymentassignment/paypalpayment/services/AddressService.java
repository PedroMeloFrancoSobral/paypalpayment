package com.paypalpaymentassignment.paypalpayment.services;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.paypalpaymentassignment.paypalpayment.domain.Address;
import com.paypalpaymentassignment.paypalpayment.dto.AddressDTO;
import com.paypalpaymentassignment.paypalpayment.repositories.AddressRepository;
import com.paypalpaymentassignment.paypalpayment.services.exceptions.DataIntegrityException;
import com.paypalpaymentassignment.paypalpayment.services.exceptions.ObjectNotFoundException;


@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address find(int id){
        Optional<Address> obj = addressRepository.findById(id);
        return obj.orElseThrow(() ->  new ObjectNotFoundException("Address not found,try again"));
    }

    public Address insert(Address obj){
        obj.setId(null);
        Address Address = addressRepository.save(obj);
        return Address;
    }

    public Address update(Address obj){
        Address newObj = find(obj.getId());
        updateData(newObj,obj);
        return addressRepository.save(newObj);
    }

    public void updateData(Address newObj, Address obj){
        newObj.setAddressLine1(obj.getAddressLine1());
        newObj.setAddressLine2(obj.getAddressLine2());
        newObj.setState(obj.getState());
        newObj.setPostalCode(obj.getPostalCode());
        newObj.setCountryCode(obj.getCountryCode());
        newObj.setClient(obj.getClient());
    }
  public Address fromDTO(AddressDTO objDto){
        return new Address(objDto.getId(),objDto.getAddressLine1(),objDto.getAddressLine2(),objDto.getState(),
        objDto.getPostalCode(),objDto.getCountryCode(),objDto.getClient());
    }


    public void delete(Integer id){
        find(id);
        try{
            addressRepository.deleteById(id);
        } catch (DataIntegrityException  e){
            throw new ObjectNotFoundException("Não foi possível excluir o Address");
        }
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public Page<Address> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return addressRepository.findAll(pageRequest);
    }

}
