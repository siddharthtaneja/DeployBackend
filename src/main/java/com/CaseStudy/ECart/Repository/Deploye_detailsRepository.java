package com.CaseStudy.ECart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.CaseStudy.ECart.Models.Deploye_details;
import com.CaseStudy.ECart.Models.pojos.req.ClusterRequest;
//@Repository
public interface Deploye_detailsRepository extends JpaRepository<Deploye_details,String> {
 List<Deploye_details> findAllByAll(ClusterRequest request);
 void updateip(ClusterRequest clusterRequest,String isActice , String ip );
 @Modifying
 @Transactional
 @Query(value = "INSERT INTO deploye_details(`cluster_name`, `service_name`, `IP`, `version`, `isActive`) VALUES (?1 ,?2 ,?3 ,?4 ,?5)" , nativeQuery = true)
 void savecustom(String cluster ,String service ,String ip ,String version ,String isActive);
 @Modifying
 @Transactional
 @Query(value = "INSERT INTO dev_deploy_details(`cluster_name`, `service_name`, `IP`, `version`, `isActive`) VALUES (?1 ,?2 ,?3 ,?4 ,?5)" , nativeQuery = true)
 void savecustomdev(String cluster ,String service ,String ip ,String version ,String isActive);
}
