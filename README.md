# attemper-java-sdk
任务调度中心[attemper](https://github.com/attemper/attemper)的java sdk
- 仅适用于*Spring Boot/MVC*项目

## 与**执行器**交互
满足有* Http任务* 的需求
- `attemper-java-sdk-micro-executor`: 适用`spring boot`
- `attemper-java-sdk-rest-executor`: 适用`spring mvc`

``` properties
# 若有异步Http任务，需要配置  
dispatch.executor.service-name=#执行器的服务名
```
 
## 与**调度中心后端**交互
***目前只有延迟任务才需要用之***  
  - `attemper-java-sdk-micro-web`: 适用`spring boot`
  - `attemper-java-sdk-rest-web`: 适用`spring mvc`

``` properties
dispatch.user-name=# 租户的用户名
dispatch.password=# 租户的加密后密码
dispatch.web.service-name=# 调度中心后端的服务名
```
