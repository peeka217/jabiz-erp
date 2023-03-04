package com.jabiz.erp.site.infra;

import com.jabiz.erp.site.domain.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SiteRepository extends JpaRepository<Site, Long> {

    Optional<List<Site>> findByUseYnEquals(String useYn);

}
