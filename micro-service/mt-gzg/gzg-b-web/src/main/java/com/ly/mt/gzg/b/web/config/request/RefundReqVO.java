package com.ly.mt.gzg.b.web.config.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class RefundReqVO {
    @NotBlank(message = "orderId不能为空")
    private String orderId;
}
