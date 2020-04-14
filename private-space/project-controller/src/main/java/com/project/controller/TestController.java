package com.project.controller;

import cn.hutool.json.JSONObject;
import com.project.dto.UserEntity;
import com.project.entity.StudentEntity;
import com.project.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @ClassName TestController
 * @Description todo
 * @Author lu
 * @Date 2020/4/8
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1")
public class TestController {


    private Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/validateData", method = RequestMethod.POST)
    public String validateDataMethod(@RequestParam("name") String name,
                                     @RequestParam("age") String age,
                                     @RequestParam("key") String key,
                                     @RequestParam("sign") String sign
    ) {
        logger.info("name:{},age:{},key:{},sign:{}", name, age, key, sign);
        return "验证成功";

    }

//======================================================================================================================//

    /**
     * @description:todo 测试返回值为List<Object>是否可以封装成功
     * @author: xiaolu
     * @Date: 2020/4/9
     * @return: {
     * "data": [
     * {
     * "id": 1,
     * "account": "123456789",
     * "password": "987654321",
     * "email": "123456789@qq.com"
     * }
     * ],
     * "code": 1000,
     * "msg": "操作成功"
     * }
     */
    @RequestMapping(value = "/return/Object")
    public List<UserEntity> testReturnObject() {

        logger.info("1111111111");

        List<StudentEntity> allStudent = studentService.findAllStudent();
        System.out.println(allStudent);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setAccount("111111111");
        userEntity.setEmail("123456789@qq.com");
        userEntity.setPassword("987654321");
        List<UserEntity> userEntityList = Arrays.asList(userEntity);
        return userEntityList;
    }

    /**
     * @description:todo 测试返回值为String是否可以封装成功
     * @author: xiaolu
     * @Date: 2020/4/9
     * @return: {"data":"用户信息正常","code":1000,"msg":"操作成功"}
     */
    @RequestMapping(value = "/return/String")
    public String testReturnString() {
        return "用户信息正常";
    }

    /**
     * @description:todo 测试返回值为Map是否可以封装成功
     * @author: xiaolu
     * @Date: 2020/4/9
     * @return: {
     * "data": {
     * "userInfo": {
     * "id": 1,
     * "name": "小红",
     * "age": 12
     * }
     * },
     * "code": 1000,
     * "msg": "操作成功"
     * }
     */
    @RequestMapping(value = "/return/Map")
    public Map<String, JSONObject> testReturnMap() {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("id", 1);
        jsonObject.put("name", "小红");
        jsonObject.put("age", 12);
        Map<String, JSONObject> resultMap = new HashMap<>(1);
        resultMap.put("userInfo", jsonObject);
        return resultMap;
    }

//======================================================================================================================//

    /**
     * @description:todo 测试入参校验, 通过 bindingResult印出 错误信息 【校验信息在UserEntity实体类中】
     * @author: xiaolu
     * @Date: 2020/4/9
     * @return:
     */
    @RequestMapping(value = "/test/1", method = RequestMethod.POST)
    public String getUser(@RequestBody @Valid UserEntity userEntity, BindingResult bindingResult) {
        for (ObjectError error : bindingResult.getAllErrors()) {
            return error.getDefaultMessage();
        }
        return "成功!";
    }

    /**
     * @description:todo 测试入参校验，不需要 bindingResult校验，通过 ExceptionControllerAdvice 捕获全局异常
     * @author: xiaolu
     * @Date: 2020/4/9
     * @return:
     */
    @RequestMapping(value = "/test/2", method = RequestMethod.POST)
    public String getUser1(@RequestBody @Valid UserEntity userEntity) {
        return "成功!";
    }


}
