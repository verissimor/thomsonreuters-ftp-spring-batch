package com.verissimo.thomsonreuters.ftpbatch.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verissimo.thomsonreuters.ftpbatch.entities.Company;
import com.verissimo.thomsonreuters.ftpbatch.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;

	public Company findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Company findByName(String name) {
		return repository.findByName(name).orElse(null);
	}

	public List<Company> findAll() {
		return repository.findAll();
	}

	public void validate(Company company) {
		// main rules (only for example)
		if (company.getName().isEmpty()) {
			throw new IllegalArgumentException("Error Company Name don't be empty");
		}
	}

	public Company save(Company company) {
		validate(company);
		return repository.save(company);
	}

	public List<Company> save(List<Company> companies) {
		// ## ONLY FOR EXAMPLE -> THIS IS SLOW
		return companies.stream().map(company -> save(company)).collect(Collectors.toList());
	}
	public Company saveByName(String name) {
		Company item = new Company();
		item.setName(name);
		return repository.save(item);
	}

	public void delete(Company company) {
		repository.delete(company);
	}

	public Company findOrInsert(String name) {
		Company item = findByName(name);
		if (item != null) {
			return item;
		}

		return saveByName(name);
	}

}
