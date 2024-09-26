package com.mole.mall.mbg.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UmsGrowthChangeHistory implements Serializable {
    private Long id;

    private Long memberId;

    private LocalDateTime createTime;

    @Schema(title = "改变类型：0->增加；1->减少")
    private Integer changeType;

    @Schema(title = "积分改变数量")
    private Integer changeCount;

    @Schema(title = "操作人员")
    private String operateMan;

    @Schema(title = "操作备注")
    private String operateNote;

    @Schema(title = "积分来源：0->购物；1->管理员修改")
    private Integer sourceType;

    @Serial
    private static final long serialVersionUID = 1L;
}