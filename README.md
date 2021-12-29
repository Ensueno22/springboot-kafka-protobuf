### Protobuf 사용 Kafka 구성

> Protobuf 설치
> ``` shell
> brew install protobuf
> ```
> protoc java 생성 명령어
> ```shell
> # java 파일 생성 명령어
> protoc --java_out=./build ./ResSendMessage.proto
> protoc --java_out=./build ./ReqSendMessage.proto
> 
> # protoc3 에서 default 값 설정은 사라짐. 빌드시 이전 파일 에러발생
> ```
>
> 참조 사이트
>
> https://jeong-pro.tistory.com/190





