# Lab3Android
 Eregin 20Pi-2
 
 Рад представить вам мою лабораторную работу номер три.
 Она реализована на основе некоторой абстрактной мморпг. В игре присутствует на текущий момент всего три класса: Воин, Маг и лучник.
 Работает она так:
 * На основном экране отображаются все созданные игроки и три кнопки: Поиск по заданным пользователем параметрам, Вход на арену и добавление нового игрока
 * Добавление игрока(кнопка): там всё достаточно понятно, требуется ввести никнейм и класс, и пересонаж автоматически добавиться с первым уровнем
 * Вход на арену(кнопка): нажав на эту кнопку, пользователь попадёт на вход арены, где будут отображаться все сыгранные бои, а внизу слева будет на похожая кнопка(Начать битву)
 * Начать битву(кнопка): В новом окне пользователь сможет ввести никнеймы двух героев, которые сразятся в битве за честь и славу, при некорректном вводе пользователя откинет назад, при правильном вводе он сможет увидеть битву
 * Битва(слой): на данном экране будут представлены портереты двух персонажей, их максимальное количество здоровья и лог битвы, нажав кнопку "Finish" пользователь сможет сохранить этот бой в таблицу(победивший персонаж получает +1 уровень)
 * Поиск по заданным пользователем параметрам(кнопка): нажав на эту кнопку, пользователь попадёт на новый экран, где сможет вводить нужные ему параметры поиска(резуллтат поиска будет отображаться на новом экране)
 
 По условиям лабораторной работы:
- должно быть не менее, чем 3 таблицы(Список игроков, описание классов, результаты боёв)
- хотя бы один select с inner join или union(Для корректного отображения результатов боёв классы и уровни персонажей получаются как раз с помощью такого запроса)
- полноценный интерфейс(Выполнено)
- должны быть функции create, update, delete и тд(Полностью реализовано для всех таблиц, но пользователь может взаимодействовать только с таблицей игроков(полностью) и результаты боёв(только добавление))
- выборки (пользователь задает параметры выборки через интерфейс)(Выполнено)
- использование room(Выполнено)

Слабые места:
* Код написан достаточно грязно, так как я не рассчитал время и собственные силы, и попытался прыгнуть выше головы. Результат есть, но ценой качества кода
* Баланс в игре нарушен: я ошибся с численными бонусами классов и в итоге получилось, что маг сильнее всех, лучник сильнее война, а воин слабее их всех
* Информационные сводки в сложных местах могут быть недостаточно полным, чтобы объяснить пользователю тонкости работы приложения
