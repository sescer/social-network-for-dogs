## DOGS SOCIAL NETWORK MICROSERVICE

###Перед запуском:
- Запустите docker container с PostgreSQL
- Отредактируйте в файле src/main/resources/application.yml url, username и password для того, чтобы приложение присоединилось к базе данных при запуске


### Сборка приложения
>mvn clean install

Во время сборки для тестов также необходим контейнер с POSTGRES.
В дальнейшем, когда перейдем на тест контейнеры, необходимость в запущенном рядом контейнере отпадет.