package com.verissimo.thomsonreuters.ftpbatch.batchs.docket;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.verissimo.thomsonreuters.ftpbatch.entities.Docket;
import com.verissimo.thomsonreuters.ftpbatch.service.DocketService;

@Component
public class DocketItemWriter implements ItemWriter<Docket> {

	private static final Logger LOG = LoggerFactory.getLogger(DocketItemWriter.class);

	@Autowired
	private DocketService docketService;

	@Override
	public void write(List<? extends Docket> items) throws Exception {

		docketService.save((List<Docket>) items);
	}
}

