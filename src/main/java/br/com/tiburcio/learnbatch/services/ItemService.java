package br.com.tiburcio.learnbatch.services;

import br.com.tiburcio.learnbatch.entities.Item;
import br.com.tiburcio.learnbatch.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findByWorkedFalse() {
        return itemRepository.findByWorkedFalse();
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }
}
