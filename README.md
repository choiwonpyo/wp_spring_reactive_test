- 명령형 프로그래밍 => 일련의 작업 단계를 기술
- 리액티브 프로그래밍 => 데이터가 전달될 파이프라인을 구성

### 리액티브 요약
- 리액티브 프로그래밍에서는 데이터가 흘러가는 파이프라인을 생성한다.
- 리액티브 스트림은 Publisher, Subscriber, Subscription, Transformer의 네 가지 타입을 정의한다.
- 프로젝트 리액터는 리액티브 스트림을 구현하며, 수많은 오퍼레이션을 제공하는 Flux와 Mono의 두 가지 타입으로 스트림을 정의한다.
- 스프링 5는 리액터를 사용해서 리액티브 컨트롤러, 리퍼지터리, Rest 클라이언트를 생성하고 다른 리액티브 프레임워크를 지원한다.
- 리액티브 스트림은 차단되지 않는 백프레셔를 갖느 ㄴ비동기 스트림 처리의 표준을 제공하는 것이 목표이다.

### 함수형 프로그래밍 모델

- RequestPredicate: 처리될 요청의 종류를 선언한다.
- RouterFunction: 일치하는 요청이 어떻게 핸들러에게 전달되어야 하는지를 선언한다.
- ServerRequest: HTTP 요청을 나타내며 헤더와 몸체 정보를 사용할 수 있다.
- ServerResponse: HTTP 응답을 나타내며, 헤더와 몸체 정보를 포함한다.
