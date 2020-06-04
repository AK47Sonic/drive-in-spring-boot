## 分布式事务

- Spring事务机制
    - 本地事务 （单数据源或单资源）
    - 外部（全局）事务 （一个transactionManager管理多资源，2阶段提交）- 实现：JTA - 规范： XA
        - 应用服务器提供，JNDI 比如：JBoss
        - 类库 比如：atomikos