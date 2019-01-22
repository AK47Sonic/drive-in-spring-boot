# Spring Flow

## Spring Container 的创建和刷新

1. AbstractApplicationContext[AnnotationConfigApplicationContext]
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
        6. registerBeanPostProcessors(beanFactory); 注册BeanPostProcessor，来拦截bean创建过程的
            1. 增加BeanPostProcessorChecker
            2. 获取所有的BeanPostProcessor, 都可以通过PriorityOrdered或Ordered来指定顺序
                - BeanPostProcessor
                - DestructionAwareBeanPostProcessor
                - InstantiationAwareBeanPostProcessor
                - SmartInstantiationAwareBeanPostProcessor
                - MergedBeanDefinitionPostProcessor
            3. 先注册PriorityOrdered的BeanPostProcessor：[DefaultListableBeanFactory]beanFactory.addBeanPostProcessor(postProcessor); -> AbstractBeanFactory.beanPostProcessors.add(beanPostProcessor);
            4. 再注册Ordered的BeanPostProcessor：[DefaultListableBeanFactory]beanFactory.addBeanPostProcessor(postProcessor); -> AbstractBeanFactory.beanPostProcessors.add(beanPostProcessor);
            5. 再注册没有实现任何优先级接口的BeanPostProcessor：[DefaultListableBeanFactory]beanFactory.addBeanPostProcessor(postProcessor); -> AbstractBeanFactory.beanPostProcessors.add(beanPostProcessor);
            6. 最后注册MergedBeanDefinitionPostProcessor
            7. 增加监听器检查的BeanPostProcessor： ApplicationListenerDetector:[在bean创建完成后，检查bean是否是ApplicationListener，如果是，则加入到AnnotationConfigApplicationContext.applicationEventMulticaster]
        7. initMessageSource(); 初始化MessageSource, 做国际化功能，消息绑定，消息解析
            1. 获取beanFactory
            2. 看容器beanFactory中是否有MessageSource， 如果有，就获取，保存到AbstractApplicationContext.messageSource。如果没有，就创建。
            3. 把MessageSource注册到容器中DefaultListableBeanFactory.manualSingletonNames：beanFactory.registerSingleton(MESSAGE_SOURCE_BEAN_NAME, this.messageSource);
        8. initApplicationEventMulticaster(); 初始化事件派发器
            1. 获取beanFactory
            2. 从beanFactory中获取applicationEventMulticaster组件
            3. 如果没有配置，就创建一个SimpleApplicationEventMulticaster， 并注入
        9. onRefresh(); 空方法
        10. registerListeners(); 把容器中所有的ApplicationListener注册进来
            1. 获取所有statically specified的ApplicationListener， 加入到AbstractApplicationContext.applicationEventMulticaster中：getApplicationEventMulticaster().addApplicationListener(listener);
            2. 获取容器中所有的ApplicationListener，加入到AbstractApplicationContext.applicationEventMulticaster中： getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
            3. 派发earlyApplicationEvents：getApplicationEventMulticaster().multicastEvent(earlyEvent);
        11. finishBeanFactoryInitialization(beanFactory); 初始化所有剩下的单实例bean
            1. preInstantiateSingletons()； 初始化剩下的单实例bean
                1. 获取容器中的所有bean的beanDefinitionNames，依次进行遍历，创建对象和初始化
                    1. 获取bean的定义信息： RootBeanDefinition
                    2. 如果bean不是抽象的，是单实例的，不是懒加载的
                        1. 是FactoryBean，实现FactoryBean接口的bean： if (isFactoryBean(beanName)) {}
                            1. 
                        2. 不是FactoryBean，则用AbstractBeanFactory.getBean(beanName);创建对象
                            1. AbstractBeanFactory.doGetBean
                                1. 先从[ConcurrentHashMap<String, Object>] DefaultSingletonBeanRegistry.singletonObjects中获取单实例bean，如果能获取到，说明这个bean之前已经被创建过。
                                2. 缓存中获取不到，开始bean的创建对象流程，标记当前bean已经被创建，为了防止多线程同时创建单实例bean：AbstractBeanFactory.markBeanAsCreated(beanName); 
                                3. 获取bean的定义信息，
                                4. 获取当前bean依赖的其他bean， 如果有，用AbstractBeanFactory.getBean(dep);方式先创建依赖的bean
                                5. 启动单实例bean的创建流程: 用AbstractBeanFactory.createBean(beanName, mbd, args);
                            
        12. finishRefresh();   
                    
                    
                    
                    
                
                
                    
                   
                    
                   
                    
                    