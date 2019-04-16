package com.verissimo.thomsonreuters.ftpbatch.cucumber;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.verissimo.thomsonreuters.ftpbatch.entities.Docket;
import com.verissimo.thomsonreuters.ftpbatch.service.DocketService;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SpringBootTest(classes = TestConfig.class)
public class DocketSteps {

	@Autowired
	private DocketService docketService;

	private Exception ex = null;
	private Docket docket = null;

	@Before
	public void restartTest() {
		ex = null;
		docket = null;
	}

	@Given("a docket with price eod {string}")
	public void a_docket_with_price_eod(String priceEod) {
		docket = new Docket();
		docket.setPriceEod(Float.parseFloat(priceEod));
	}

	@When("save the docket")
	public void save_the_docket() {
		try {
			docketService.save(docket);
		} catch (Exception e) {
			ex = e;
		}
	}

	@Then("an error is showed about price eod")
	public void an_error_is_showed_about_price_eod() {
		String msgExpected = "Error docket PriceEod don't can be less than 0";
		Assert.assertEquals(msgExpected, ex.getMessage());
	}

}
