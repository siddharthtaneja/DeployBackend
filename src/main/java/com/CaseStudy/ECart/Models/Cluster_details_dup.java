package com.CaseStudy.ECart.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@IdClass(Cluster_details_dup.class)
public class Cluster_details_dup implements Serializable {
    @Id
    private String cluster_name;
    @Id
    private String IP;
    private String ownername;
    private String ownerID;

    public Cluster_details_dup(){}

    public String getCluster_name() {
        return cluster_name;
    }

    public void setCluster_name(String cluster_name) {
        this.cluster_name = cluster_name;
    }


    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getOwnerName() {
        return ownername;
    }

    public void setOwnerName(String ownerName) {
        this.ownername = ownerName;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }
}
