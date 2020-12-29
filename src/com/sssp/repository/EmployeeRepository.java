package com.sssp.repository;

import com.sssp.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author lijichen
 * @date 2020/12/3 - 16:07
 */
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query("SELECT e FROM Employee e")
    List<Employee> getAllEmployee();

    @Override
    Page<Employee> findAll(Pageable pageable);

    Employee getTopByName(String name);
}
