package com.verissimo.thomsonreuters.ftpbatch.batchs.docket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.verissimo.thomsonreuters.ftpbatch.service.FtpManagerService;

@Component
public class DocketJobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger LOG = LoggerFactory.getLogger(DocketJobCompletionNotificationListener.class);

	@Value("${config.ftp.filename}")
	private String configFtpFilename;

	@Autowired
	private FtpManagerService ftpManagerService;

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOG.info("------> Running After Job: deleting the ftp file");

			// disabled for testing purpose
			// ftpManagerService.delete(configFtpFilename);
		}
	}
}
