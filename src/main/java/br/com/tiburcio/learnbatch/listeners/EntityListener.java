package br.com.tiburcio.learnbatch.listeners;

import br.com.tiburcio.learnbatch.entities.Product;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class EntityListener {

    @PrePersist
    public void prePersist(Product product) {
        product.setWorkedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(Product product) {
        product.setWorkedDate(LocalDateTime.now());
    }
}
