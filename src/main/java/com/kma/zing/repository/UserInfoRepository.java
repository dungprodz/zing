package com.kma.zing.repository;

import com.kma.zing.entity.TblUserInforEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<TblUserInforEntity,Integer> {
    TblUserInforEntity findByUserid(int userId);

    TblUserInforEntity findByUsername(String userName);

    boolean existsByUsername(String userName);
}
