package com.wangj.web.italker.push.service;


import com.wangj.web.italker.push.bean.db.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/account")
public class AccountService {


    @GET
    @Path("/login")
    public String get() {
        return "You get the login.";
    }


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User post() {
        User user = new User();
        user.setName("帅哥");
        user.setSex(2);
        return user;
    }

}
