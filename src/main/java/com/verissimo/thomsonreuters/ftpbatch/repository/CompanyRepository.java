package com.verissimo.thomsonreuters.ftpbatch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verissimo.thomsonreuters.ftpbatch.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findByName(String name);

}
