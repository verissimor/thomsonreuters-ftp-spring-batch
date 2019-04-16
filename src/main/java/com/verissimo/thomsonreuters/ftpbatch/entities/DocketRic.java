package com.verissimo.thomsonreuters.ftpbatch.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "docket_ric")
public class DocketRic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long docketRicCod;

	private String name;
	private boolean isEnabled = false;

	public Long getDocketRicCod() {
		return docketRicCod;
	}

	public void setDocketRicCod(Long docketRicCod) {
		this.docketRicCod = docketRicCod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


}
