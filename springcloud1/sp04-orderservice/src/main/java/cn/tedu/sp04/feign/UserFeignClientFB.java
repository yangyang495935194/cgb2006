package cn.tedu.sp04.feign;

import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFB implements UserFeignClient {


    @Override
    public JsonResult<User> getUserId(Integer userId) {
        //模拟缓存数据
        if (Math.random()<0.5){
            return JsonResult.ok(new User(userId,"缓存name"+userId, "缓存pwd"+userId));

        }

        return JsonResult.err().msg("获取用户失败");
    }

    @Override
    public JsonResult addScore(Integer UserId, Integer score) {
        return JsonResult.err().msg("增加用户积分失败");
    }
}
