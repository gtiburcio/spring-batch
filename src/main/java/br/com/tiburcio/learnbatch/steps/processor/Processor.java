package br.com.tiburcio.learnbatch.steps.processor;

import br.com.tiburcio.learnbatch.entities.Item;
import br.com.tiburcio.learnbatch.entities.Product;
import br.com.tiburcio.learnbatch.services.ItemService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class Processor implements ItemProcessor<Item, Product> {

    @Autowired
    private ItemService itemService;

    @Override
    public Product process(Item item) {
        BigDecimal attr = new BigDecimal(item.getAttribute());
        BigDecimal percentage = new BigDecimal(item.getPercentage() / 100);
        BigDecimal finalValue = item.getTax().add(attr);
        if (percentage.intValue() != 0) {
            finalValue.divide(percentage);
        }
        Product product = item.getProduct();
        product.setTotalValue(finalValue);
        setWorkedItem(item);
        return product;
    }

    private void setWorkedItem(Item item) {
        item.setWorked(true);
        itemService.saveItem(item);
    }
}
