# Reactor 3.x Basic

## Flux
* 0, 1, 또는 여러 요소 생성하는 일반적인 리액티트 스트림.

## Mono
* 최대 하나의 요소 생성. 한개도 안 생성할 수도 있음.
* 버퍼 중복과 값비싼 동기화 작업을 생략하기 때문에 Mono가 보다 더 효율적이다.
* `CompletableFuture<T>`와 의미론적으로 동일하지만
  * `CompletableFuture`는 즉시 처리를 시작하고
  * `Mono`는 구독자가 나타날 때까지 아무것도 하지 않는다.
* 클라이언트에게 작업이 완료됐음을 알리는데 사용 가능. `Mono<Void>`와 `onComplete()`, `onError()` 조합.

## Flux/Mono 상호변환
* `Flux<T>.collectList()` -> `Mono<List<T>>`
* `Mono<T>.flux()` -> `Flux<T>`

## RxJava 2.x
RxJava 2.x 를 리액티브 스트림과 호환하게 하려면, `Flowable`로 변환해야 한다.

* `Observable`
  * null 불허.
  * `Publisher` 구현 안 함
  * 배압 없음
  * 오버헤드 낮음
* `Flowable`
  * `Flux`와 동급.
  * `Publisher` 구현
* `Single`
  * `Publisher` 구현 안 함.
  * `CompletableFuture`와 유사함.
  * 구독이 발생해야 처리 시작
* `Maybe`
  * `Mono`와 동급.
  * `Publisher` 구현 안 함.
  * `toFlowable()` 제공.
* `Completable`
  * `Mono<Void>`와 동급
  * `onError()`, `onComplete()` 신호 발생.
  * `onNext()` 발생 안함.