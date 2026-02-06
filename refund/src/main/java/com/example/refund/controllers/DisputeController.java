package com.example.refund.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.refund.dto.ApiResponse;
import com.example.refund.dto.DecisionRequest;
import com.example.refund.dto.RaiseDisputeRequest;
import com.example.refund.services.DisputeRefundService;

@RestController
@RequestMapping("/api/disputes")
public class DisputeController {

    @Autowired
    private DisputeRefundService service;

    @PostMapping
    public ApiResponse raiseDispute(
            @RequestBody RaiseDisputeRequest request) {

        Long disputeId =
            service.raiseDispute(
                request.getTxnId(),
                request.getReason()
            );

        return new ApiResponse(
            "Dispute raised successfully",
            disputeId
        );
    }

    @PostMapping("/{id}/decision")
    public ApiResponse decideDispute(
            @PathVariable Long id,
            @RequestBody DecisionRequest request) {

        service.decideDispute(id, request.getDecision());

        return new ApiResponse(
            "Dispute decision updated",
            null
        );
    }
}

