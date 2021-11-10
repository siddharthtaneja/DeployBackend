package com.CaseStudy.ECart.Repository;

import com.CaseStudy.ECart.Models.Master_service;
import com.CaseStudy.ECart.Models.pojos.req.ClusterRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Master_serviceRepository extends JpaRepository<Master_service,String> {
    List<Master_service> findAll(ClusterRequest clusterRequest);
    void savemy(ClusterRequest clusterRequest,Master_service master_service);
}
