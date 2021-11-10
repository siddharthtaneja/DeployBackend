package com.CaseStudy.ECart.Controller;

import java.util.List;
import java.util.Random;

import com.CaseStudy.ECart.Models.pojos.req.FullSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.CaseStudy.ECart.Models.Deploye_details;
import com.CaseStudy.ECart.Models.pojos.req.ClusterRequest;
import com.CaseStudy.ECart.Repository.Deploye_detailsRepository;
import com.CaseStudy.ECart.Service.Deploye_detailsService;
import com.CaseStudy.ECart.Service.DockerService;

@RestController
//@RequestMapping("/qadeploye")
@CrossOrigin(origins = "*")
public class Deloye_detailsController {
    @Autowired
    Deploye_detailsRepository deploye_detailsRepository;
    @Autowired
    Deploye_detailsService deploye_detailsService;
    @Autowired
    DockerService dockerService;
    @GetMapping("/health")
    public String health() {
        return "up and running :P";
    }
    @PostMapping("/getlist")
    public List<Deploye_details> getlistPost(@RequestBody ClusterRequest request) {
    	
    	List<Deploye_details> detailsDB=deploye_detailsRepository.findAllByAll(request);
    	List<Deploye_details>  detailsDBAndServer=dockerService.dummy(detailsDB);
        return detailsDBAndServer;
    }
    @PostMapping("/updateip/{isActive}/{ip}")
    public String updateip(@RequestBody ClusterRequest clusterRequest ,@PathVariable("isActive") String isactive , @PathVariable("ip") String ip){
        return deploye_detailsService.updateip(clusterRequest,isactive, ip);
    }
    @PostMapping("/summaryDetails/{cluster}")
    public List<FullSummary> getSummaryd(@RequestBody ClusterRequest clusterRequest,@PathVariable("cluster") String cluster) {
        return deploye_detailsService.getSummary(clusterRequest,cluster);
    }
    @GetMapping("/postnewservice/{clus}/{ser}/{ip}")
    public String postnew(@RequestHeader(value = "env") String s,@PathVariable("clus") String clus , @PathVariable("ser") String ser , @PathVariable("ip") String ip ) {
        String ver = "dummy";
        String isActive = "Y";
        if(s.equalsIgnoreCase("qa")) {
            deploye_detailsRepository.savecustom(clus, ser, ip, ver, isActive);	
        }
        else if(s.equalsIgnoreCase("dev")){
            deploye_detailsRepository.savecustomdev(clus,ser,ip,ver,isActive);
        }
        return "200";
    }
    
    @PostMapping("/restartContainer")
    public Object restartContainer(@RequestBody ClusterRequest request) {
    	
    	if(null==request.getServer_ip()||request.getServer_ip().isEmpty())
    	{
    		return "IP can not be null or empty.";
    	}
    	if(null==request.getService_name()||request.getService_name().isEmpty())
    	{
    		return "Service Name can not be null or empty.";
    	}
    	
    	boolean restartState=dockerService.restartContainer(request);
    	if(restartState) {
    	List<Deploye_details> detailsDB=deploye_detailsRepository.findAllByAll(request);
    	List<Deploye_details>  detailsDBAndServer=dockerService.dummy(detailsDB);
        return detailsDBAndServer;
    }else
    {
    	return "error in restart probabily not container found ";
    }
    }
}
