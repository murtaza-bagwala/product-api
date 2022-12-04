# product-api
This application contains the APIs to create, list and find the product.

## Prerequisites
This app uses :-

- Java 11.0.16
- Spring Boot 2.4.1
- In-Memory H2 Database

## Installation

- Clone the repository
- mvn install
- mvn spring-boot:run

After running, the project, switch to your browser and hit 
http://localhost:8080/api/products. We should see the list of Products.

Swagger documentation is also added, and it will be up at http://localhost:8080/swagger-ui.html#/

Below is the list of APIs that are implemented :-

**To create the Product**

`POST:- http://localhost:8080/api/products`

Request Body:- 

```
{
    "title": "Soap",
    "details": "Cleanising agent",
    "price": 40.5,
    "size": "small",
    "category": "domestic"
}

```

Response:- 

```

{
    "details": "Cleanising agent",
    "title": "Soap",
    "id": "8c3ed1bb-166c-47dd-a3bb-afd501f0f13a",
    "category": "domestic",
    "size": "small",
    "price": 40.5
}

```

If details, title or price has null value, it returns an error

```
{
    "timestamp": "2022-12-04",
    "status": 400,
    "errors": [
        "title must not be null"
    ]
}

```

**To list the Products**

`GET:- http://localhost:8080/api/products`

Response :- 

```
 [
    {
        "details": "Cleanising agent",
        "title": "Soap",
        "id": "8c3ed1bb-166c-47dd-a3bb-afd501f0f13a",
        "category": "domestic",
        "size": "small",
        "price": 40.5
    },
    {
        "details": "Cleanising agent",
        "title": "Detergent",
        "id": "05bb82bf-392f-489c-85d7-6743e8679212",
        "category": "domestic",
        "size": "small",
        "price": 40.5
    },
    {
        "details": "Healthy and Protenious bar",
        "title": "Proteing Bar",
        "id": "c4cd58ce-dade-4112-a6e1-e11afa4e625b",
        "category": "food",
        "size": null,
        "price": 100.5
    }
]
```

If products are not available it returns an error

```
{
    "timestamp": "2022-12-04T18:30:07.350255",
    "message": "No Product found"
}
```

**To fetch product details by Id**

`GET:- http://localhost:8080/api/products/fc231103-ded1-4a25-8d2a-d174bde3057c`

Response :- 

```
{
    "details": "Healthy and Protenious bar",
    "title": "Proteing Bar",
    "id": "fc231103-ded1-4a25-8d2a-d174bde3057c",
    "category": "food",
    "size": null,
    "price": 100.5
}
```
 If product is not found then it returns an error

```
{
    "timestamp": "2022-12-04T18:31:55.83154",
    "message": "Product not found"
}

```

## Domain Model and Design decisions.



