package com.verissimo.thomsonreuters.ftpbatch.batchs.docket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.verissimo.thomsonreuters.ftpbatch.dto.DocketLineDTO;
import com.verissimo.thomsonreuters.ftpbatch.service.FtpManagerService;

@Component
public class DocketItemReaderUtil {

	@Autowired
	private FtpManagerService ftpManagerService;

	@Value("${config.ftp.filename}")
	private String configFtpFilename;

	private static final Logger LOG = LoggerFactory.getLogger(DocketItemReaderUtil.class);

	public FlatFileItemReader<DocketLineDTO> getItemReader() {
		Path local = ftpManagerService.download(configFtpFilename);
		Resource resource = convertPathToResource(local);

		return new FlatFileItemReaderBuilder<DocketLineDTO>() //
				.name("docketItemReader") //
				.resource(resource) //
				.linesToSkip(1) //
				.delimited() //
				.names(getNames()) //
				.fieldSetMapper(getFieldSetMapper()) //
				.build();
	}

	private Resource convertPathToResource(Path local) {
		Resource resource;
		try {
			resource = new InputStreamResource(new FileInputStream(local.toFile()));
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage());
			throw new IllegalArgumentException("Error to retrive the file before download.");
		}
		return resource;
	}

	private static String[] getNames() {
		return new String[] { "fistLineNoName", "seccode", "tradeDate", "sector", "mktCap", "priceEod", "ric", "X",
				"docketId", "startDate", "date", "openCloseStatus", "companyName", "oaId", "caseType1", "caseType2" };
	}

	private static BeanWrapperFieldSetMapper<DocketLineDTO> getFieldSetMapper() {
		return new BeanWrapperFieldSetMapper<DocketLineDTO>() {
			{
				setTargetType(DocketLineDTO.class);
			}
		};
	}

}
