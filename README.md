# testRestaurant (Sales)
_API REST que cuenta con 3 métodos para las ventas de un restaurant_

## Login (POST)
Método de tipo POST que autoriza la creación y las consultas de ventas a través de la generación de un token de seguridad que debe añadirse en los header de los métodos a consultar
### url
```
http://localhost:8080/auth
``` 
### body
```
{
    "username": "user",
    "password": "password"
}
``` 
### Ejemplo parámetros del header que deben añadirse
```
Content-Type:application/json
Authorization:eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTY0MzM5NTYwNzAyLCJleHAiOjE1NjQ5NDQzNjB9.r4wKHk_Kpd63QxquK44r_DPzTA4lMLbVYeqXJyCGwD4wu0tqAFXR51vvIJzejORxwpIxTSZLguvzO8twhQV81A
``` 

## Crear Venta (POST)
Método de tipo POST que crea una venta 
### url
```
http://localhost:8080/sales
``` 
### body
```
{
	"date":"2019-07-28",
	"time":"14:03:07.911584",
	"paymentType":"tdc",
	"amount": 27.00,
	"currencyType": "clp",
	"operationCode": 1234
}
``` 
## Consultar ventas del día(GET)
Método de tipo GET que dada una fecha, consulta las ventas de un día en específico 
### url
```
http://localhost:8080/sales/date/{date}
``` 

