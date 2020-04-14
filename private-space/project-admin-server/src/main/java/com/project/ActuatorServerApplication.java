package com.project;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName ActuatorServerApplication
 * @Description todo 监控检查server应用启动入口
 * @Author lu
 * @Date 2020/4/10
 * @Version 1.0
 */
@SpringBootApplication
@EnableAdminServer
public class ActuatorServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActuatorServerApplication.class, args);
        System.out.println("                  _oo0oo_\n" +
                "                 o8888888o\n" +
                "                 88\" . \"88\n" +
                "                 (| -_- |)\n" +
                "                 0\\  =  /0\n" +
                "               ___/`---'\\___\n" +
                "             .' \\\\|     |// '.\n" +
                "            / \\\\|||  :  |||// \\\n" +
                "           / _||||| -:- |||||- \\\n" +
                "          |   | \\\\\\  -  /// |   |\n" +
                "          | \\_|  ''\\---/''  |_/ |\n" +
                "          \\  .-\\__  '-'  ___/-. /\n" +
                "        ___'. .'  /--.--\\  `. .'___\n" +
                "     .\"\" '<  `.___\\_<|>_/___.' >' \"\".\n" +
                "    | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
                "    \\  \\ `_.   \\_ __\\ /__ _/   .-` /  /\n" +
                "=====`-.____`.___ \\_____/___.-`___.-'=====\n" +
                "                  `=---='\n" +
                "\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "\n" +
                "       springboot-server   监控服务启动成功！   ");
    }
}
