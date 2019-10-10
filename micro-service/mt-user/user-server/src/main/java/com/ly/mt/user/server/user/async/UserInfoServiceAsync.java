package com.ly.mt.user.server.user.async;

import com.ly.mt.core.common.entity.shop.ShopInfo;
import com.ly.mt.core.common.entity.user.UserInfoVo;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.user.server.user.mapper.UserInfoServiceMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.Optional;

import static com.ly.mt.core.common.constant.RedisEnum.ENTITY_USER_LOGIN_NAME_REDIS;
import static com.ly.mt.core.common.constant.RedisEnum.ENTITY_USER_MOBILE_REDIS;

/**
 * @Description 用户信息操作异步任务
 * @Author taoye
 */
@Component
public class UserInfoServiceAsync {
    @Resource
    UserInfoServiceMapper mapper;
    @Resource
    RedisServer redisServer;

    /**
     * @Description 缓存用户信息
     * @Author taoye
     */
    @Async
    public void refreshRedis(UserInfoVo user) {
        // 缓存新数据
        user = mapper.getUserById(user);
        if (null == user) {
            return;
        }
        redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, user.getMobile(), user);
        redisServer.setEntity(ENTITY_USER_LOGIN_NAME_REDIS, user.getLoginName(), user);
    }



    @Async
    public void RefreshShopRedis(String shopId){
        //刷新b端缓存
        ShopInfo info = new ShopInfo();
        info.setId(shopId);
        Optional<ShopInfo> shopInfoOptional = Optional.
                ofNullable(mapper.getShopUser(info));
        if(shopInfoOptional.isPresent()){
            redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, shopInfoOptional.get().getMobile(), shopInfoOptional.get());
        }

    }
}