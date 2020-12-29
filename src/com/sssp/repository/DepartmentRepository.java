package com.sssp.repository;

import com.sssp.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    //该方法使用二级缓存
    @QueryHints({@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true")})
    @Query("SELECT d FROM Department d")
    public List<Department> getALL();
}
