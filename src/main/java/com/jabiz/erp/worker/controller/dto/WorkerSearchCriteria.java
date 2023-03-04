package com.jabiz.erp.worker.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@NoArgsConstructor
public class WorkerSearchCriteria {

    private Long id;
    private String nationalityCode;
    private String realName;
    private String birthday;
    private String phoneNumber;
    private String accountNumber;

    private int pagingNumber;
    private int pagingSize;

    @Builder
    public WorkerSearchCriteria(Long id,
                                String nationalityCode, String realName, String birthday,
                                String phoneNumber, String accountNumber,
                                int pagingNumber, int pagingSize) {
        this.id = id;

        this.nationalityCode = nationalityCode;
        this.realName = realName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;

        this.pagingNumber = pagingNumber;
        this.pagingSize = pagingSize;
    }
}
