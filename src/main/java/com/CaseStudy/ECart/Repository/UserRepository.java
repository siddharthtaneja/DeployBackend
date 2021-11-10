package com.CaseStudy.ECart.Repository;

import com.CaseStudy.ECart.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
    Users getByEmail(String name);
}
