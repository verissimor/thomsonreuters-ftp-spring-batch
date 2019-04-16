package com.verissimo.thomsonreuters.ftpbatch.batchs.docket;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.verissimo.thomsonreuters.ftpbatch.dto.DocketLineDTO;
import com.verissimo.thomsonreuters.ftpbatch.entities.Docket;

@Configuration
@EnableBatchProcessing
public class DocketBatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DocketItemReaderUtil docketItemReaderUtil;

	@Bean
	public FlatFileItemReader<DocketLineDTO> reader() {
		return docketItemReaderUtil.getItemReader();
	}

	@Bean
	public DocketItemProcessor processor() {
		return new DocketItemProcessor();
	}

	@Bean
	public DocketItemWriter writer() {
		return new DocketItemWriter();
	}

	@Bean
	public Job importDocketJob(DocketJobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importDocketJob") //
				.incrementer(new RunIdIncrementer()) //
				.listener(listener) //
				.flow(step1) //
				.end() //
				.build();
	}


	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1") //
				.<DocketLineDTO, Docket>chunk(10) //
				.reader(reader()) //
				.processor(processor()) //
				.writer(writer()) //
				.build();
	}

}
