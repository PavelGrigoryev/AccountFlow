# Описание

Приложение для работы со счетами пользователей

***

# Описание использованных технологий

* Java 21
* Maven
* Lombok
* Postgresql
* Spring-boot 3.4.5
* Spring-boot-starter-data-jpa
* Spring-boot-starter-data-redis
* Spring-boot-starter-web
* Spring-boot-starter-validation
* Spring-boot-starter-aop
* Springdoc-openapi-starter-webmvc-ui 2.8.6
* Jsonwebtoken-jjwt-api 0.12.6
* Jsonwebtoken-jjwt-impl 0.12.6
* Jsonwebtoken-jjwt-jackson 0.12.6
* Liquibase
* Spring-boot-starter-test
* Testcontainers-Postgresql

***

# Инструкции по запуску проекта

1. Нужен docker с redis и postgresql последними версиями.
2. Создать дб ```create database account_flow```
3. Настроить под себя [application.yaml](src/main/resources/application.yaml). По сути добавив username и password от
   своей postgresql, остальное можно не менять

```.yaml
spring:
  application:
    name: AccountFlow
    description: "Приложение для работы со счетами пользователей"
    version: 1.0
  datasource:
    url: jdbc:postgresql://localhost:5432/account_flow
    username: pavel // от вашей postgresql
    password: pavel // от вашей postgresql
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
    properties:
      hibernate:
        format_sql: true
    open-in-view: false
jwt:
  secret-key: 73357638792F423F4528482B4D6251655468576D5A7133743677397A24432646 //ключ шифрования
  expiration-time: 900000 //срок жизни jwt 15 минут
redis:
  ttl: 15 //срок жизни кэша в редис 15 минут
balance:
  fixed-rate: 30000 // каждые 30 секунд джоба повторяется
  initial-delay: 30000 // джоба стартанёт через 30 секунд поосле запуска приложения
  increase-multiplier: 1.1 //увеличение баланса на 10%
  max-multiplier: 2.07 // но не более 207%
scheduling:
  enabled: true // включить джобу
```

4. Приложение можно запускать.

***

# Swagger UI

http://localhost:8080/swagger-ui/index.html

***

# Примеры запросов к приложению:

1. [login.http](src/main/resources/http/login.http) для получения jwt
2. [accounts.http](src/main/resources/http/accounts.http) для работы со счетами
3. [emails.http](src/main/resources/http/emails.http) для работы с почтовыми ящиками
4. [phones.http](src/main/resources/http/phones.http) для работы с телефонами
5. [users.http](src/main/resources/http/users.http) поиск пользователей по спеке

## P.S.:

1. Запросы запускаются прям в идейке, если она ultimate.
2. В header USER_ID должен быть jwt для всех POST(кроме аутентификации по email+password, либо по phone+password)
   /PATCH/DELETE запросов.
3. Все GET и POST(только аутентификации по email+password, либо по phone+password) запросы доступны без header USER_ID.
