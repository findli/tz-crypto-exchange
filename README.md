# tz-crypto-exchange

Тестовое задание (на Java):
Используйте тестовый сервер биржи Bitmex.com: https://testnet.bitmex.com/ 
Задание:
1. Получить и сохранять в памяти книгу заявок XBTUSD (orderBooKL2_25) методом Websocket API. (БД в памяти не подходит, использовать объекты java).
2. По REST-запросу к серверу выдавать 10 лучших котировок (5 best ask, 5 best bid) с объемами из книги заявок.