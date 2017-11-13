package com.gaoxi.order.init;

import com.gaoxi.enumeration.order.ProcessReqEnum;
import com.gaoxi.order.annotation.InjectProcessors;
import com.gaoxi.order.annotation.PackageScan;
import com.gaoxi.order.engine.ProcessEngine;
import com.gaoxi.order.processor.Processor;
import com.gaoxi.utils.AnnotationUtil;
import com.gaoxi.utils.ClassUtil;
import com.gaoxi.utils.EnumUtil;
import com.google.common.collect.Maps;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/9 下午2:46
 *
 * @description 解析@InjectProcessors，向ProcessEngine注入所有的Processor
 */
@Component
@PackageScan("com.gaoxi.order.engine.ProcessEngine")
public class InitProcessorMap implements CommandLineRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void run(String... strings) throws Exception {
        initProcessorMap();
    }

    /**
     * 初始化ProcessorEngine的processorMap
     */
    private void initProcessorMap() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        // 获取ProcessEngine所在的路径
        String pkg = getPackage();

        // 获取ProcessEngine的Class对象
        Class<ProcessEngine> processEngineClazz = (Class<ProcessEngine>) Class.forName(pkg);

        // 获取processorMapField
        Field processorMapField = processEngineClazz.getDeclaredField("processorMap");

        // 获取@InjectProcessors
        InjectProcessors injectProcessors = processorMapField.getAnnotation(InjectProcessors.class);

        // 创建processorMap对象
        Map<ProcessReqEnum, Processor> processorMap = createProcessorMap(injectProcessors);

        // 赋值
        setValueForProcessorMap(processorMap, processorMapField);

    }

    /**
     * 给ProcessorEngine的processorMap对象赋值
     * @param processorMap processorMap对象
     * @param processorMapField processorMap的Field对象
     */
    private void setValueForProcessorMap(Map<ProcessReqEnum, Processor> processorMap, Field processorMapField) throws IllegalAccessException {
        // 获取ProcessEngine对象
        ProcessEngine processEngine = applicationContext.getBean(ProcessEngine.class);

        // 将processorMapField设为public
        processorMapField.setAccessible(true);

        // 赋值
        processorMapField.set(processEngine, processorMap);
    }

    /**
     * 创建ProcessorMap
     * @param injectProcessors injectProcessors注解对象
     * @return ProcessorMap
     */
    private Map<ProcessReqEnum, Processor> createProcessorMap(InjectProcessors injectProcessors) {

        // 初始化容器
        Map<ProcessReqEnum, Processor> processorMap = Maps.newHashMap();

        // 获取注解中的值
        Class[] processorClazzList = injectProcessors.value();
        if (processorClazzList==null || processorClazzList.length<=0) {
            return processorMap;
        }

        // 遍历
        for (Class<Processor> processorClazz : processorClazzList) {
            // 获取Processor
            Processor processor = applicationContext.getBean(processorClazz);
            // 获取枚举
            ProcessReqEnum processReqEnum = EnumUtil.msgOf(ProcessReqEnum.class, processorClazz.getSimpleName());
            // 加入map
            processorMap.put(processReqEnum, processor);

            System.out.println(processor);
            System.out.println("------------");
        }

        System.out.println(processorMap);
        return processorMap;
    }

    /**
     * 获取需要扫描的包名
     */
    private String getPackage() {
        PackageScan packageScan = AnnotationUtil.getAnnotationValueByClass(this.getClass(), PackageScan.class);
        return packageScan.value();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
