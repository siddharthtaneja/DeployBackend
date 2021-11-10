package com.CaseStudy.ECart.Service;

import com.CaseStudy.ECart.Models.Cluster_details_dup;
import com.CaseStudy.ECart.Models.Deploye_details;
import com.CaseStudy.ECart.Models.Master_service;
import com.CaseStudy.ECart.Models.pojos.req.ClusterRequest;
import com.CaseStudy.ECart.Models.pojos.req.FullSummary;
import com.CaseStudy.ECart.Repository.Cluster_detailsRepository;
import com.CaseStudy.ECart.Repository.Deploye_detailsRepository;
import com.CaseStudy.ECart.Repository.Master_serviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Deploye_detailsService {
    @Autowired
    Deploye_detailsRepository deploye_detailsRepository;
    @Autowired
    Cluster_detailsRepository cluster_detailsRepository;
    @Autowired
    Master_serviceRepository master_serviceRepository;
    public String updateip(ClusterRequest clusterRequest, String isActive, String ip ) {
        deploye_detailsRepository.updateip(clusterRequest,isActive,ip);
        return "Success";
    }


    public List<FullSummary> getSummary(ClusterRequest cluster,String clustername) {
        List<FullSummary> list4 = new ArrayList<FullSummary>();
        List<Master_service> list = master_serviceRepository.findAll(cluster);
        cluster.setCluster_name(clustername);
        for(Master_service master_service: list){
            FullSummary fullSummary = new FullSummary();
            cluster.setService_name(master_service.getService_name());
            fullSummary.setService(master_service.getService_name());
            List<Deploye_details> list1 = deploye_detailsRepository.findAllByAll(cluster);
            if(list1.isEmpty()){
                fullSummary.setIp(null);
            }
            else {
                if (list1.get(0).getIsActive().equals("Y")) {
                    String str = list1.get(0).getVersion();
                    if(!str.equals("dummy")) {
                        fullSummary.setIp(list1.get(0).getIP());
                    }
                }
            }
            list4.add(fullSummary);
        }
        return list4;
    }
}
