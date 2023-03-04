package com.jabiz.erp.util.service;

import com.jabiz.erp.exception.InvalidDataException;
import com.jabiz.erp.site.domain.entity.Site;
import com.jabiz.erp.site.infra.SiteRepository;
import com.jabiz.erp.systemcode.domain.entity.SystemCode;
import com.jabiz.erp.systemcode.infra.SystemCodeRepository;
import com.jabiz.erp.util.controller.dto.SelectBoxForCodeResponse;
import com.jabiz.erp.util.controller.dto.SelectBoxForIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UtilityService {

    private final SystemCodeRepository systemCodeRepository;
    private final SiteRepository siteRepository;

    @Transactional(readOnly = true)
    public Map<String, List<SelectBoxForCodeResponse>> lookUpSystemCodesForSelectBox(List<String> codeCategories, String menuCode) {
        Map<String, List<SelectBoxForCodeResponse>> selectBoxMap = new HashMap<>();
        codeCategories.forEach(codeCategory -> {
            List<SystemCode> systemCodes =  systemCodeRepository.findByCodeCategoryOrderBySortSeqAsc(codeCategory)
                    .orElseThrow(InvalidDataException::new);
            List<SelectBoxForCodeResponse> responses = new ArrayList<>();
            systemCodes.forEach(systemCode -> {
                if (systemCode.isAvailableMenu(menuCode))
                    responses.add(SelectBoxForCodeResponse.of(systemCode));
            });

            selectBoxMap.put(codeCategory, responses);
        });

        return selectBoxMap;
    }

    @Transactional(readOnly = true)
    public Map<String, List<SelectBoxForIdResponse>> lookUpIdsForSelectBox(List<String> idCategories) {
        Map<String, List<SelectBoxForIdResponse>> selectBoxMap = new HashMap<>();
        idCategories.forEach(idCategory -> {
            switch (idCategory) {
                case "site":
                    List<Site> sites =  siteRepository.findByUseYnEquals("Y")
                            .orElseThrow(InvalidDataException::new);

                    List<SelectBoxForIdResponse> responses = new ArrayList<>();
                    sites.forEach(site -> {
                        responses.add(SelectBoxForIdResponse.of(site.getId(), site.getSiteName()));
                    });

                    selectBoxMap.put(idCategory, responses);
                    break;
                case "company":
                    break;
                default:
            }
        });

        return selectBoxMap;
    }


}
