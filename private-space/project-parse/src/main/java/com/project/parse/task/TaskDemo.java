package com.project.parse.task;

import com.project.entity.StudentEntity;
import com.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName TaskDemo
 * @Description todo
 * @Author lu
 * @Date 2020/4/10
 * @Version 1.0
 */
@Component
public class TaskDemo {


    @Autowired
    private StudentService studentService;

    @Scheduled(cron = "*/6 * * * * ?")
    private void process() {
        List<StudentEntity> allStudent = studentService.findAllStudent();
        System.out.println(allStudent);
        System.out.println("定时任务：启动成功！");
    }
}
