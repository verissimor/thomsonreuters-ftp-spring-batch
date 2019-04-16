package com.verissimo.thomsonreuters.ftpbatch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verissimo.thomsonreuters.ftpbatch.entities.DocketRic;
import com.verissimo.thomsonreuters.ftpbatch.repository.DocketRicRepository;

@Service
public class DocketRicService {

	@Autowired
	private DocketRicRepository repository;

	public DocketRic findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public DocketRic findByName(String name) {
		return repository.findByName(name).orElse(null);
	}

	public List<DocketRic> findAll() {
		return repository.findAll();
	}

	public void validate(DocketRic docketRic) {
		// main rules (only for example)
		if (docketRic.getName().isEmpty()) {
			throw new IllegalArgumentException("Error docketRic Name don't be empty");
		}
	}

	public DocketRic save(DocketRic docketRic) {
		validate(docketRic);
		return repository.save(docketRic);
	}

	public DocketRic saveByName(String name) {
		DocketRic item = new DocketRic();
		item.setName(name);
		item.setEnabled(true);
		return repository.save(item);
	}

	public void delete(DocketRic docketRic) {
		repository.delete(docketRic);
	}

	public DocketRic findOrInsert(String name) {
		DocketRic item = findByName(name);
		if (item != null) {
			return item;
		}

		return saveByName(name);
	}

}
