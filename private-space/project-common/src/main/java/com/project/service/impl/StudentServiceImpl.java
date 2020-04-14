package com.project.service.impl;

import com.project.dao.StudentRepository;
import com.project.entity.StudentEntity;
import com.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName StudentService
 * @Description todo
 * @Author lu
 * @Date 2020/4/10
 * @Version 1.0
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentEntity> findAllStudent() {
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        return studentEntityList;
    }
}
