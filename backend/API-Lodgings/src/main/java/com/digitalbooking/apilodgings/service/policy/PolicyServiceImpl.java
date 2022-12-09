package com.digitalbooking.apilodgings.service.policy;

import com.digitalbooking.apilodgings.dto.policy.PolicyDTO;
import com.digitalbooking.apilodgings.repository.IPolicyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "PolicyServiceImpl")
public class PolicyServiceImpl implements IPolicyService {

    private final IPolicyRepository policyRepository;

    private final ObjectMapper mapper;


    @Autowired
    public PolicyServiceImpl(IPolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
        mapper = new ObjectMapper();
    }


    @Override
    public PolicyDTO findPolicyById(Integer id) {
        return null;
    }

    @Override
    public PolicyDTO findPolicyByTitle(String title) {
        return null;
    }

    @Override
    public PolicyDTO createPolicy(PolicyDTO policy) {
        return null;
    }

    @Override
    public List<PolicyDTO> findAllPolicies() {
        return null;
    }
}
