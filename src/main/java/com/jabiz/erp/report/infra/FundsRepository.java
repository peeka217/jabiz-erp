package com.jabiz.erp.report.infra;

import com.jabiz.erp.report.domain.entity.Funds;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface FundsRepository extends JpaRepository<Funds, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Funds> findById(Long id);

}
