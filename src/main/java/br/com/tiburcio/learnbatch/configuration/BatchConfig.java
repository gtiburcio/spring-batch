package br.com.tiburcio.learnbatch.configuration;

import br.com.tiburcio.learnbatch.entities.Item;
import br.com.tiburcio.learnbatch.entities.Product;
import br.com.tiburcio.learnbatch.services.ItemService;
import br.com.tiburcio.learnbatch.steps.listeners.JobCompletionNotificationListener;
import br.com.tiburcio.learnbatch.steps.processor.Processor;
import br.com.tiburcio.learnbatch.steps.reader.ItemEntityReader;
import br.com.tiburcio.learnbatch.steps.writer.Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Autowired
    private ItemService itemService;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public ItemReader<Item> reader() {
        ItemEntityReader reader = new ItemEntityReader();
        reader.setItemIterator(itemService.findByWorkedFalse().iterator());
        return reader;
    }

    @Bean
    public ItemProcessor<Item, Product> processor() {
        return new Processor();
    }

    @Bean
    public ItemWriter<Product> writer() {
        return new Writer();
    }

    @Bean
    public Job job(JobCompletionNotificationListener listener, Step step) {
        return jobBuilderFactory.get("processor").incrementer(new RunIdIncrementer()).listener(listener).flow(step).end().build();
    }

    @Bean
    public Step step1(PlatformTransactionManager transactionManager) {
        return stepBuilderFactory.get("step1").transactionManager(transactionManager)
                .<Item, Product>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer()).build();
    }
}
