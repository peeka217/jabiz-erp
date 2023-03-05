package com.jabiz.erp.util.controller;

import com.jabiz.erp.util.controller.dto.SelectBoxForCodeResponse;
import com.jabiz.erp.util.controller.dto.SelectBoxForIdResponse;
import com.jabiz.erp.util.service.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/utility")
public class UtilityController {

    private final UtilityService utilityService;

    @GetMapping("/selectboxes/systemcode")
    public ResponseEntity<Map<String, List<SelectBoxForCodeResponse>>> searchSystemCodesForSelectBox(
            @RequestParam(value = "code_categories") List<String> codeCategories,
            @RequestParam(value = "menu_code") String menuCode) {

        return ResponseEntity.ok(utilityService.searchSystemCodesForSelectBox(codeCategories, menuCode));
    }

    @GetMapping("/selectboxes/id")
    public ResponseEntity<Map<String, List<SelectBoxForIdResponse>>> searchIdsforSelectBox(
            @RequestParam(value = "id_categories") List<String> idCategories) {
        return ResponseEntity.ok(utilityService.searchIdsForSelectBox(idCategories));
    }
}
