package com.verissimo.thomsonreuters.ftpbatch.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verissimo.thomsonreuters.ftpbatch.entities.Docket;
import com.verissimo.thomsonreuters.ftpbatch.repository.DocketRepository;

@Service
public class DocketService {

	@Autowired
	private DocketRepository repository;

	public Docket findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<Docket> findAll() {
		return repository.findAll();
	}

	public void validate(Docket docket) {
		// main rules (only for example)
		if (docket.getPriceEod() < 0) {
			throw new IllegalArgumentException("Error docket PriceEod don't can be less than 0");
		}
	}

	public Docket save(Docket docket) {
		validate(docket);
		return repository.save(docket);
	}

	public List<Docket> save(List<Docket> dockets) {
		// ## ONLY FOR EXAMPLE -> THIS IS SLOW
		return dockets.stream().map(docket -> save(docket)).collect(Collectors.toList());
	}
	
	public void delete(Docket docket) {
		repository.delete(docket);
	}

}
