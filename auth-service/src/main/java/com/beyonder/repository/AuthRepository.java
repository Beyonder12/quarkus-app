package com.beyonder.repository;

import com.beyonder.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthRepository implements PanacheRepository<User> {
}
