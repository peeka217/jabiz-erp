package com.jabiz.erp.worker.infra;

import com.jabiz.erp.worker.domain.entity.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    Page<Worker> findByNationalityCodeAndDeletedYn(String nationalityCode, String deletedYn, Pageable pageable);

}
