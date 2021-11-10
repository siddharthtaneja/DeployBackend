package com.CaseStudy.ECart.Models;

import org.hibernate.annotations.NaturalId;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@IdClass(Deploye_details.class)
public class Deploye_details implements Serializable {
    @Id
    private String cluster_name;
    @Id
    private String service_name;
    private String iP;
    private String isactive;
    private Timestamp dep_timestamp;
    private String version;
    private String dep_by;
    private String previous_version;
    private String deployed_version_onServer;
    private String container_status;

    public Deploye_details(){}
    public String getCluster_name() {
        return cluster_name;
    }

    public void setCluster_name(String cluster_name) {
        this.cluster_name = cluster_name;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getIP() {
        return iP;
    }

    public void setIP(String IP) {
        this.iP = IP;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIsActive() {
        return isactive;
    }

    public void setIsActive(String isActive) {
        this.isactive = isActive;
    }

    public Timestamp getDep_timestamp() {
        return dep_timestamp;
    }

    public void setDep_timestamp(Timestamp dep_timestamp) {
        this.dep_timestamp = dep_timestamp;
    }

    public String getDep_by() {
        return dep_by;
    }

    public void setDep_by(String dep_by) {
        this.dep_by = dep_by;
    }

    public String getPrevious_version() {
        return previous_version;
    }

    public void setPrevious_version(String previous_version) {
        this.previous_version = previous_version;
    }
	/**
	 * @return the deployed_version_onServer
	 */
	public String getDeployed_version_onServer() {
		return deployed_version_onServer;
	}
	/**
	 * @param deployed_version_onServer the deployed_version_onServer to set
	 */
	public void setDeployed_version_onServer(String deployed_version_onServer) {
		this.deployed_version_onServer = deployed_version_onServer;
	}
	/**
	 * @return the container_state
	 */
	public String getContainer_status() {
		return container_status;
	}
	/**
	 * @param container_state the container_state to set
	 */
	public void setContainer_status(String container_status) {
		this.container_status = container_status;
	}
}
