package br.com.tiburcio.learnbatch.repositories;

import br.com.tiburcio.learnbatch.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
