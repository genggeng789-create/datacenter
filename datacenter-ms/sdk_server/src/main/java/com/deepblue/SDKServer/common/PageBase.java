package com.deepblue.SDKServer.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageBase {
    private Integer status;
    private int pageSize;
    private int pageIndex;
}
