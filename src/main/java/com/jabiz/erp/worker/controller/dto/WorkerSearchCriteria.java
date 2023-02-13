package com.jabiz.erp.worker.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@NoArgsConstructor
public class WorkerSearchCriteria {

    private String nationalityCode;
    private String realName;
    private String birthday;
    private String phoneNumber;
    private String accountNumber;

    @Builder
    public WorkerSearchCriteria(String nationalityCode, String realName, String birthday,
                                String phoneNumber,String accountNumber) {
        this.nationalityCode = nationalityCode;
        this.realName = realName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
    }
}
