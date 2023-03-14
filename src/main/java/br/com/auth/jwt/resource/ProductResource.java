package br.com.auth.jwt.resource;

import br.com.auth.jwt.model.Product;
import br.com.auth.jwt.repository.ProductRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @DeleteMapping("/remover/{codigo}")
    @RolesAllowed("ADMIN")
    public void remover(@PathVariable int codigo){
        repository.deleteById(codigo);
    }

}
