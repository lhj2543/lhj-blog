package com.lhj.system.support;

import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringContextSupport implements ApplicationContextAware
{
    private static ApplicationContext _applicationContext;
    private static AutowireCapableBeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)throws BeansException{
        _applicationContext = applicationContext;
        beanFactory = applicationContext.getAutowireCapableBeanFactory();
    }

    public static <T> T getBean(String name)
            throws BeansException
    {
        return (T) _applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz)
            throws BeansException
    {
        return _applicationContext.getBean(clazz);
    }

    public static <T> Map<String, T> getBeans(Class<T> clazz) {
        return _applicationContext.getBeansOfType(clazz);
    }

    public static <T> T getBean(String name, Class requiredType)throws BeansException{
        return (T) _applicationContext.getBean(name, requiredType);
    }

    public static boolean containsBean(String name)
    {
        return _applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException
    {
        return _applicationContext.isSingleton(name);
    }

    public static Class getType(String name)
            throws NoSuchBeanDefinitionException
    {
        return _applicationContext.getType(name);
    }

    public static String[] getAliases(String name)
            throws NoSuchBeanDefinitionException
    {
        return _applicationContext.getAliases(name);
    }

    public static void autowireBean(Object bean)
    {
        beanFactory.autowireBean(bean);
    }
}