package cn.tedu.sp08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableDiscoveryClient
@EnableHystrixDashboard //启动监控仪表盘
@SpringBootApplication
public class Sp08RibbonApplication {

	public static void main(String[] args) {

		SpringApplication.run(Sp08RibbonApplication.class, args);
	}



}
