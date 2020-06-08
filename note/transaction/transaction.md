## 分布式事务

- Spring事务机制
    - 本地事务 （单数据源或单资源）
    - 外部（全局）事务 （一个transactionManager管理多资源，2阶段提交）- 实现：JTA - 规范： XA
        - 应用服务器提供，JNDI 比如：JBoss
        - 类库 比如：atomikos

- Spring分布式事务实现
    - XA与最后资源博弈
    - 共享资源
    - 最大努力一次提交
    - 链式事务管理
    