package com.gaoxi.order.init;

import com.gaoxi.order.annotation.InjectComponents;
import com.gaoxi.order.annotation.PackageScan;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.utils.AnnotationUtil;
import com.gaoxi.utils.ClassUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/7 下午4:30
 *
 * @description 初始化组件列表
 */
@PackageScan("com.gaoxi.order.processor")
@Component
public class InitComponentList implements CommandLineRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void run(String... strings) throws Exception {
        // 初始化组件列表
        initComponents();
    }

    /**
     * 初始化组件列表
     */
    private void initComponents() throws InstantiationException, IllegalAccessException {
        // 获取需要扫描的包名
        String pkgName = getPackage();

        // 获取包下所有类
        List<Class<?>> classes = ClassUtil.getClasses(pkgName);
        if (CollectionUtils.isEmpty(classes)) {
            return;
        }

        // 遍历类
        for (Class clazz : classes) {
            Field[] fields = clazz.getDeclaredFields();
            if (fields==null || fields.length==0) {
                continue;
            }

            // 遍历成员变量
            for (Field field : fields) {
                // 获取Field上的@InjectComponents注解
                InjectComponents injectComponents = AnnotationUtil.getAnnotationValueByField(field, InjectComponents.class);
                if (injectComponents == null) {
                    continue;
                }

                // 创建componentList对象
                List<BaseComponent> componentList = initComponentList(injectComponents);

                // 赋值
                setValueForField(clazz, field, componentList);
            }
        }

    }

    /**
     * 将ComponentList赋给ComponentList
     * @param clazz ComponentList所在类
     * @param field ComponentList成员变量
     * @param componentList 组件对象列表
     */
    private void setValueForField(Class clazz, Field field, List<BaseComponent> componentList) throws IllegalAccessException {
        // 获取Processor对象
        Object processor = applicationContext.getBean(clazz);

        // 将field设为public
        field.setAccessible(true);

        // 赋值
        field.set(processor, componentList);

        System.out.println(processor);
        System.out.println(componentList);
        System.out.println("---------------");
    }

    /**
     * 初始化componentList
     * @param injectComponents componentList上的注解
     */
    private List<BaseComponent> initComponentList(InjectComponents injectComponents) throws IllegalAccessException, InstantiationException {

        List<BaseComponent> componentList = Lists.newArrayList();

        // 获取Components
        Class[] componentClassList = injectComponents.value();
        if (componentClassList == null || componentClassList.length <= 0) {
            return componentList;
        }

        // 遍历components
        for (Class<BaseComponent> componentClass : componentClassList) {
            // 获取IOC容器中的Component对象
            BaseComponent componentInstance = applicationContext.getBean(componentClass);
            // 加入容器
            componentList.add(componentInstance);
        }

        return componentList;
    }

    /**
     * 获取需要扫描的包名
     */
    private String getPackage() {
        PackageScan packageScan = AnnotationUtil.getAnnotationValueByClass(this.getClass(), PackageScan.class);
        return packageScan.value();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
