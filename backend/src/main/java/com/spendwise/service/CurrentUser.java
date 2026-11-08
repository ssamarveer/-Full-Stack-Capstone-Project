package com.spendwise.service;
import com.spendwise.model.AppUser; import com.spendwise.repo.UserRepository; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.stereotype.Component;
@Component public class CurrentUser { private final UserRepository repo; public CurrentUser(UserRepository r){repo=r;} public AppUser get(){String email=SecurityContextHolder.getContext().getAuthentication().getName();return repo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));} }
