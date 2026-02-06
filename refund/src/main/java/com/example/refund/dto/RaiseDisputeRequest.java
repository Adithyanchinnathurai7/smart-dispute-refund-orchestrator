package com.example.refund.dto;

import lombok.Data;

@Data
public class RaiseDisputeRequest {
    private Long txnId;
    private String reason;
}

