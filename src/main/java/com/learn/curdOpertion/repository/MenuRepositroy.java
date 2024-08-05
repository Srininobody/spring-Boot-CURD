package com.learn.curdOpertion.repository;

import com.learn.curdOpertion.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepositroy extends JpaRepository<Menu,Long> {
    @Query(value = "SELECT * FROM menu WHERE status = 'L'", nativeQuery = true)
    List<Menu> findAllActiveMenus();
}
