package com.mole.mall.mbg.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsFlashPromotion implements Serializable {
    private Long id;

    @Schema(title = "秒杀时间段名称")
    private String title;

    @Schema(title = "开始日期")
    private LocalDate startDate;

    @Schema(title = "结束日期")
    private LocalDate endDate;

    @Schema(title = "上下线状态")
    private Integer status;

    @Schema(title = "创建时间")
    private LocalDateTime createTime;

    @Serial
    private static final long serialVersionUID = 1L;
}