# Spring-Boot and Reactor

## Reactive 구현체들간의 상호 변환
* ReactiveAdapter : Reactive 구현체들간의 변환
* ReactiveAdapterRegistry : ReactiveAdapter 저장소

## Reactive I/O
* DataBuffer : byte 인스턴스의 버퍼 추상화. 여러 byte 인스턴스 버터들에 대한 구현을 추상화

### DataBufferUtils
* I/O(netowork, resource, file 등) 작업을 리액티브 스트림 형태로 할 수 있게 해준다.