# RESTful API Test Automation

## Table Of Contents
* [Test Scenario List](#test-scenario-list)
* [Different Levels of Automation](#different-levels-of-automation)
* [NFRs](#nfr)


## Test Scenario List

```
Base URL - http://localhost:8080/api/v3
```

### Pet API

#### Add a pet(```/pet```).
POST Request

		Sample Payload

		```
		{
		  "id": 1,
		  "name": "Dog Name",
		  "category": {
		    "id": 1,
		    "name": "Dogs"
		  },
		  "photoUrls": [
		    "url1"
		  ],
		  "tags": [
		    {
		      "id": 1,
		      "name": "tag1"
		    }
		  ],
		  "status": "available"
		}
		```

* TC1 
 - Should be able to successfully add a pet.

	**Given:**

	* Base url with end point.
	* Sample body playload in raw json. 
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Response body should be same as the request.
	* Default response headers should be in place.


* TC2 
 - Should accept when same pet details which is already used to create a pet is used again.

	**Given:**

	* Base url with end point.
	* Sample body playloadjson.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Response body should be same as the request.
	* Default response headers should be in place.
	* Duplicate should not be created when trying to fetch the details with id.

* TC3
 - Should throw error when different format is used other than raw json.

	**Given:**

	* Base url with end point.
	* Sample playload json in form data(other than raw json).
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 415
	* Response body message should be "HTTP 415 Unsupported Media Type".
	* Should not add a pet.


* TC4
 - Should throw error when id field is represented in String instead of Integer.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with ID as "10".
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: unable to convert input to io.swagger.petstore.model.Pet".
	* Should not add a pet.


* TC5
 - Should throw error when category id field is represented in String instead of Integer.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with Category ID as "10".
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: unable to convert input to io.swagger.petstore.model.Pet".
	* Should not add a pet.

* TC6
 - Should throw error when payload is empty.

	**Given:**

	* Base url with end point.
	* empty payload.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: ..." complete body.
	* Should not add a pet.

* TC7
 - Should be abe to add a pet only by giving the ID.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with just the ID.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Should not add a pet.
	* Pet created with given ID with photo urls and tags as empty list.


#### Find pets by status(```/pet/findByStatus?status={status}```).
GET Request

* TC1
 - Should be abe to successfully retrieve list of pets with the given status.

	**Given:**

	* Base url with end point.
	* status can be [available, pending, sold].
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Retrieve list of pets in json format with pet details as given in the sample payload.


* TC2
 - Should throw error when invalid status is given.

	**Given:**

	* Base url with end point.
	* If an invalid status(like "a") other than(available, pending, sold) is given in the query param.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Error Message - "Input Error."


* TC3
 - Should throw error when header content type is invalid.

	**Given:**

	* Base url with end point.
	* status is available.
	* Invalid Header content type.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Body - Html error for bad request.


#### Find pet by Id(```/pet/{id}```).
GET Request

* TC1
 - Should be able to successfully retrieve the pet with the given pet ID.

	**Given:**

	* Base url with end point.
	* Valid Id in the path param
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Retrieve the pet details in json format as given in the sample payload.

* TC2
 - Should given Not found message when the given pet id is not found in the DB.

	**Given:**

	* Base url with end point.
	* Pet ID which is not found in DB.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 404
	* Message in Body - "Pet not found".


* TC2
 - Should throw error when pet id is given in invalid format.

	**Given:**

	* Base url with end point.
	* Pet ID is given in text.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Message in Body - "Input error ...".



#### Find pets by Tags(```/pet/findByTags?tags={tag}```).
GET Request

* TC1
 - Should be abe to successfully retrieve list of pets with the given tag.

	**Given:**

	* Base url with end point.
	* Valid Tag name available in the DB in the param.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Retrieve list of pets in json format with pet details as given in the sample payload.


* TC2
 - Should return empty list when given tag name is not present.

	**Given:**

	* Base url with end point.
	* Tag name not present in DB
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Body - Empty List[].


* TC3
 - Should throw error when tag name is empty

	**Given:**

	* Base url with end point.
	* No tag param is given.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Body Message - No tags provided. Try again?


#### Delete Pet(```/pet/{petid}?petId={petId}```).
DELETE Request

* TC1
 - Should be able to successfully delete pet when valid pet id is given.

	**Given:**

	* Base url with end point.
	* Valid Pet Id available in the DB in the path param and query param.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Body message - Pet deleted.

* TC2
 - Should not throw error when petID not present in DB is provided

	**Given:**

	* Base url with end point.
	* Pet Id not present in the DB in the path param and query param.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Body message - Pet deleted.


* TC3
 - Should throw error when header content type is invalid.

	**Given:**

	* Base url with end point.
	* status is available.
	* Invalid Header content type.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	


#### Update a pet(```/pet```).
PUT Request

		Sample Payload

		```
		{
		  "id": 1,
		  "name": "Dog Name",
		  "category": {
		    "id": 1,
		    "name": "Dogs"
		  },
		  "photoUrls": [
		    "url1"
		  ],
		  "tags": [
		    {
		      "id": 1,
		      "name": "tag1"
		    }
		  ],
		  "status": "available"
		}
		```

* TC1 
 - Should be able to successfully Update a pet.

	**Given:**

	* Base url with end point.
	* Sample body playload in raw json with valid pet ID which is present in the DB. 
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Response body should be same as the request.
	* Default response headers should be in place.


* TC2
 - Should give a not found response when pet not present in DB is used as input.

	**Given:**

	* Base url with end point.
	* Sample body playload in raw json with valid pet ID which is not present in the DB. 
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 404
	* Response body - Pet not found	


* TC3
 - Should throw error when different format is used other than raw json.

	**Given:**

	* Base url with end point.
	* Sample playload json in form data(other than raw json).
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 415
	* Response body message should be "HTTP 415 Unsupported Media Type".


* TC4
 - Should throw error when id field is represented in String instead of Integer.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with ID as "10".
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: unable to convert input to io.swagger.petstore.model.Pet".


* TC5
 - Should throw error when category id field is represented in String instead of Integer.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with Category ID as "10".
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: unable to convert input to io.swagger.petstore.model.Pet".


* TC6
 - Should throw error when payload is empty.

	**Given:**

	* Base url with end point.
	* empty payload.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: ..." complete body.
	* Should not add a pet.

* TC7
 - Should be able to add a pet only by giving the ID.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with just the ID.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Pet created with given ID with photo urls and tags as empty list.


#### Update Pet With Form data(```/pet/{petid}?{QueryParamsToUpdate}```).
POST Request

* TC1
 - Should be able to successfully update pet when valid pet id and params to update are given.

	**Given:**

	* Base url with end point.
	* Valid Pet Id available in the DB in the path param and query params.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* response should be updated pet details.


* TC2
 - Should give a not found response when pet not present in DB is used as input.

	**Given:**

	* Base url with end point.
	* Sample body playload in raw json with valid pet ID which is not present in the DB. 
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 404
	* Response body - Pet not found	


* TC3
 - Should throw error when id field is given as text instead of Integer.

	**Given:**

	* Base url with end point.
	* path param Id field givn as "something".
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response message - should be "Input error: unable to convert input to Long".
	

* TC4
 - Should throw error when valid id field is given but name is not given in the input.

	**Given:**

	* Base url with end point.
	* valid path param Id field is given which is present in DB but name is not given in the query params.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response message - No Name provided. Try again?
	

#### Update pet by uploading image(```/pet/{petid}/uploadImage```).
POST Request

* TC1
 - Should be able to successfully upload the image to an existing pet.

	**Given:**

	* Base url with end point.
	* Valid existing Pet Id in the path param.
	* Content Type - application/octet-stream
	* Request Body - Binary
	* File upload - image file format.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* response - Pet details with photo urls updated with new entry.


* TC2
 - Should give a not found response when pet not present in DB is used as input.

	**Given:**

	* Base url with end point.
	* Pet Id not present in the DB.
	* Content Type - application/octet-stream
	* Request Body - Binary
	* File upload - image file format.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 404
	* Response body - Pet not found	


* TC2
 - Should throw error when unsupported content type is used.

	**Given:**

	* Base url with end point.
	* Pet Id not present in the DB.
	* Content Type - application/json
	* Request Body - Binary
	* File upload - image file format.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 415
	* Response body - HTTP 415 Unsupported Media Type


### Store API


#### Get Inventory(```/store/inventory```).
GET Request

* TC1
 - Should be able to successfully retrieve the current inventory.

	**Given:**

	* Base url with end point.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	```
	{
	    "approved": 50,
	    "placed": 100,
	    "delivered": 50
	}
	```

#### Place an Order(```/store/order```).
POST Request

Sample Payload

		```
		{
		  "id": 10,
		  "petId": 198772,
		  "quantity": 7,
		  "shipDate": "2022-06-30T18:35:15.641Z",
		  "status": "approved",
		  "complete": true
		}
		```

* TC1 
 - Should be able to successfully place an order.

	**Given:**

	* Base url with end point.
	* Sample body playload in raw json. 
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Response body should be same as the request.
	* Default response headers should be in place.


* TC2 
 - Should accept when same order details which is already used to place an order is used again.

	**Given:**

	* Base url with end point.
	* Sample body playloadjson.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Response body should be same as the request.
	* Default response headers should be in place.
	* Duplicate should not be createdin the DB.

* TC3
 - Should throw error when different format is used other than raw json.

	**Given:**

	* Base url with end point.
	* Sample playload json in form data(other than raw json).
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 415
	* Response body message should be "HTTP 415 Unsupported Media Type".
	* Should not add a pet.


* TC4
 - Should throw error when id field is represented in String instead of Integer.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with ID as "10".
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: unable to convert input to io.swagger.petstore.model.Pet".
	* Should not Place an Order.


* TC5
 - Should throw error when Pet id field is represented in String instead of Integer.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with Pet ID as "10".
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: unable to convert input to io.swagger.petstore.model.Pet".
	* Should not Place an Order.

* TC6
 - Should throw error when payload is empty.

	**Given:**

	* Base url with end point.
	* empty payload.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: ..." complete body.
	* Should not Place an Order.

* TC7
 - Should be able to Place an Order only by giving the ID.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with just the ID.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Should create an order with default empty lists.
	

#### Delete Order(```/store/order/{orderid}?petId={orderId}```).
DELETE Request

* TC1
 - Should be able to successfully delete Order when existing order id is provided.

	**Given:**

	* Base url with end point.
	* Valid Order Id available in the DB in the path param and query param.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200


* TC2
 - Should not throw error when Order not present in DB is provided

	**Given:**

	* Base url with end point.
	* Order Id not present in the DB in the path param and query param.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200


* TC3
 - Should throw error when header content type is invalid.

	**Given:**

	* Base url with end point.
	* status is available.
	* Invalid Header content type.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400


#### Find Order by Id(```/store/order/{id}```).
GET Request

* TC1
 - Should be able to successfully retrieve the order with existing order ID.

	**Given:**

	* Base url with end point.
	* Valid Id in the path param and query param.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Retrieve the order details in json format as given in the sample payload.

* TC2
 - Should given Not found message when the given order id is not found in the DB.

	**Given:**

	* Base url with end point.
	* Order ID which is not found in DB.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 404
	* Message in Body - "Order not found".


* TC3
 - Should throw error when pet id is given in invalid format.

	**Given:**

	* Base url with end point.
	* Order ID is given in text.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Message in Body - "Input error ...".


### User API

#### Create User(```/user```).
POST Request

		Sample Payload

		```
		{
		  "id": 20,
		  "username": "theUser20",
		  "firstName": "John",
		  "lastName": "James",
		  "email": "john@email.com",
		  "password": "12345",
		  "phone": "12345",
		  "userStatus": 1
		}
		```

* TC1 
 - Should be able to successfully create a user.

	**Given:**

	* Base url with end point.
	* Sample body playload in raw json. 
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Response body should be same as the request.
	* Default response headers should be in place.


* TC2 
 - Should accept when same user details which is already used to create a user, is used again.

	**Given:**

	* Base url with end point.
	* Sample body playloadjson.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Response body should be same as the request.
	* Default response headers should be in place.
	* Duplicate should not be created when trying to fetch the details with id.

* TC3
 - Should throw error when different format is used other than raw json.

	**Given:**

	* Base url with end point.
	* Sample playload json in form data(other than raw json).
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 415
	* Response body message should be "HTTP 415 Unsupported Media Type".
	* Should not create a user.


* TC4
 - Should throw error when id field is represented in String instead of Integer.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with ID as "10".
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: unable to convert input to io.swagger.petstore.model.Pet".
	* Should not create a user.


* TC6
 - Should throw error when payload is empty.

	**Given:**

	* Base url with end point.
	* empty payload.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: ..." complete body.
	* Should not create a user.

* TC7
 - Should be abe to create a user only by giving the ID.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with just the ID.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Should not add a pet.
	* User created with given ID with photo urls and tags as empty list.


#### User Login(```/user/login?username={username}&password={password}```).
GET Request

* TC1
 - Should be able to successfully login with valid username and password.

	**Given:**

	* Base url with end point.
	* Valid username and password given in query param.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* response message - Logged in user session: {session ID}


* TC2
 - Should not be able to successfully login when invalid username and password is given.

	**Given:**

	* Base url with end point.
	* Invalid username and password given in query param.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* response message - User not found

**Note:** Currently no validation present.


#### User Logout(```/user/logout```).
GET Request

* TC1
 - Should be able to successfully logout.

	**Given:**

	* Base url with end point.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* response message - User logged out

**Note:** Currently no validation present.


#### Find User by Id(```/user/{id}```).
GET Request

* TC1
 - Should be able to successfully retrieve the user detail with the given user ID.

	**Given:**

	* Base url with end point.
	* Valid Id in the path param
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Retrieve the User details in json format as given in the sample payload.

* TC2
 - Should given Not found message when the given user id is not found in the DB.

	**Given:**

	* Base url with end point.
	* User ID which is not found in DB.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 404
	* Message in Body - "User not found".


* TC2
 - Should throw error when user id is given in invalid format.

	**Given:**

	* Base url with end point.
	* User ID is given in text.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Message in Body - "Input error ...".


#### Create User List(```/user/createWithList```).
POST Request

		Sample Payload

		```
		[
			{
			  "id": 20,
			  "username": "theUser20",
			  "firstName": "John",
			  "lastName": "James",
			  "email": "john@email.com",
			  "password": "12345",
			  "phone": "12345",
			  "userStatus": 1
			}
		]
		```

* TC1 
 - Should be able to successfully create user list.

	**Given:**

	* Base url with end point.
	* Sample body playload in raw json. 
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Response body should be same as the request.
	* Default response headers should be in place.


* TC2 
 - Should accept when same user details which is already used to create a user, is used again.

	**Given:**

	* Base url with end point.
	* Sample body playloadjson.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Response body should be same as the request.
	* Default response headers should be in place.
	* Duplicate should not be created when trying to fetch the details with id.

* TC3
 - Should throw error when different format is used other than raw json.

	**Given:**

	* Base url with end point.
	* Sample playload json in form data(other than raw json).
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 415
	* Response body message should be "HTTP 415 Unsupported Media Type".
	* Should not create a user.


* TC4
 - Should throw error when id field is represented in String instead of Integer.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with ID as "10" in anyone one of the User Object.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: unable to convert input to io.swagger.petstore.model.User".
	* Should not create a user.


* TC6
 - Should throw error when payload is empty.

	**Given:**

	* Base url with end point.
	* empty payload.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 500
	* Response body message should be "Input error: ..." complete body.
	* Should not create user.


* TC7
 - Should throw error when payload is given with empty list.

	**Given:**

	* Base url with end point.
	* empty list payload.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "No User provided. Try again?".
	* Should not create user.


#### Delete User(```/user/{userid}```).
DELETE Request

* TC1
 - Should be able to successfully delete user when valid user id is given.

	**Given:**

	* Base url with end point.
	* Valid User Id available in the DB in the path param.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200


* TC2
 - Should not throw error when user Id not present in DB is provided

	**Given:**

	* Base url with end point.
	* User Id not present in the DB in the path param.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200


* TC3
 - Should throw error when header content type is invalid.

	**Given:**

	* Base url with end point.
	* status is available.
	* Invalid Header content type.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	


#### Update user(```/user/{userId}```).
PUT Request

		Sample Payload

		```
		{
		  "id": 20,
		  "username": "theUser20",
		  "firstName": "Phil",
		  "lastName": "Williams",
		  "email": "john@email.com",
		  "password": "12345",
		  "phone": "12345",
		  "userStatus": 1
		}
		```

* TC1 
 - Should be able to successfully Update a User.

	**Given:**

	* Base url with end point.
	* Sample body playload in raw json with valid User ID which is present in the DB. 
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Response body should be same as the request.
	* Default response headers should be in place.


* TC2
 - Should give a not found response when User not present in DB is used as input.

	**Given:**

	* Base url with end point.
	* Sample body playload in raw json with valid User ID which is not present in the DB. 
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 404
	* Response body - User not found	


* TC3
 - Should throw error when different format is used other than raw json.

	**Given:**

	* Base url with end point.
	* Sample playload json in form data(other than raw json).
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 415
	* Response body message should be "HTTP 415 Unsupported Media Type".


* TC4
 - Should throw error when id field is represented in String instead of Integer.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with ID as "10".
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: unable to convert input to io.swagger.petstore.model.Pet".


* TC5
 - Should throw error when payload is empty.

	**Given:**

	* Base url with end point.
	* Empty payload
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 400
	* Response body message should be "Input error: ..." complete body.


* TC6
 - Should be able to Update a user only by giving the ID.

	**Given:**

	* Base url with end point.
	* Sample playload json in raw Json with just the ID.
	* No Authorization required.
	
	**Response Verification:**

	* Status Code - 200
	* Response - updated user.



## Different Levels of Automation

* Most of the scnearios listed above should be covered as part of the unit tests. The integration/service tests can be primarily used to validate the integrations and to check the overall api functionality. Rest of the testcases w.r.t formats, negative, regex and other states can be covered as part of the unit tests which has the following advantages.

- Faster execution.
- Actual service is not required.
- Early feedback.
- Individual functionality can be verified quickly.


Pros of Service/integration tests
- testing the actual api.
- Captures incase of bugs with integrations.
- Can be run in local or in CI.
- tests the actual functionality rather than mocks in most cases.


## NFR
* Apart from the functional tests, the other attributes such as security, reliability, maintainability, scalability, usuability should also be validated for the APIs.

























