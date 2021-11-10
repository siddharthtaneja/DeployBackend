/**
 *
 */
package com.CaseStudy.ECart.Repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.CaseStudy.ECart.Models.Deploye_details;
import com.CaseStudy.ECart.Models.pojos.req.ClusterRequest;
import org.springframework.lang.Nullable;


public class Deploye_detailsRepositoryImpl implements Deploye_detailsRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Nullable
    public List<Deploye_details> findAllByAll(ClusterRequest request) {

        System.out.println("in Deploye_detailsRepositoryImpl");
        String query="SELECT * FROM ";
        String tableName="deploye_details";

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
            tableName="dev_deploy_details";
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
                putAND = true;

            }
            if (null != request.getService_name()) {
                if (putAND) {
                    query = query + "AND ";
                }
                query = query + "service_name='" + request.getService_name() + "' ";
            }
        }
        System.out.println("Query:"+query);
        List<Deploye_details> list = entityManager.createNativeQuery(query,Deploye_details.class).getResultList();


        return list;

    }

    @Override
    @Transactional
    public void updateip(ClusterRequest clusterRequest, String isActice, String ip) {
        System.out.println("In update ip");
        String query="UPDATE ";
        String tableName="deploye_details";
        if(clusterRequest.getEnv_type().equalsIgnoreCase("dev")){
            tableName = "dev_deploye_details";
            query = query + tableName ;
        }
        else if(clusterRequest.getEnv_type().equalsIgnoreCase("qa")){
            query = query + tableName;
        }
        query = query + " SET ip =" +"'" + ip +"'" + "," + "isactive='" + isActice + "'"  + " WHERE " + "cluster_name='" + clusterRequest.getCluster_name() + " ' " + " AND " + "service_name='" + clusterRequest.getService_name() + "'" ;
        System.out.println(query);
        entityManager.createNativeQuery(query,Deploye_details.class).executeUpdate();
    }


    @Override
    public List<Deploye_details> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Deploye_details> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Deploye_details> findAllById(Iterable<String> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Deploye_details> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends Deploye_details> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Deploye_details> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub

    }

    @Override
    public Deploye_details getOne(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Deploye_details> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Deploye_details> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Deploye_details> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Deploye_details> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Deploye_details> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Deploye_details entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(Iterable<? extends Deploye_details> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends Deploye_details> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Deploye_details> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Deploye_details> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends Deploye_details> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public void savecustom(String cluster, String service, String ip, String version, String isActive) {
        // TODO Auto-generated method stub

    }

    @Override
    public void savecustomdev(String cluster, String service, String ip, String version, String isActive) {

    }

}
