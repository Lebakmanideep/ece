package org.example.ecommerceapi.DAO;

import org.example.ecommerceapi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
