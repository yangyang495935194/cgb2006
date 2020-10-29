package cn.tedu.sp04.feign;

import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service",fallback = UserFeignClientFB.class)
public interface UserFeignClient {
    //1.获取用户
    @GetMapping("/{UserId}")
    public JsonResult<User> getUserId(@PathVariable Integer UserId);

    //2.增加用户积分
    @GetMapping("/{UserId}/score")
    public  JsonResult addScore(@PathVariable Integer UserId,@RequestBody Integer score);

}
