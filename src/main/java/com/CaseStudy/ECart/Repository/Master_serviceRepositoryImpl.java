package com.CaseStudy.ECart.Repository;

import com.CaseStudy.ECart.Models.Cluster_details_dup;
import com.CaseStudy.ECart.Models.Master_service;
import com.CaseStudy.ECart.Models.pojos.req.ClusterRequest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class Master_serviceRepositoryImpl implements Master_serviceRepository{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Master_service> findAll(ClusterRequest clusterRequest) {
        String query="SELECT * FROM ";
        String tableName="master_service";
        if(clusterRequest.getEnv_type().equalsIgnoreCase("dev")){
            tableName = "dev_master_service";
            query = query + tableName;
        }
        else if(clusterRequest.getEnv_type().equalsIgnoreCase("qa")){
            query = query + tableName;
        }
        List<Master_service> list = entityManager.createNativeQuery(query,Master_service.class).getResultList();
        return list;
    }

    @Override
    @Transactional
    public void savemy(ClusterRequest clusterRequest,Master_service master_service) {
        String query="INSERT INTO ";
        String tableName="master_service(id,service_name,fqdn)";
        if(clusterRequest.getEnv_type().equalsIgnoreCase("dev")){
            tableName = "dev_master_service(id,service_name,fqdn)";
            query = query + tableName + " VALUES";
        }
        else if(clusterRequest.getEnv_type().equalsIgnoreCase("qa")){
            query = query + tableName + " VALUES";
        }
        query = query + "(" + master_service.getId() +"," + "'"  + master_service.getService_name() + "'" + ","  + "'" + master_service.getFqdn() + "'" + ")" ;
        System.out.println(query);
        entityManager.createNativeQuery(query, Master_service.class).executeUpdate();
    }

    @Override
    public List<Master_service> findAll() {
        return null;
    }

    @Override
    public List<Master_service> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Master_service> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Master_service> findAllById(Iterable<String> iterable) {
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
    public void delete(Master_service master_service) {

    }

    @Override
    public void deleteAll(Iterable<? extends Master_service> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Master_service> S save(S s) {
        return null;
    }

    @Override
    public <S extends Master_service> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Master_service> findById(String s) {
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
    public <S extends Master_service> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Master_service> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Master_service getOne(String s) {
        return null;
    }

    @Override
    public <S extends Master_service> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Master_service> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Master_service> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Master_service> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Master_service> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Master_service> boolean exists(Example<S> example) {
        return false;
    }
}
