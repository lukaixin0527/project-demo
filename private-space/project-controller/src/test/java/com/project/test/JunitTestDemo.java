package com.project.test;

import com.project.entity.StudentEntity;
import com.project.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName JunitTestDemo
 * @Description todo 单元测试
 * @Author lu
 * @Date 2020/4/8
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JunitTestDemo {

    @Autowired
    private StudentService studentService;

    @Test
    public void junitTestMethod() {
        System.out.println("单元测试成功");
        System.out.println("单元测试成功");
        System.out.println("单元测试成功");
    }

    @Test
    public void junitTestJpaFind() {
        List<StudentEntity> studentEntityList = studentService.findAllStudent();
        System.out.println(studentEntityList);

    }


}
