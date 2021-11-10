package com.CaseStudy.ECart.Service;

import java.util.List;

import org.apache.http.conn.HttpHostConnectException;
import org.springframework.stereotype.Service;

import com.CaseStudy.ECart.Models.Deploye_details;
import com.CaseStudy.ECart.Models.pojos.req.ClusterRequest;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DockerClientBuilder;

@Service
public class DockerService {

	public List<Deploye_details> dummy(List<Deploye_details> details) {
		System.out.println("dummy method");

		for (Deploye_details detail : details) {

			String IP = detail.getIP();
			DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://" + IP + ":2375").build();

			
			List<Container> containers=null;
			try {
			containers = dockerClient.listContainersCmd().withShowAll(true).exec();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				detail.setContainer_status("not able to connect to server");
				detail.setDeployed_version_onServer("not able to Cconnect to server");
				continue;
			}
			System.out.println("ss" + containers);
			for (Container container : containers) {
				//System.out.println("DB serviceName :" + detail.getService_name());
				//System.out.println("Container Name on server :" + container.getNames()[0]);
				String ports = null;
				if(container.ports.length != 0) {
					for (int i = 0; i < container.ports.length; i++) {
						if (i == 0) {
							if(container.ports[i].getPublicPort() != null) {
								int port = container.ports[i].getPublicPort();
								ports = Integer.toString(port);
								ports = ports + ",";
							}
						} else {
							if (container.ports[i].getPublicPort() != null) {
								int port = container.ports[i].getPublicPort();
								String myport = Integer.toString(port);
								ports = ports + myport + ",";
							}
						}
					}
				}
				String containerNameonServer = container.getNames()[0].replace("/", "");
				//System.out.println("Container Name on server afterreplace :" + containerNameonServer);

				if (detail.getService_name().equalsIgnoreCase(containerNameonServer)) {
				//	System.out.println("container: " + container.getNames() + " ports: " + container.getNames()
				//			+ " version: " + container.getImage()+" status:"+container.getStatus()+" state:"+container.getState());
					String imageParts[] = container.getImage().split(":");
					detail.setContainer_status(container.getStatus());
					
					try {
							detail.setDeployed_version_onServer(imageParts[1]);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						if(ports != null) {
							detail.setService_name(detail.getService_name() + "(" + ports.substring(0, ports.length() - 1) + ")");
						}
					}
					catch (Exception e){
						e.printStackTrace();
					}
					// System.out.println("image: "+container.getImage());
				}
			}

		}
		return details;
	}
	
	public boolean restartContainer(ClusterRequest request) {
		
		
		System.out.println("restart docker method");

		

			String IP = request.getServer_ip();
			DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://" + IP + ":2375").build();

			boolean isRestartDone=false;
			List<Container> containers=null;
			try {
			containers = dockerClient.listContainersCmd().withShowAll(true).exec();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
                        String uicontainer  = new String();
			if(request.getService_name().contains("(")){
				int index = request.getService_name().indexOf('(');
				uicontainer = request.getService_name().substring(0,index);
			}
			else{
				uicontainer = request.getService_name();
			}
			
			for (Container container : containers) {
				String containerNameonServer = container.getNames()[0].replace("/", "");


				if (uicontainer.equalsIgnoreCase(containerNameonServer)) {
				
					dockerClient.restartContainerCmd(uicontainer).exec();
					isRestartDone=true;
				}
			}

		
	return 	isRestartDone;
	}
	
}