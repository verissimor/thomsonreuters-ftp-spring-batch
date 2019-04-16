package com.verissimo.thomsonreuters.ftpbatch.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.verissimo.thomsonreuters.ftpbatch.entities.Company;
import com.verissimo.thomsonreuters.ftpbatch.service.CompanyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("api/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/", method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Find all company", authorizations = { @Authorization(value = "basicAuth") })
	public List<Company> findAll() {
		return companyService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Get a company by id", authorizations = { @Authorization(value = "basicAuth") })
	public Company findById(@PathVariable("id") @NotNull Long id) {
		return companyService.findById(id);
	}

	@RequestMapping(value = "/", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Save a new company", authorizations = { @Authorization(value = "basicAuth") })
	public Company save(@RequestBody Company company) {
		return companyService.save(company);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Update a company", authorizations = { @Authorization(value = "basicAuth") })
	public Company update(@PathVariable("id") @NotNull Long id, @RequestBody Company request) {
		Company companyDb = companyService.findById(id);
		companyDb.setName(request.getName() != null ? request.getName() : companyDb.getName());
		companyDb.setPhone(request.getPhone() != null ? request.getPhone() : companyDb.getPhone());
		companyDb.setCeo(request.getCeo() != null ? request.getCeo() : companyDb.getCeo());

		return companyService.save(companyDb);
	}

	@RequestMapping(value = "/{id}", method = {
			RequestMethod.DELETE }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Delete a company", authorizations = {
			@Authorization(value = "basicAuth") })
	public Company delete(@PathVariable("id") @NotNull Long id) {
		Company companyDb = companyService.findById(id);
		companyService.delete(companyDb);
		return companyDb;
	}
	
}
