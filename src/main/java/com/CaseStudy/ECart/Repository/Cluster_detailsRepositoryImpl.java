package com.CaseStudy.ECart.Repository;

import com.CaseStudy.ECart.Models.Cluster_details_dup;
import com.CaseStudy.ECart.Models.Deploye_details;
import com.CaseStudy.ECart.Models.pojos.req.ClusterRequest;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Cluster_detailsRepositoryImpl implements Cluster_detailsRepository{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<String> myfindall(ClusterRequest clusterRequest) {
        System.out.println("In findall");
        String query="SELECT distinct cluster_name FROM ";
        String tableName="cluster_details_dup";
        if(clusterRequest.getEnv_type().equalsIgnoreCase("dev")){
            tableName = "dev_cluster_details_dup";
            query = query + tableName;
        }
        else if(clusterRequest.getEnv_type().equalsIgnoreCase("qa")){
            query = query + tableName;
        }
        System.out.println(query);
        List<String> list = entityManager.createNativeQuery(query).getResultList();
        return list;
    }

    @Override
    public List<Cluster_details_dup> findAll() {
        return null;
    }

    @Override
    public List<Cluster_details_dup> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Cluster_details_dup> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Cluster_details_dup> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Cluster_details_dup cluster_details_dup) {

    }

    @Override
    @Transactional
    public void deletemy(Cluster_details_dup cluster_details_dup , ClusterRequest clusterRequest) {
        String query="DELETE FROM ";
        String tableName="cluster_details_dup";
        if(clusterRequest.getEnv_type().equalsIgnoreCase("dev")){
            tableName = "dev_cluster_details_dup";
            query = query + tableName ;
        }
        else if(clusterRequest.getEnv_type().equalsIgnoreCase("qa")){
            query = query + tableName;
        }
        query = query + " WHERE " + "cluster_name='" + cluster_details_dup.getCluster_name() + "'" + " AND " + "ip='" + cluster_details_dup.getIP() + "'" ;
        System.out.println(query);
        entityManager.createNativeQuery(query).executeUpdate();
    }

    @Override
    @Transactional
    public int save(Cluster_details_dup cluster_details_dup,ClusterRequest clusterRequest) {
        System.out.println("in save ip");
        String query="INSERT INTO ";
        String tableName="cluster_details_dup(cluster_name,ip,ownerName,ownerID)";
        if(clusterRequest.getEnv_type().equalsIgnoreCase("dev")){
            tableName = "dev_cluster_details_dup(cluster_name,ip,ownerName,ownerID)";
            query = query + tableName + " VALUES";
        }
        else if(clusterRequest.getEnv_type().equalsIgnoreCase("qa")){
            query = query + tableName + " VALUES";
        }
        query = query + "(" +  "'"  + cluster_details_dup.getCluster_name() + "'" + ","  + "'" + cluster_details_dup.getIP() + "'" + "," + "'" + cluster_details_dup.getOwnerName() + "'" + "," + "'" + cluster_details_dup.getOwnerID() + "'" + ")" ;
        System.out.println(query);
        int cluster_details_dup1 = entityManager.createNativeQuery(query,Cluster_details_dup.class).executeUpdate();
        return  cluster_details_dup1;
    }

    @Override
    public void deleteAll(Iterable<? extends Cluster_details_dup> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Cluster_details_dup> S save(S s) {
        return null;
    }

    @Override
    public <S extends Cluster_details_dup> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Cluster_details_dup> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Cluster_details_dup> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Cluster_details_dup> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Cluster_details_dup getOne(String s) {
        return null;
    }

    @Override
    public <S extends Cluster_details_dup> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Cluster_details_dup> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Cluster_details_dup> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Cluster_details_dup> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Cluster_details_dup> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Cluster_details_dup> boolean exists(Example<S> example) {
        return false;
    }


    @Override
    public List<Cluster_details_dup> list(ClusterRequest request) {
        System.out.println("in ClusterdetailsImpl");
        String query="SELECT * FROM ";
        String tableName="cluster_details_dup";

        boolean putAND=false;
        if(null==request)
        {
            System.out.println("req null");
        }
        if(null==request.getEnv_type())
        {
            System.out.println("req env null");
        }

        if(request.getEnv_type().equalsIgnoreCase("dev"))
        {
            tableName="dev_cluster_details_dup";
        }
        if(null == request.getCluster_name() && null == request.getService_name() && null == request.getServer_ip()){
            query = query + tableName;
        }
        else {
            query = query + tableName + " WHERE ";
            if (null != request.getCluster_name()) {
                query = query + "cluster_name='" + request.getCluster_name() + "' ";
                putAND = true;
            }
            if (null != request.getServer_ip()) {
                if (putAND) {
                    query = query + "AND ";
                }
                query = query + "IP='" + request.getServer_ip() + "' ";
            }
        }
        System.out.println("Query:"+query);
        List<Cluster_details_dup> list = entityManager.createNativeQuery(query,Cluster_details_dup.class).getResultList();


        return list;
    }

    @Override
    public List<String> findAllIp(ClusterRequest clusterRequest) {
        System.out.println("in find all ip");
        String query="SELECT distinct ip FROM ";
        String tableName="cluster_details_dup";
        if(clusterRequest.getEnv_type().equalsIgnoreCase("dev")){
            tableName = "dev_cluster_details_dup";
            query = query+ tableName;
        }
        else if(clusterRequest.getEnv_type().equalsIgnoreCase("qa")){
            query = query + tableName;
        }
        List<String> list = entityManager.createNativeQuery(query).getResultList();
        return list;
    }
}
