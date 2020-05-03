package br.com.tiburcio.learnbatch.steps.reader;

import br.com.tiburcio.learnbatch.entities.Item;
import lombok.Setter;
import org.springframework.batch.item.ItemReader;

import java.util.Iterator;

@Setter
public class ItemEntityReader implements ItemReader<Item> {

    private Iterator<Item> itemIterator;

    @Override
    public Item read() {
        return itemIterator.hasNext() ? itemIterator.next() : null;
    }
}
