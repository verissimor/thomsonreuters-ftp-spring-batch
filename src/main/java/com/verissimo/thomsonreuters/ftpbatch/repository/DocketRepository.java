package com.verissimo.thomsonreuters.ftpbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verissimo.thomsonreuters.ftpbatch.entities.Docket;

public interface DocketRepository extends JpaRepository<Docket, Long> {

}
