package com.learn.curdOpertion.repository;

import com.learn.curdOpertion.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRespository extends JpaRepository<AppUser,Long> {
}
