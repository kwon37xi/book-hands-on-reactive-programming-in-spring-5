# Spring Event Listener 결과
* http://localhost:8080 호출
* 4개의 창에서 동시에 요청할 때,

```
[nio-8080-exec-4] r.c.s.TemperatureController              : Requeted
[nio-8080-exec-1] r.c.s.TemperatureController              : Requeted
[nio-8080-exec-2] r.c.s.TemperatureController              : Requeted
[nio-8080-exec-3] r.c.s.TemperatureController              : Requeted
[lTaskExecutor-1] r.c.s.TemperatureController              : send temperature : 9.290080159276759
[lTaskExecutor-1] r.c.s.TemperatureController              : send temperature : 9.290080159276759
[lTaskExecutor-1] r.c.s.TemperatureController              : send temperature : 9.290080159276759
[lTaskExecutor-1] r.c.s.TemperatureController              : send temperature : 9.290080159276759
[lTaskExecutor-2] r.c.s.TemperatureController              : send temperature : 8.23406147600597
[lTaskExecutor-2] r.c.s.TemperatureController              : send temperature : 8.23406147600597
[lTaskExecutor-2] r.c.s.TemperatureController              : send temperature : 8.23406147600597
[lTaskExecutor-2] r.c.s.TemperatureController              : send temperature : 8.23406147600597
[lTaskExecutor-1] r.c.s.TemperatureController              : send temperature : 4.961169219398466
[lTaskExecutor-1] r.c.s.TemperatureController              : send temperature : 4.961169219398466
[lTaskExecutor-1] r.c.s.TemperatureController              : send temperature : 4.961169219398466
[lTaskExecutor-1] r.c.s.TemperatureController              : send temperature : 4.961169219398466
[lTaskExecutor-2] r.c.s.TemperatureController              : send temperature : -11.667562134115464
[lTaskExecutor-2] r.c.s.TemperatureController              : send temperature : -11.667562134115464
[lTaskExecutor-2] r.c.s.TemperatureController              : send temperature : -11.667562134115464
[lTaskExecutor-2] r.c.s.TemperatureController              : send temperature : -11.667562134115464

```

## AsyncRequestTimeoutException 방지
Spring MVC 에서 [AsyncRequestTimeoutException](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/context/request/async/AsyncRequestTimeoutException.html)이
발생한다.

아래 설정으로 timeout 무제한으로 변경할 수 있다.
```
spring:
  mvc:
    async:
      request-timeout: -1
```