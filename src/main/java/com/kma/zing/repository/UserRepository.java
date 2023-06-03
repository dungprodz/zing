package com.kma.zing.repository;

import com.kma.zing.entity.TblUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<TblUsersEntity, Integer> {
    TblUsersEntity findAllByUsernameAndStatus(String userName, String status);

    TblUsersEntity findByUsername(String userName);
}
