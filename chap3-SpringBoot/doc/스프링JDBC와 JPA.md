### 스프링JDBC와 JPA

JDBC ( Java DataBase Connectivity )

JDBC API 는 관계형 데이터베이스에 저장된 데이터에 접근하는 방법을 정의한다.

JDBC 드라이버는 특정 데이터베이스에 대한 JDBC API 의 구현체이다.

예를 들어 com.mysql.jdbc.Driver 는 MySQL 데이터베이스에 대한 드라이버 클래스 이름이고

org.hsqldb.jdbcDriver 는 순수 자바로 작성된 관계형 데이터베이스인 HSQLDB 에 대한 드라이버 클래스 이름이다.

스프링 JDBC 는 JDBC API 위에서 데이터베이스와 더 쉽게 상호작용할 수 있도록 스프링에서 제공하는 추상화 계층이다.

### JPA ( Java Persistence API )

JPA 는 자바 객체의 영속성을 위한 자바의 표준화된 접근 방식이다.

이 방식은 객체 지향 모델과 관계형 데이터베이스에 저장된 데이터 사이의 간격을 메우기 위해 객체 관계형 매핑(ORM, Object-Relational Mapping) 매커니즘을 사용한다.

하이버네이트( Hibernate )ORM 은 가장 흔히 사용하는 JPA 표준을 구현한 구현체이다.

표준 API 외에도 하이버네이트 ORM 은 고유의 API 도 제공한다.
