package cn.tedu.sp06.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class RibbonController {
    @Autowired
    private RestTemplate rt;

    // 调用远程的商品服务
    //如果远程调用失败，则转到另一段代码执行，代码要指定执行的方法名
    @HystrixCommand(fallbackMethod = "getItemsFB")//getItemsFB指定跳转到方法
    @GetMapping("/item-service/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId) {
        //调用远程服务
        // {1} 是 RestTemplate 定义的一种占位符格式，后面第三个参数 orderId 会对占位符进行填充
        return rt.getForObject(
                "http://item-service/{1}",
                JsonResult.class,
                orderId);

    }

    @HystrixCommand(fallbackMethod = "getItemsFB")//getItemsFB指定跳转到方法
    @PostMapping("/item-service/decreaseNumber")
    public JsonResult<?> decreaseNumber(@RequestBody List<Item> items) {
        // 调用商品服务，减少商品库存
        return rt.postForObject(
                "http://item-service/decreaseNumber",
                items, //post请求协议体数据
                JsonResult.class);
    }
    @HystrixCommand(fallbackMethod = "getItemsFB")//getItemsFB指定跳转到方法
    @GetMapping("/user-service/{userId}")
    public JsonResult<User> getUser(@PathVariable Integer userId) {
        return rt.getForObject("http://user-service/{1}", JsonResult.class, userId);
    }
    @HystrixCommand(fallbackMethod = "getItemsFB")//getItemsFB指定跳转到方法
    @GetMapping("/user-service/{userId}/score")
    public JsonResult<?> addScore(@PathVariable Integer userId, Integer score) {
        return rt.getForObject("http://user-service/{1}/score?score={2}", JsonResult.class, userId, score);
    }
    @HystrixCommand(fallbackMethod = "getItemsFB")//getItemsFB指定跳转到方法
    @GetMapping("/order-service/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId) {
        return rt.getForObject("http://order-service/{1}", JsonResult.class, orderId);
    }
    @HystrixCommand(fallbackMethod = "getItemsFB")//getItemsFB指定跳转到方法
    @GetMapping("/order-service/")
    public JsonResult<?> addOrder() {
        return rt.getForObject(
                "http://order-service/", JsonResult.class);
    }
   ///////////////////  上面方法执行失败可返回以下方法
   public JsonResult<List<Item>> getItemsFB(@PathVariable String orderId) {
        return JsonResult.err().msg("获取订单列表失败");

   }
    public JsonResult<?> decreaseNumberFB(@RequestBody List<Item> items) {
      return  JsonResult.err().msg("减少库存商品失败");
    }

    public JsonResult<User> getUserFB(@PathVariable Integer userId) {
      return  JsonResult.err().msg("获取用户失败");
    }

    public JsonResult<?> addScoreFB(@PathVariable Integer userId, Integer score) {
      return  JsonResult.err().msg("增加用户积分失败");
    }

    public JsonResult<Order> getOrderFB(@PathVariable String orderId) {
        return JsonResult.err().msg("获取订单失败");
    }
    public JsonResult<?> addOrderFB() {
      return JsonResult.err().msg("保存订单失败");
    }
}









