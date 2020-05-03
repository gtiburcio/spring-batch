package br.com.tiburcio.learnbatch.repositories;

import br.com.tiburcio.learnbatch.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByWorkedFalse();
}
