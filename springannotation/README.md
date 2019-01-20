# Spring Flow

## Spring Container 的创建和刷新

1. AbstractApplicationContext
    - getEnvironment() 获得Environment
    1. refresh();
        1. prepareRefresh(); 刷新前的预处理；
            1. initPropertySources(); 空方法，子类可以继承覆盖实现来自定义属性
            2. getEnvironment().validateRequiredProperties(); 通过StandardEnvironment中PropertySourcesPropertyResolver对RequiredProperties进行校验(environment.setRequiredProperties())
                - Environment.getSystemProperties() 获得系统属性，比如：SPID,VM options, OS name
                - Environment.getSystemEnvironment() 获得系统环境， 比如：JAVA_HOME，HADOOP_HOME
            3. 创建earlyApplicationEvents = new LinkedHashSet<>(); 保存容器中早期的一些事件            
        2. obtainFreshBeanFactory(); 获得BeanFactory
            1. GenericApplicationContext.refreshBeanFactory(); 设置序列化id: beanFactory.setSerializationId(getId());
                - DefaultListableBeanFactory 在GenericApplicationContext初始化构造函数中创建beanFactory = new DefaultListableBeanFactory();
            2. GenericApplicationContext.getBeanFactory(); 获得DefaultListableBeanFactory实例      
        3. prepareBeanFactory(beanFactory); BeanFactory的预准备工作
            - 设置类加载器
            - 设置表达式解析器StandardBeanExpressionResolver
            - 添加属性编辑注册器ResourceEditorRegistrar
            - 添加部分的Bean后置处理器：beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
                - ApplicationContextAwareProcessor 对实现*Aware接口的类进行处理
                    - EnvironmentAware
                    - ResourceLoaderAware
                    - ApplicationContextAware
                    - MessageSourceAware
                    - ApplicationEventPublisherAware
                    - 等
                - ApplicationListenerDetector
                - LoadTimeWeaverAwareProcessor
            - 设置忽略的自动装配的接口：beanFactory.ignoreDependencyInterface(ApplicationContextAware.class);
                - EnvironmentAware
                - ApplicationEventPublisherAware
                - ApplicationContextAware
                - 等
            - 注册可解析的依赖，保存在DefaultListableBeanFactory.resolvableDependencies: beanFactory.registerResolvableDependency(ApplicationContext.class, this); 可以在任何组件中自动注入以下对象[@Autowired]
                - BeanFactory.class
                - ResourceLoader.class
                - ApplicationEventPublisher.class
                - ApplicationContext.class
            - 添加编译时的AspectJ支持: loadTimeWeaver -> LoadTimeWeaverAwareProcessor
                - beanFactory.addBeanPostProcessor(new LoadTimeWeaverAwareProcessor(beanFactory));
                - beanFactory.setTempClassLoader(new ContextTypeMatchClassLoader(beanFactory.getBeanClassLoader()));
            - 给beanFactory注册一些能用的组件
                - environment -> StandardEnvironment
                    - 保存在Map<String, Object> DefaultSingletonBeanRegistry.singletonObjects
                    - 保存在Set<String> DefaultSingletonBeanRegistry.registeredSingletons
                    - 保存在Set<String> manualSingletonNames
                - systemProperties -> System.getProperties();
                - systemEnvironment -> System.getenv();
        4. postProcessBeanFactory(beanFactory); 空方法，BeanFactory的预准备工作完成后，进行的后置处理工作
        5. invokeBeanFactoryPostProcessors(beanFactory); 执行BeanFactoryPostProcessor
            - BeanDefinitionRegistryPostProcessor
            - BeanFactoryPostProcessor
            1. invokeBeanFactoryPostProcessors 执行BeanFactoryPostProcessor的方法
                1. 获取AbstractApplicationContext.beanFactoryPostProcessors
                2. 遍历BeanFactoryPostProcessor
                    1. 如果是BeanDefinitionRegistry， 把SharedMetadataReaderFactoryBean加入DefaultListableBeanFactory.beanDefinitionMap和DefaultListableBeanFactory.beanDefinitionNames
                    2. 如果不是， regularPostProcessors.add(postProcessor);
                3. 获取所有的BeanDefinitionRegistryPostProcessor
                    1. 对所有实现了PriorityOrdered接口的BeanDefinitionRegistryPostProcessor排序
                    2. 执行所有实现了PriorityOrdered接口的BeanDefinitionRegistryPostProcessor：postProcessor.postProcessBeanDefinitionRegistry(registry);
                    3. 对所有实现了Ordered接口的BeanDefinitionRegistryPostProcessor排序
                    4. 执行所有实现了Ordered接口的BeanDefinitionRegistryPostProcessor：postProcessor.postProcessBeanDefinitionRegistry(registry);
                    5. 最后执行所有没有实现任何优先级接口的BeanDefinitionRegistryPostProcessor: postProcessor.postProcessBeanDefinitionRegistry(registry);
                4. 获取所有的BeanFactoryPostProcessor
                    1. 对所有实现了PriorityOrdered接口的BeanFactoryPostProcessor排序
                    2. 执行所有实现了PriorityOrdered接口的BeanFactoryPostProcessor：postProcessor.postProcessBeanFactory(beanFactory);
                    3. 对所有实现了Ordered接口的BeanFactoryPostProcessor排序
                    4. 执行所有实现了Ordered接口的BeanFactoryPostProcessor：postProcessor.postProcessBeanFactory(beanFactory);
                    5. 最后执行所有没有实现任何优先级接口的BeanFactoryPostProcessor: postProcessor.postProcessBeanFactory(beanFactory);
                    
                    
                    
                    
                
                
                    
                   
                    
                   
                    
                    