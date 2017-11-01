package com.gaoxi.init;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.annotation.AuthScan;
import com.gaoxi.annotation.Login;
import com.gaoxi.annotation.Role;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.enumeration.HttpMethodEnum;
import com.gaoxi.facade.redis.RedisUtilsFacade;
import com.gaoxi.facade.user.UserService;
import com.gaoxi.utils.AnnotationUtil;
import com.gaoxi.utils.ClassUtil;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.zookeeper.Op;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static com.gaoxi.utils.ClassUtil.getClasses;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 上午10:04
 * @description 初始化用户信息
 */
@AuthScan("com.gaoxi.controller")
@Component
public class InitAuth implements CommandLineRunner {

    @Reference
    private UserService userService;

    @Reference
    private RedisUtilsFacade redisUtils;

    /** 用户信息列表 */
    private List<UserEntity> userEntityList = Lists.newArrayList();

    /** 接口权限列表 */
    private Map<String,AccessAuthEntity> accessAuthMap = Maps.newHashMap();

    @Override
    public void run(String... strings) throws Exception {
        // 加载用户信息
        loadUserInfo();

        // 加载接口访问权限
        loadAccessAuth();
    }


    /**
     * 加载用户信息
     */
    private void loadUserInfo() {

    }

    /**
     * 加载接口访问权限
     */
    private void loadAccessAuth() throws IOException {
        // 获取待扫描的包
        AuthScan authScan = AnnotationUtil.getAnnotationValueByClass(this.getClass(), AuthScan.class);
        String pkgName = authScan.value();

        // 获取包下所有类
        List<Class<?>> classes = ClassUtil.getClasses(pkgName);
        if (CollectionUtils.isEmpty(classes)) {
            return;
        }

        // 遍历类
        for (Class clazz : classes) {
            Method[] methods = clazz.getMethods();
            if (methods==null || methods.length==0) {
                continue;
            }

            // 遍历函数
            for (Method method : methods) {
                AccessAuthEntity accessAuthEntity = buildAccessAuthEntity(method);
                if (accessAuthEntity!=null) {
                    String key = accessAuthEntity.getHttpMethodEnum().getMsg()+accessAuthEntity.getUrl();
                    accessAuthMap.put(key, accessAuthEntity);
                }
            }
        }

        // 将接口访问权限加入Redis中
        if (!accessAuthMap.isEmpty()) {
            redisUtils.set("accessAuthMap", accessAuthMap);
        }
    }

    /**
     * 构造AccessAuthEntity对象
     * @param method
     * @return
     */
    private AccessAuthEntity buildAccessAuthEntity(Method method) {
        GetMapping getMapping = AnnotationUtil.getAnnotationValueByMethod(method, GetMapping.class);
        PostMapping postMapping = AnnotationUtil.getAnnotationValueByMethod(method, PostMapping.class);
        PutMapping putMapping= AnnotationUtil.getAnnotationValueByMethod(method, PutMapping.class);
        DeleteMapping deleteMapping = AnnotationUtil.getAnnotationValueByMethod(method, DeleteMapping.class);

        AccessAuthEntity accessAuthEntity = null;
        if (getMapping!=null) {
            accessAuthEntity = new AccessAuthEntity();
            accessAuthEntity.setHttpMethodEnum(HttpMethodEnum.GET);
            accessAuthEntity.setUrl(getMapping.value()[0]);
        }
        else if (postMapping!=null) {
            accessAuthEntity = new AccessAuthEntity();
            accessAuthEntity.setHttpMethodEnum(HttpMethodEnum.POST);
            accessAuthEntity.setUrl(postMapping.value()[0]);
        }
        else if (putMapping!=null) {
            accessAuthEntity = new AccessAuthEntity();
            accessAuthEntity.setHttpMethodEnum(HttpMethodEnum.PUT);
            accessAuthEntity.setUrl(putMapping.value()[0]);
        }
        else if (deleteMapping!=null) {
            accessAuthEntity = new AccessAuthEntity();
            accessAuthEntity.setHttpMethodEnum(HttpMethodEnum.DELETE);
            accessAuthEntity.setUrl(deleteMapping.value()[0]);
        }

        if (accessAuthEntity!=null) {
            accessAuthEntity = getLoginAndRole(method, accessAuthEntity);
            accessAuthEntity.setMethodName(method.getName());
        }

        return accessAuthEntity;
    }

    /**
     * 获取指定方法上的@Login的值和@Role的值
     * @param method 目标方法
     * @param accessAuthEntity
     * @return
     */
    private AccessAuthEntity getLoginAndRole(Method method, AccessAuthEntity accessAuthEntity) {
        // 获取@Role的值
        Role role = AnnotationUtil.getAnnotationValueByMethod(method, Role.class);
        if (role!=null && StringUtils.isNotEmpty(role.value())) {
            accessAuthEntity.setRole(role.value());
            accessAuthEntity.setLogin(true);
            return accessAuthEntity;
        }

        // 获取@Login的值
        Login login = AnnotationUtil.getAnnotationValueByMethod(method, Login.class);
        if (login!=null) {
            accessAuthEntity.setLogin(true);
        }

        accessAuthEntity.setLogin(false);
        return accessAuthEntity;
    }

}
