package com.sssp.entities;

import javax.persistence.*;

/**
 * @author lijichen
 * @date 2020/12/3 - 14:51
 */
@Cacheable//该类可以使用二级缓存
@Entity
@Table(name = "sssp_department")
public class Department {
    private Integer id;
    private String name;

    public Department() {
    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
