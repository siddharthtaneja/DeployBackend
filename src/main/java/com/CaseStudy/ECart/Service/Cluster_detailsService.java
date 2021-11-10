package com.CaseStudy.ECart.Service;

import com.CaseStudy.ECart.Models.Cluster_details_dup;
import com.CaseStudy.ECart.Models.Deploye_details;
import com.CaseStudy.ECart.Models.pojos.req.ClusterRequest;
import com.CaseStudy.ECart.Models.pojos.req.Summary;
import com.CaseStudy.ECart.Repository.Cluster_detailsRepository;
import com.CaseStudy.ECart.Repository.Cluster_detailsRepositoryImpl;
import com.CaseStudy.ECart.Repository.Deploye_detailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class Cluster_detailsService {
    @Autowired
    Cluster_detailsRepository cluster_detailsRepository;
    @Autowired
    Deploye_detailsRepository deploye_detailsRepository;


    public String postip(ClusterRequest clusterRequest, String ip) {
        List<Cluster_details_dup> ap = cluster_detailsRepository.list(clusterRequest);
        Cluster_details_dup cluster_details_dup = new Cluster_details_dup();
        cluster_details_dup.setCluster_name(ap.get(0).getCluster_name());
        cluster_details_dup.setIP(ip);
        cluster_details_dup.setOwnerID(ap.get(0).getOwnerID());
        cluster_details_dup.setOwnerName(ap.get(0).getOwnerName());
        cluster_detailsRepository.save(cluster_details_dup, clusterRequest);
        return "success";
    }

    public String deleteip(ClusterRequest clusterRequest) {
        List<Cluster_details_dup> ap = cluster_detailsRepository.list(clusterRequest);
        cluster_detailsRepository.deletemy(ap.get(0), clusterRequest);
        return "success";
    }


    public int getsummary2(ClusterRequest clusterRequest, List<Cluster_details_dup> list8) {
        int sum = 0;
        for (Cluster_details_dup cluster_details_dup : list8) {
            clusterRequest.setServer_ip(cluster_details_dup.getIP());
            List<Deploye_details> deploye_details = deploye_detailsRepository.findAllByAll(clusterRequest);
            if(!deploye_details.isEmpty()) {
                for (Deploye_details deploye_details1 : deploye_details) {
                    if (deploye_details1.getIsActive().equals("Y")) {
                        sum++;
                    }
                }
            }
        }
        return sum;
    }

    public int getip2(ClusterRequest clusterRequest) {
        List<Cluster_details_dup> cluster_details1 = cluster_detailsRepository.list(clusterRequest);
        return  cluster_details1.size();
    }

    public List<Summary> getsumm(ClusterRequest clusterRequest) {
        List<String> list2 = cluster_detailsRepository.myfindall(clusterRequest);
        List<Summary> list = new ArrayList<>();
        for (String cluster_details : list2) {
            Summary summary = new Summary();
            int flag = 0;
            clusterRequest.setCluster_name(null);
            clusterRequest.setServer_ip(null);
            clusterRequest.setService_name(null);
            summary.setClustername(cluster_details);
            clusterRequest.setCluster_name(cluster_details);
            summary.setNoofip(getip2(clusterRequest));
            List<Cluster_details_dup> cluster_details1 = cluster_detailsRepository.list(clusterRequest);
            summary.setNoofservices(getsummary2(clusterRequest,cluster_details1));
            for (Cluster_details_dup cluster_details_dup : cluster_details1) {
                if (cluster_details_dup.getOwnerName() != null && !cluster_details_dup.getOwnerName().isEmpty()) {
                    summary.setOwner(cluster_details_dup.getOwnerName());
                    flag = 1;
                    break;
                }
            }
            if(flag == 0){
                summary.setOwner(null);
            }
            list.add(summary);
        }
        return list;
    }
}
