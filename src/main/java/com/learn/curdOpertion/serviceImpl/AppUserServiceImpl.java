package com.learn.curdOpertion.serviceImpl;

import com.learn.curdOpertion.entity.AppUser;
import com.learn.curdOpertion.repository.AppUserRespository;
import com.learn.curdOpertion.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRespository respository;
    @Override
    public AppUser saveUser(AppUser user) {
        return respository.save(user);
    }

    @Override
    public List<AppUser> getAllUser() {
        return respository.findAll();
    }
}
