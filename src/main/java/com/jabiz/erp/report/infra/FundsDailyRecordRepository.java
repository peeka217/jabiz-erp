package com.jabiz.erp.report.infra;

import com.jabiz.erp.report.domain.entity.FundsDailyRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundsDailyRecordRepository extends JpaRepository<FundsDailyRecord, Long>, FundsDailyRecordRepositoryCustom {

}
