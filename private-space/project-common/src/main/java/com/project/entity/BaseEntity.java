package com.project.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Description todo 父类实体
 * @Author lu
 * @Date 2020/4/8
 * @Version 1.0
 */
@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @CreatedDate
    @Column(name = "gmt_create", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date gmtCreate;

    @LastModifiedDate
    @Column(name = "gmt_modified", columnDefinition = "DATETIME COMMENT '最后更新时间'")
    private Date gmtModified;

    @Version
    @Column(name = "version", columnDefinition = "int COMMENT '版本号'")
    private Long version;
}
