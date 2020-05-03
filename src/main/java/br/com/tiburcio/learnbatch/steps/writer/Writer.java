package br.com.tiburcio.learnbatch.steps.writer;

import br.com.tiburcio.learnbatch.entities.Product;
import br.com.tiburcio.learnbatch.services.ProductService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Writer implements ItemWriter<Product> {

    @Autowired
    private ProductService productService;

    @Override
    public void write(List<? extends Product> products) throws Exception {
        products.forEach(product -> product.setEnd(true));
        productService.saveAll((List<Product>) products);
    }
}
