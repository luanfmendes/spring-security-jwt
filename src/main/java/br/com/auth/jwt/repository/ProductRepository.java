package br.com.auth.jwt.repository;

import br.com.auth.jwt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
