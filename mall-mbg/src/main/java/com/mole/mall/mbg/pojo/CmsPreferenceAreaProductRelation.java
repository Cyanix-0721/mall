package com.mole.mall.mbg.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CmsPreferenceAreaProductRelation implements Serializable {
    private Long id;

    private Long preferenceAreaId;

    private Long productId;

    @Serial
    private static final long serialVersionUID = 1L;
}