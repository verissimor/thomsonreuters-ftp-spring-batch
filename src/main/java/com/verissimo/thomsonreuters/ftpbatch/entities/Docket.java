package com.verissimo.thomsonreuters.ftpbatch.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Entity
@Table(name = "docket")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class Docket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long docketCod;

	private Integer seccode;
	private LocalDate tradeDate;
	private Integer sector;

	private Float mktCap;
	private Float priceEod;

	@OneToOne(fetch = FetchType.LAZY)
	private DocketRic ric;

	private Integer X;
	private String docketId;
	private LocalDate startDate;
	private LocalDate date;
	private String openCloseStatus;

	@OneToOne(fetch = FetchType.LAZY)
	private Company company;

	private String oaId;
	private String caseType1;
	private String caseType2;

	public Long getDocketCod() {
		return docketCod;
	}

	public void setDocketCod(Long docketCod) {
		this.docketCod = docketCod;
	}

	public Integer getSeccode() {
		return seccode;
	}

	public void setSeccode(Integer seccode) {
		this.seccode = seccode;
	}

	public LocalDate getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDate tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Integer getSector() {
		return sector;
	}

	public void setSector(Integer sector) {
		this.sector = sector;
	}

	public Float getMktCap() {
		return mktCap;
	}

	public void setMktCap(Float mktCap) {
		this.mktCap = mktCap;
	}

	public Float getPriceEod() {
		return priceEod;
	}

	public void setPriceEod(Float priceEod) {
		this.priceEod = priceEod;
	}

	public DocketRic getRic() {
		return ric;
	}

	public void setRic(DocketRic ric) {
		this.ric = ric;
	}

	public Integer getX() {
		return X;
	}

	public void setX(Integer x) {
		X = x;
	}

	public String getDocketId() {
		return docketId;
	}

	public void setDocketId(String docketId) {
		this.docketId = docketId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getOpenCloseStatus() {
		return openCloseStatus;
	}

	public void setOpenCloseStatus(String openCloseStatus) {
		this.openCloseStatus = openCloseStatus;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getOaId() {
		return oaId;
	}

	public void setOaId(String oaId) {
		this.oaId = oaId;
	}

	public String getCaseType1() {
		return caseType1;
	}

	public void setCaseType1(String caseType1) {
		this.caseType1 = caseType1;
	}

	public String getCaseType2() {
		return caseType2;
	}

	public void setCaseType2(String caseType2) {
		this.caseType2 = caseType2;
	}

}
