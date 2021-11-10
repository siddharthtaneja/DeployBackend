package com.CaseStudy.ECart.Repository;

import com.CaseStudy.ECart.Models.Cluster_details_dup;
import com.CaseStudy.ECart.Models.Deploye_details;
import com.CaseStudy.ECart.Models.pojos.req.ClusterRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Cluster_detailsRepository extends JpaRepository<Cluster_details_dup,String> {
    List<String> myfindall(ClusterRequest clusterRequest);
    List<Cluster_details_dup> list(ClusterRequest clusterRequest);
    List<String> findAllIp(ClusterRequest clusterRequest);
    void deletemy(Cluster_details_dup cluster_details_dup,ClusterRequest clusterRequest);
    int save(Cluster_details_dup cluster_details_dup, ClusterRequest clusterRequest);
}
