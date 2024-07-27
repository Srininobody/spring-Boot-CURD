package com.learn.curdOpertion.service;

import com.learn.curdOpertion.entity.AppUser;

import java.util.List;

public interface AppUserService {
    public AppUser saveUser(AppUser app);
    public List<AppUser> getAllUser();

}
