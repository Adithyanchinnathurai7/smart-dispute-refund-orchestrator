package com.example.refund.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.refund.repositories.DisputeRefundRepository;

@Service
public class DisputeRefundService {

    @Autowired
    private DisputeRefundRepository repository;

    public Long raiseDispute(Long txnId, String reason) {
        return repository.raiseDispute(txnId, reason);
    }

    public void decideDispute(Long disputeId, String decision) {
        repository.decideDispute(disputeId, decision);
    }

    public Long processRefund(Long disputeId) {
        return repository.processRefund(disputeId);
    }
}

