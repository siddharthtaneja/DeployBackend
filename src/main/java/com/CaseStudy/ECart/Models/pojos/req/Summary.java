package com.CaseStudy.ECart.Models.pojos.req;

public class Summary {
    String clustername;
    int noofip;
    int noofservices;
    String Owner;

    public String getClustername() {
        return clustername;
    }

    public void setClustername(String clustername) {
        this.clustername = clustername;
    }

    public int getNoofip() {
        return noofip;
    }

    public void setNoofip(int noofip) {
        this.noofip = noofip;
    }

    public int getNoofservices() {
        return noofservices;
    }

    public void setNoofservices(int noofservices) {
        this.noofservices = noofservices;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }
}
