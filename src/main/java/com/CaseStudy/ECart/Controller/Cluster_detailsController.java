package com.CaseStudy.ECart.Controller;

import com.CaseStudy.ECart.Models.Cluster_details_dup;
import com.CaseStudy.ECart.Models.pojos.req.ClusterRequest;
import com.CaseStudy.ECart.Repository.Cluster_detailsRepository;
import com.CaseStudy.ECart.Service.Cluster_detailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class Cluster_detailsController {
    @Autowired
    Cluster_detailsRepository cluster_detailsRepository;
    @Autowired
    Cluster_detailsService cluster_detailsService;
    @PostMapping("/getall")
    public List<String> getallcluster(@RequestBody ClusterRequest clusterRequest){
        // System.out.println("cluster" + clusterRequest.getEnv_type());
        return cluster_detailsRepository.myfindall(clusterRequest);
    }
    @PostMapping("getAllIp")
    public List<String> getAllip(@RequestBody ClusterRequest clusterRequest){
        return  cluster_detailsRepository.findAllIp(clusterRequest);
    }
    @PostMapping("/getclusterdetails")
    public List<Cluster_details_dup> getlist(@RequestBody ClusterRequest clusterRequest) {
        return cluster_detailsRepository.list(clusterRequest);
    }
    @PostMapping("/addiptocluster/{ip}")
    public String postip(@RequestBody ClusterRequest clusterRequest,@PathVariable("ip")String ip){
        return cluster_detailsService.postip(clusterRequest,ip);
    }
    @PostMapping("/deleteiptocluster")
    public String deleteip(@RequestBody ClusterRequest clusterRequest){
        return cluster_detailsService.deleteip(clusterRequest);
    }
    @PostMapping("/addcluster")
    public int addcluster(@RequestHeader(value = "env") String s,@RequestBody Cluster_details_dup cluster_details){
        ClusterRequest clusterRequest = new ClusterRequest();
        if(s.equalsIgnoreCase("qa")){
            clusterRequest.setEnv_type("qa");
        }
        else if(s.equalsIgnoreCase("dev")){
            clusterRequest.setEnv_type("dev");
        }
        return cluster_detailsRepository.save(cluster_details,clusterRequest);
    }
//    @GetMapping("/deletecluster/{cluster}")
//    public String deleteCluster(@RequestHeader(value = "qa") String s,@PathVariable("cluster") String cluster){
//        Cluster_details_dup cluster_details =cluster_detailsRepository.findAllByCluster_name(cluster);
//        cluster_detailsRepository.delete(cluster_details);
//        return "Succees";
//    }
//    @PostMapping("/updatecluster")
//    public Cluster_details_dup updatecluster(@RequestHeader(value = "qa") String s, @RequestBody Cluster_details_dup cluster_details){
//       // System.out.println(cluster_details);
//        return cluster_detailsRepository.saveAndFlush(cluster_details);
//    }
//    @GetMapping("/updatename/{cluster}/{ip}")
//    public Cluster_details updatenamem(@PathVariable("cluster") String cluster,@PathVariable("ip") String ip){
//        return cluster_detailsService.updatename(cluster,ip);
//    }
    @PostMapping("compare/{id2}")
    public String compare(@RequestBody ClusterRequest clusterRequest ,  @PathVariable("id2") String id2){
        String ownerid = null;
        System.out.println(clusterRequest.getCluster_name());
        int flag = 0;
        List<Cluster_details_dup> list = cluster_detailsRepository.list(clusterRequest);
        for(Cluster_details_dup cluster_details_dup: list){
            if(cluster_details_dup.getOwnerID() != null && !cluster_details_dup.getOwnerID().isEmpty()){
                ownerid = cluster_details_dup.getOwnerID();
                flag = 1;
                break;
            }
        }
        if(flag == 0){
            return "\"value\"";
        }
        else if(flag != 0){
            if(ownerid.equalsIgnoreCase(id2)){
                return "\"tree\"";
            }
            else {
                return "\"" + ownerid + "\"" ;
            }
        }
        return  null;
    }
}
