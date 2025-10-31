# BPTS

This app was created with Bootify.io - tips on working with the code [can be found here](https://bootify.io/next-steps/).

## Development

This is basic CRUD service develop to implement transfers funds from one bank account to another,
so not a real world or production code

# Improvement 

**Things that could be improved:**

Security would be based on Oauth or some other form like JWT or similar.

Account class is most basic, in real world it would have way more variables.

Id would be UUID, but as previously mentioned this is most basic setup.

After spending some time trying to set Lombok it just wouldn't work, so there for reason for leaving some annotation commented

I wanted to add custom errors log based on specific error code.

Mapping layer should be added.

OpenAPI would be a plus

Setting up Docker for some reason also took away a lot of time, so I choose to start app without it


# Testing


Start app by running in console -- mvn spring-boot:run

Next you can call using Postman or other API testing tool:

##  GET http://localhost:8080/account/all
**Response:**
- Status code: 200 OK
- Body:

[
{
"id": "5533",
"funds": 1000.00
},
{
"id": "2233",
"funds": 500.00
},
{
"id": "2255",
"funds": 700.00
},
{
"id": "22655",
"funds": 700.00
},
{
"id": "55555",
"funds": 900.00
}
]

## POST http://localhost:8080/transfer/funds
**Request**
- Status code: 200 OK
- Body:

{
"sourceAccount":"5533",
"targetAccount" :"2255",
"amount": 50
}

**Response**
- Status code: 200 OK
- Body:

{
"id": "2255",
"funds": 750.00
}

## GET http://localhost:8080/transfer/getAllTransfers
**Response**
- Status code: 200 OK
- Body:

[
{
"id": 10000,
"amount": 50.00,
"sourceAccount": "5533",
"destinationAccount": "2255",
"transactionTime": "2025-10-31T03:22:02.426221"
}
]








