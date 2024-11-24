# Процедура запуска автотестов.
## 1. Для работы SUT необходима СУБД. Так как все настройки уже внесены в этот репозитории, необходимо сказать репо командой «/git clone» (ссылка с https).
## 2. После этого необходимо открыть Docker на локальном компьютере, открыть проект в IDEA и в консоли ввести команду «docker-compose up». 
## 3. После загрузки иммеджа создатся контейнер MySQL с нужными для нас параметрами.
## 4. Далее в новой вкладке консоли нужно ввести команду «java -jar ./artifacts/aqa-shop.jar».
## 5. После того как SUT будет поднят, можно перейти в класс PaymentGateTest и произвести запуск теста. 
## 6. Сгенерировать отчёт по итогам тестирования с помощью Allure, который автоматически откроется в браузере с помощью команды в терминале ./gradlew allureServe.

# Ссылки на тестовую документацию:

Plan: https://github.com/xamelion098/AutomationTestDIPLOM/blob/master/docs/Plan.md

Report: https://github.com/xamelion098/AutomationTestDIPLOM/blob/master/docs/Report.md

Summary: https://github.com/xamelion098/AutomationTestDIPLOM/blob/master/docs/Summary.md.
