/**
 *
 */
package com.CaseStudy.ECart.Models.pojos.req;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ClusterRequest {

    String cluster_name;
    String server_ip;
    String service_name;
    String env_type;
    /**
     * @return the cluster_name
     */
    public String getCluster_name() {
        return cluster_name;
    }
    /**
     * @param cluster_name the cluster_name to set
     */
    public void setCluster_name(String cluster_name) {
        this.cluster_name = cluster_name;
    }
    /**
     * @return the server_ip
     */
    public String getServer_ip() {
        return server_ip;
    }
    /**
     * @param server_ip the server_ip to set
     */
    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }
    /**
     * @return the service_name
     */
    public String getService_name() {
        return service_name;
    }
    /**
     * @param service_name the service_name to set
     */
    public void setService_name(String service_name) {
        this.service_name = service_name;
    }
    /**
     * @return the env_type
     */
    public String getEnv_type() {
        return env_type;
    }
    /**
     * @param env_type the env_type to set
     */
    public void setEnv_type(String env_type) {
        this.env_type = env_type;
    }

}
