package com.CaseStudy.ECart.Controller;

import com.CaseStudy.ECart.Models.Master_service;
import com.CaseStudy.ECart.Models.pojos.req.ClusterRequest;
import com.CaseStudy.ECart.Models.pojos.req.Summary;
import com.CaseStudy.ECart.Repository.Master_serviceRepository;
import com.CaseStudy.ECart.Service.Cluster_detailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
//@RequestMapping("/qamaster")
@CrossOrigin(origins = "*")
public class Master_serviceController {
    @Autowired
    Master_serviceRepository master_serviceRepository;
    @Autowired
    Cluster_detailsService cluster_detailsService;
    @PostMapping("/getlistmaster")
    public List<Master_service> getlist(@RequestBody ClusterRequest clusterRequest){
        System.out.println(clusterRequest.getEnv_type());
        return master_serviceRepository.findAll(clusterRequest);
    }
    @PostMapping("/addservice")
    public void postnew(@RequestHeader(value = "env")String s,@RequestBody Master_service master_service){
        ClusterRequest clusterRequest = new ClusterRequest();
        if(s.equalsIgnoreCase("qa")){
            clusterRequest.setEnv_type("qa");
        }
        else if(s.equalsIgnoreCase("dev")){
            clusterRequest.setEnv_type("dev");
        }
        System.out.print(master_service);
        master_serviceRepository.savemy(clusterRequest,master_service);
    }
    //    @GetMapping("/deleteservice/{service}")
//    public String deleteservice(@PathVariable("service")String service){
//        master_serviceRepository.deleteById(service);
//        return "Success";
//    }
    @PostMapping("/getmysummary")
    public List<Summary> getSumm(@RequestBody ClusterRequest clusterRequest) {
        return cluster_detailsService.getsumm(clusterRequest);
    }
}
