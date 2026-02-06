package com.example.refund.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;




@Repository
public class DisputeRefundRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Call raise_dispute
    public Long raiseDispute(Long txnId, String reason) {
        return jdbcTemplate.execute(
            (Connection con) -> {
                CallableStatement cs =
                    con.prepareCall("{ CALL raise_dispute(?, ?, ?) }");
                cs.setLong(1, txnId);
                cs.setString(2, reason);
                cs.registerOutParameter(3, Types.BIGINT);
                return cs;
            },
            (CallableStatement cs) -> {
                cs.execute();
                return cs.getLong(3);
            }
        );
    }

    // Call decide_dispute
    public void decideDispute(Long disputeId, String decision) {
        jdbcTemplate.execute(
            (Connection con) -> {
                CallableStatement cs =
                    con.prepareCall("{ CALL decide_dispute(?, ?) }");
                cs.setLong(1, disputeId);
                cs.setString(2, decision);
                return cs;
            }
        );
    }

    // Call process_refund
    public Long processRefund(Long disputeId) {
        return jdbcTemplate.execute(
            (Connection con) -> {
                CallableStatement cs =
                    con.prepareCall("{ CALL process_refund(?, ?) }");
                cs.setLong(1, disputeId);
                cs.registerOutParameter(2, Types.BIGINT);
                return cs;
            },
            (CallableStatement cs) -> {
                cs.execute();
                return cs.getLong(2);
            }
        );
    }
}

