package com.jabiz.erp.systemcode.infra;

import com.jabiz.erp.systemcode.domain.entity.SystemCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SystemCodeRepository extends JpaRepository<SystemCode, Long> {

    Optional<List<SystemCode>> findByCodeCategoryOrderBySortSeqAsc(String codeCategory);
}
