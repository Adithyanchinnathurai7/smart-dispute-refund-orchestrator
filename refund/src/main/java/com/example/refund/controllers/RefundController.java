package com.example.refund.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.refund.dto.ApiResponse;
import com.example.refund.services.DisputeRefundService;

@RestController
@RequestMapping("/api/refunds")
public class RefundController {

    @Autowired
    private DisputeRefundService service;

    @PostMapping("/{disputeId}")
    public ApiResponse processRefund(
            @PathVariable Long disputeId) {

        Long refundId =
            service.processRefund(disputeId);

        return new ApiResponse(
            "Refund processed successfully",
            refundId
        );
    }
}

