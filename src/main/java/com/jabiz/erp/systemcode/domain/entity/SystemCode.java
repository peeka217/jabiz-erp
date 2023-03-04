package com.jabiz.erp.systemcode.domain.entity;

import com.jabiz.erp.primitive.entity.PrimitiveEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Arrays;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SystemCode extends PrimitiveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeCategory;
    private String codeCategoryName;
    private String code;
    private String codeName;

    private String unavailableMenuCodes;
    private int sortSeq;

    public Boolean isAvailableMenu(String menuCode) {
        if (unavailableMenuCodes == null)
            return true;
        String[] unavailableMenus = this.unavailableMenuCodes.split("&");

        return !Arrays.stream(unavailableMenus).anyMatch(menuCode::equals);
    }

    @Builder
    public SystemCode(Long id,
                      String codeCategory, String codeCategoryName,
                      String code, String codeName,
                      int sortSeq) {
        this.id = id;

        this.codeCategory = codeCategory;
        this.codeCategoryName = codeCategoryName;
        this.code = code;
        this.codeName = codeName;

        this.sortSeq = sortSeq;
    }

}
