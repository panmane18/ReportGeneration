package com.report.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ConstructionRecordBO {
    private int customerId;
    private int contractId;
    private String geoZone;
    private String teamCode;
    private String projectCode;
    private int buildDuration;
}
