package com.sssp.service;

import com.sssp.entities.Employee;
import com.sssp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author lijichen
 * @date 2020/12/3 - 16:13
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Page<Employee> getPage(int pageNo, int pageSize) {
        PageRequest page = PageRequest.of(pageNo - 1, pageSize);
        return employeeRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Employee getByName(String name) {
        System.out.println(name);
        return employeeRepository.getTopByName(name);
    }

    @Transactional
    public void saveOrUpdateEmployee(Employee employee) {
        employee.setCreateTime(new Date());
        employeeRepository.saveAndFlush(employee);
    }

    @Transactional
    public Employee getById(Integer id) {
        return employeeRepository.getOne(id);
    }

    @Transactional
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

}
