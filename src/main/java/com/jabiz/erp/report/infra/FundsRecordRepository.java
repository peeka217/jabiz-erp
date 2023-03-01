package com.jabiz.erp.report.infra;

import com.jabiz.erp.report.domain.entity.FundsRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundsRecordRepository extends JpaRepository<FundsRecord, Long>, FundsRecordRepositoryCustom {
}
