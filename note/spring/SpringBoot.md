### Spring Boot
1. Java 和 C相比，性能缺点
    - 类型内存消耗较大（java int 32, c int 16)
    - 垃圾回收（java被动回收，c主动回收）
    - Java full gc 有停顿
    - Java只有线程没有协程

2. 标准化优化技术
    - 资源变化
        1. 响应头(response headers)：Last-Modified: Wed, 03 Sep 2014 10:00:27 GMT
        2. 请求头(request headers)：If-Modified-Since: Wed, 03 Sep 2014 10:00:27 GMT
    - 资源缓存
        1. 响应头：Etag: "1ec5-502264e2ae4c0"
        2. 请求头：If-None-Match: "1ec5-502264e2ae4c0"