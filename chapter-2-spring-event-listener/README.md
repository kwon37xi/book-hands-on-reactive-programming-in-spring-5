# Spring Event Listener 결과
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