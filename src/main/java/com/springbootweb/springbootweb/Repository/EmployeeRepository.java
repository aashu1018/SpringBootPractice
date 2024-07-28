package com.springbootweb.springbootweb.Repository;

import com.springbootweb.springbootweb.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
