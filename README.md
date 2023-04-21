# jwp-racingcar

## 기능 구현
- [X] 웹 요청/응답 구현하기
  - [X] 서비스 계층 생성
  - [X] 웹 컨트롤러 생성
    - [X] Post 요청 처리
- [X] DB 구현
  - [x] 플레이 결과 저장
    - [X] 플레이 횟수
    - [X] 플레이어 별 최종 이동 거리
    - [X] 우승자
    - [X] 플레이한 날짜/시간
  - [x] 플레이 결과 조회
    - [x] 우승자
    - [x] 참가자
  
- [x] 예외처리
  - [x] console로 처리되고 있던 예외처리 메세지를 api 응답으로 보내기
  - [x] spring validaton을 이용해서 이름과 시도횟수의 blank 처리하기


## 리팩토링 목록
- [x] 중복된 기능을 하는 코드 제거하기
- [x] count의 예외처리 추가하기
  - [x] 음수에 대한 예외처리
- [x] 테스트 코드를 보다 구체적으로 구성하기
- [x] service 계층에서 도메인 분리하기 
- [x] dao의 단위를 entity하나당 하나로 매치되도록 분리하기
- [x] console application의 출력 방식을 web application과 동일하게 하기
- [x] web과 console 모두 같은 service를 이용하는 방식으로 수정
- [x] query문을 한번 호출하여 플레이어들을 한번에 저장하게 수정
- [x] 테이블 구조 수정 (player 테이블에 isWinner 추가 및 game 테이블에 winner 제거)