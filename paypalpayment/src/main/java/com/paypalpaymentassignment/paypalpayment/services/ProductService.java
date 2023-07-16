package com.paypalpaymentassignment.paypalpayment.services;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.paypalpaymentassignment.paypalpayment.domain.Product;
import com.paypalpaymentassignment.paypalpayment.dto.ProductDTO;
import com.paypalpaymentassignment.paypalpayment.repositories.ProductRepository;
import com.paypalpaymentassignment.paypalpayment.services.exceptions.DataIntegrityException;
import com.paypalpaymentassignment.paypalpayment.services.exceptions.ObjectNotFoundException;


@Service
public class ProductService {
    @Autowired
    private ProductRepository ProductRepository;

    public Product find(int id){
        Optional<Product> obj = ProductRepository.findById(id);
        return obj.orElseThrow(() ->  new ObjectNotFoundException("Product not found, try again"));
    }

    public Product insert(Product obj){
        obj.setId(null);
        Product Product = ProductRepository.save(obj);
        return Product;
    }

    public Product update(Product obj){
        Product newObj = find(obj.getId());
        updateData(newObj,obj);
        return ProductRepository.save(newObj);
    }

    public void updateData(Product newObj, Product obj){
        newObj.setName(obj.getName());
        newObj.setPrice(obj.getPrice());

    }
  public Product fromDTO(ProductDTO objDto){
        return new Product(objDto.getId(),objDto.getName(),objDto.getPrice());
    }

    public void delete(Integer id){
        find(id);
        try{
            ProductRepository.deleteById(id);
        } catch (DataIntegrityException  e){
            throw new ObjectNotFoundException("Não foi possível excluir o Product");
        }
    }

    public List<Product> findAll(){
        return ProductRepository.findAll();
    }

    public Page<Product> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return ProductRepository.findAll(pageRequest);
    }

}
