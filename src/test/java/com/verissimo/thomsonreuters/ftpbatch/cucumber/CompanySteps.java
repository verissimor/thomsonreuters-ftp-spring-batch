package com.verissimo.thomsonreuters.ftpbatch.cucumber;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.verissimo.thomsonreuters.ftpbatch.entities.Company;
import com.verissimo.thomsonreuters.ftpbatch.service.CompanyService;
import com.verissimo.thomsonreuters.ftpbatch.service.DocketService;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CompanySteps {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private DocketService docketService;

	private List<Company> companies = new ArrayList<Company>();

	@Before
	public void restartTest() {
		companies = new ArrayList<Company>();

		// clear db
		docketService.findAll().stream().forEach(el -> docketService.delete(el));
		companyService.findAll().stream().forEach(el -> companyService.delete(el));
	}

	@Given("the following companies:")
	public void the_following_companies(List<List<String>> dataTable) {
		// remove the first line
		dataTable.remove(0);

		for (List<String> dataItem : dataTable) {
			Company company = new Company();
			company.setName(dataItem.get(1));
			company.setPhone(Long.parseLong(dataItem.get(2)));
			company.setCeo(dataItem.get(3));

			companies.add(company);
		}
	}

	@When("save the company")
	public void save_the_company() {
		companyService.save(companies);
	}

	@When("save the company avoiding duplicates")
	public void save_the_company_avoiding_duplicates() {
		companies.forEach(el -> companyService.findOrInsert(el.getName()));
	}

	@Then("there are {int} companies saved")
	public void there_are_companies_saved(Integer qtdExpected) {
		List<Company> companiesDb = companyService.findAll();
		Integer coutDb = companiesDb.size();
		Assert.assertEquals(qtdExpected, coutDb);
	}



}
