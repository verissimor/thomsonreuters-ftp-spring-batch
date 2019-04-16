package com.verissimo.thomsonreuters.ftpbatch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verissimo.thomsonreuters.ftpbatch.entities.DocketRic;

public interface DocketRicRepository extends JpaRepository<DocketRic, Long> {

	Optional<DocketRic> findByName(String name);

}
