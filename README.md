# Назначение
Приложение показывает пример каким образом можно слушать очереди из разных JMS брокеров для реализации на SpringBoot.

## Сборка и запуск
В корне проекта выполнить 'gradlew build runApp'.
В результате запустится приложение, в котором 2 листенера слушают 2 очереди из разных брокеров, объявленных в конфиге.
Можно настроить и на один брокер.