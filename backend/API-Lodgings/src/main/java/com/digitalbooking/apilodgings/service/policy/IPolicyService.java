package com.digitalbooking.apilodgings.service.policy;

import com.digitalbooking.apilodgings.dto.PolicyDTO;

import java.util.List;

public interface IPolicyService {

    PolicyDTO findPolicyById(Integer id);
    PolicyDTO findPolicyByTitle(String title);
    PolicyDTO createPolicy(PolicyDTO policy);
    List<PolicyDTO> findAllPolicies();
}
