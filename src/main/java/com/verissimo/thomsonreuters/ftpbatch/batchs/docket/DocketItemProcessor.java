package com.verissimo.thomsonreuters.ftpbatch.batchs.docket;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.verissimo.thomsonreuters.ftpbatch.dto.DocketLineDTO;
import com.verissimo.thomsonreuters.ftpbatch.entities.Company;
import com.verissimo.thomsonreuters.ftpbatch.entities.Docket;
import com.verissimo.thomsonreuters.ftpbatch.entities.DocketRic;
import com.verissimo.thomsonreuters.ftpbatch.service.CompanyService;
import com.verissimo.thomsonreuters.ftpbatch.service.DocketRicService;

public class DocketItemProcessor implements ItemProcessor<DocketLineDTO, Docket> {

	private static final Logger LOG = LoggerFactory.getLogger(DocketItemProcessor.class);

	@Autowired
	private DocketRicService docketRicService;

	@Autowired
	private CompanyService companyService;

	@Override
	public Docket process(final DocketLineDTO lineDTO) throws Exception {
		LOG.info("Processing: " + lineDTO.getSeccode() + ";" + lineDTO.getTradeDate() + ";" + lineDTO.getDocketId()
				+ "..");

		final Docket docket = new Docket();
		
		// Data transformation - DocketRic
		DocketRic docketRic = docketRicService.findOrInsert(lineDTO.getRic());
		docket.setRic(docketRic);
		
		// Data transformation - Company
		Company company = companyService.findOrInsert(lineDTO.getCompanyName());
		docket.setCompany(company);

		// Simple type conversion
		docket.setSeccode(Integer.parseInt(lineDTO.getSeccode()));
		docket.setTradeDate(LocalDate.parse(lineDTO.getTradeDate()));
		docket.setMktCap(Float.parseFloat(lineDTO.getMktCap()));
		docket.setPriceEod(Float.parseFloat(lineDTO.getPriceEod()));
		docket.setSector(Integer.parseInt(lineDTO.getSector()));
		docket.setX(Integer.parseInt(lineDTO.getX()));
		docket.setDocketId(lineDTO.getDocketId());
		docket.setStartDate(LocalDate.parse(lineDTO.getStartDate()));
		docket.setDate(LocalDate.parse(lineDTO.getDate()));
		docket.setOpenCloseStatus(lineDTO.getOpenCloseStatus());
		docket.setOaId(lineDTO.getOaId());
		docket.setCaseType1(lineDTO.getCaseType1());
		docket.setCaseType2(lineDTO.getCaseType2());

		return docket;
	}
}
