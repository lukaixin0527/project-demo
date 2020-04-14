package com.project.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName StudentEntity
 * @Description todo
 * @Author lu
 * @Date 2020/4/8
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "student")
public class StudentEntity extends  BaseEntity{

    private String name;

    private Integer age;
}
