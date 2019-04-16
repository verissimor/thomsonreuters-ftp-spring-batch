package com.verissimo.thomsonreuters.ftpbatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.verissimo.thomsonreuters.ftpbatch.entities.Docket;
import com.verissimo.thomsonreuters.ftpbatch.service.DocketService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("api/dockets")
public class DocketController {

	@Autowired
	private DocketService docketService;

	@GetMapping
	@RequestMapping(value = "/", method = { RequestMethod.GET }, //
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "List all dockets", //
			authorizations = { @Authorization(value = "basicAuth") })
	public List<Docket> findAll() {
		return docketService.findAll();
	}
	
}
