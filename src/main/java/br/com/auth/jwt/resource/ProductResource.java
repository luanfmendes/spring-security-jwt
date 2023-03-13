package br.com.auth.jwt.resource;

import br.com.auth.jwt.model.Product;
import br.com.auth.jwt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductResource {
    @Autowired
    private ProductRepository repository;
    @GetMapping("/listar")
    public List<Product> listar(){
        return repository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar")
    public Product cadastrar(@RequestBody Product product){
        return repository.save(product);
    }

}
