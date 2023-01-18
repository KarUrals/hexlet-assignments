# Конечные автоматы

Конечный автомат – это абстрактная машина, которая в один момент времени может находиться только в одном из конечного числа состояний. Конечный автомат может переходить из одного состояния в другое, этот процесс называется переход.

В этом задании вам нужно будет самим создать конечный автомат для поста в блоге.

## Ссылки

* [Урок по конечным автоматам из курса "Автоматное программирование"](https://ru.hexlet.io/courses/js-abp/lessons/fsm/theory_unit)
* [Статья "Как спроектировать правильный конечный автомат"](https://ru.hexlet.io/blog/posts/kak-sproektirovat-pravilnyy-konechnyy-avtomat-na-rest)

## src/main/java/exercise/model/PostState.java

## Задачи

* Изучите код в файле *PostState.java*. В перечислении `PostState` содержатся состояния, в котором может находиться пост.

## src/main/java/exercise/model/Post.java

Cоздайте конечный автомат для поста, опираясь на описание его работы. У поста может быть три состояния - создан (CREATED), опубликован (PUBLISHED) и заархивирован (ARCHIVED):

* CREATED - начальное состояние, в котором создаётся новый пост. Пост находится на модерации и после проверки может быть опубликован или отправлен в архив.
* PUBLISHED - пост опубликован. Из этого состояния пост может быть только отправлен в архив
* ARCHIVED - пост находится в архиве

Есть два события:

* publish – публикация поста. Выполняется при помощи PATCH запроса на адрес */posts/1/publish*
* archive – архивация поста. Выполняется при помощи PATCH запроса на адрес */posts/1/archive*

## Задачи

* Создайте метод `publish()`, который публикует статью. Метод переводит пост в состояние `PUBLISHED`. Переход в состояние `PUBLISHED` возможен только из состояния `CREATED`. Если переход из текущего состояния в целевое возможен, метод устанавливает новое состояние и возвращает `true`, как успешность выполнения операции. Если переход не возможен, метод возвращает `false`.

* Создайте метод `archive()`, который отправляет статью в архив. Метод переводит пост в состояние `ARCHIVED`. Переход в состояние `ARCHIVED` возможен из состояний `CREATED` и `PUBLISHED`. Если переход из текущего состояния в целевое возможен, метод устанавливает новое состояние и возвращает `true`, как успешность выполнения операции. Если переход не возможен, метод возвращает `false`.

## src/main/java/exercise/controller/PostController.java

## Задачи

* Изучите метод контроллера `publish()`, который выполняет публикацию поста.

* Допишите метод `archive()`, который переводит статью в архив

* Изучите метод `getPosts()`, который возвращает список всех постов. Обратите внимание, что он возвращает только опубликованные посты.

## Подсказки

* Для наглядности нарисуйте на бумаге диаграмму состояний поста