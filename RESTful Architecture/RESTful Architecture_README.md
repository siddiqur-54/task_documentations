# Table of Contents


# 1. Introduction to REST

## 1.1 What is REST (Representational State Transfer)?

REST, or Representational State Transfer, is an architectural style for designing networked applications. It is a set of constraints used for building scalable and maintainable web services.

REST is not a protocol or a standard, it is an architectural style. During the development phase, API developers can implement REST in a variety of ways.

REST is commonly used in web development for APIs that allow clients (like web browsers or mobile apps) to interact with servers.

> A Web API (or Web Service) conforming to the REST architectural style is called a _REST API_  (or  _RESTful API_).


## 1.2 History and Evolution of REST

REST was introduced in 2000 by Roy Fielding in his Ph.D. [dissertation](https://www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm) titled "Architectural Styles and the Design of Network-based Software Architectures." Fielding, who was a co-author of the HTTP specification, designed REST to guide the evolution of the web. REST has since become the standard for building web services, especially due to its simplicity compared to SOAP.

## 1.3 Core Principles of REST

REST is based on some constraints and principles that promote simplicity, scalability, and statelessness in the design. The six guiding principles or [constraints of the RESTful architecture](https://restfulapi.net/rest-architectural-constraints/) are:
![](https://restfulapi.net/wp-content/uploads/What-is-REST.png)

1. **Uniform Interface**: A uniform interface between components simplifies the architecture and improves scalability.

2. **Client-Server Architecture**: The system is divided into clients and servers, where clients request resources, and servers provide resources.

3. **Statelessness**: Each client request to the server must contain all the necessary information. The server does not store any session state between requests.

4. **Cacheability**: Responses must define themselves as cacheable or non-cacheable to improve network efficiency.

5. **Layered System**: The system architecture can have multiple layers, with each layer interacting only with the adjacent layers.

6. **Code on Demand (Optional)**: Servers can send executable code (e.g., JavaScript) to clients to enhance functionality.


## REST vs. SOAP

| Aspect            | REST                            | SOAP                          |
|-------------------|---------------------------------|-------------------------------|
| **Protocol**      | Primarily HTTP                  | Uses multiple protocols (HTTP, SMTP, etc.) |
| **Message Format**| Typically JSON or XML           | XML                           |
| **Complexity**    | Simple, lightweight             | More complex and rigid        |
| **State**         | Stateless                       | Can be stateful               |
| **Performance**   | Generally faster                | Slower due to envelope format |

While SOAP provides more rigid security and transaction handling, REST is preferred for simpler, lightweight, and scalable APIs, especially in web applications.

---


# 2. REST Architecture Constraints
🔼 [Back to Top](#table-of-contents)

REST is based on six architectural constraints, which define the interaction between components in a RESTful system. These constraints ensure that the system is scalable, maintainable, and efficient.

## 2.1 Uniform Interface
🔼 [Back to Top](#table-of-contents)

This is one of the most important principles of REST, ensuring that the API interaction is standardized. Multiple architectural constraints help in obtaining a uniform interface and guiding the behavior of components. The uniform interface consists of four key concepts:

1. **Identification of resources**: Resources are identified using URIs (Uniform Resource Identifiers). Each resource is uniquely addressable via its URI.

2. **Manipulation of resources through representations**: Clients interact with resources via representations (e.g., JSON or XML). For example, a client sends a `PUT` request with a JSON body to update a resource.

3. **Self-descriptive messages**: Every REST message (HTTP request/response) contains enough information to describe how to process the message. This includes headers, status codes, media types, etc.

4. **HATEOAS (Hypermedia As The Engine Of Application State)**: Clients should be able to discover available actions dynamically by following links embedded in responses. Instead of hard-coding the structure of the interaction, the client follows links provided by the server to navigate the application.

Example of Uniform Interface:  
A `GET` request to `/users/123` returns a JSON representation of a user resource. The response might also contain links (HATEOAS) to related actions like updating or deleting the user.

## 2.2 Client-Server Architecture
🔼 [Back to Top](#table-of-contents)

The client-server design pattern enforces the separation of concerns.
- **Separation of concerns**: REST separates the user interface (client) from data storage (server). This allows the client and server to evolve independently, promoting modularity.
- **Responsibilities**:
  - **Client**: Manages the user interface and sends requests for resources.
  - **Server**: Handles data storage, business logic, and responds to client requests.
  
The separation of client and server simplifies the architecture, as the client does not need to know anything about the data storage or business logic. This makes it easier to scale and maintain both the client and server.


## 2.3 Statelessness
🔼 [Back to Top](#table-of-contents)

Statelessness recommends making all the client-server interactions stateless. What it means is that the server will not store anything about the latest HTTP request the client made. It will treat every request as new.

Each HTTP request to a RESTful service must contain all the information needed to understand and process this request. This kind of statelessness makes it easier to scale, cache, and manage the service.

![](https://restfulapi.net/wp-content/uploads/Stateless-REST-API-1024x576.png)

### What is a Stateless REST API?

A stateless REST API adheres to the principle of statelessness as defined by the REST architectural style. Stateless REST APIs do not establish or maintain client sessions. Clients are responsible for providing all necessary information in each request, such as authentication tokens, credentials, or context data. The server does not store client-specific session data.

The application’s session state is therefore kept entirely on the client.  **The client is responsible for storing and handling the session-related information on its own**  **side**.

This also means that the client is responsible for sending any state information to the server whenever it is needed. There should not be any  _**session affinity**_  or  _**sticky session**_  between the client and the server.

> Statelessness means that every HTTP request happens in complete isolation. When the client makes an HTTP request, it includes all information necessary for the server to fulfill the request.
> 
> The server never relies on information from previous requests from the client. If any such information is important then the client will send that as part of the current request.

To enable clients to access these stateless APIs, it is necessary that servers also include every piece of information that the client may need to create/maintain the state on its side.

To become stateless, do not store even the authentication/authorization details of the client. Provide authentication credentials with each request. Thus each request MUST be stand-alone and should not be affected by the previous conversation that happened with the same client in the past.

### Application State vs Resource State

It is important to understand the between the application state and the resource state. Both are completely different things.

**Application state**  is server-side data that servers store to identify incoming client requests, their previous interaction details, and current context information.

**Resource state**  is the current state of a resource on a server at any point in time – and it has nothing to do with the interaction between client and server. It is what we get as a response from the server as the API response. We refer to it as resource representation.

> REST statelessness means being free from the application state.

### Advantages of Stateless APIs

Stateless APIs are often simpler to develop, test, and maintain because they do not require managing session state or tracking client interactions.

There are some very noticeable advantages of having REST APIs stateless.

- Statelessness helps in  **scaling the APIs to millions of concurrent users**  by deploying it to multiple servers. Any server can handle any request because there is no session-related dependency.
- Being stateless makes REST APIs  **less complex**  – by removing all server-side state synchronization logic.
- A stateless API is also  **easy to  [cache](https://restfulapi.net/caching/)**  as well. Specific software can decide whether or not to cache the result of an HTTP request just by looking at that one request. There’s no nagging uncertainty that the state from a previous request might affect the cacheability of this one. It improves the performance of applications.
- The server never loses track of "where" each client is in the application because the client sends all necessary information with each request.

## 2.4 Cacheability
🔼 [Back to Top](#table-of-contents)

> [Caching](https://tools.ietf.org/html/rfc7234)  is the ability to store copies of frequently accessed data in several places along the request-response path.

When a consumer requests a resource representation, the request goes through a cache or a series of caches (local cache, proxy cache, or reverse proxy) toward the service hosting the resource.

If any of the caches along the request path has a fresh copy of the requested representation, it uses that copy to satisfy the request. If none of the caches can satisfy the request, the request travels to the service (or origin server as it is formally known).

By using HTTP headers, an origin server indicates whether a response can be cached and, if so, by whom, and for how long.

Caches along the response path can take a copy of a response, but only if the caching metadata allows them to do so.

Optimizing the network using caching improves the overall quality-of-service in the following ways:

-  **Reduce bandwidth**
-  **Reduce latency**
-  **Reduce load on servers**
-  **Hide network failures**

### Caching in REST APIs

Being cacheable is one of the architectural constraints of REST.

-   **GET**  requests should be cachable by default – until a special condition arises. Usually, browsers treat all GET requests as cacheable.

-   **POST**  requests are not cacheable by default but can be made cacheable if either an  `Expires`  header or a  `Cache-Control`  header with a directive, to explicitly allows caching, is added to the response.
-   Responses to  `**PUT**`  and  `**DELETE**`  requests are not cacheable at all.

> Please note that  **[HTTP dates are always expressed in GMT](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Last-Modified)**, never in local time.

### Cache Control Headers

Below given are main HTTP response headers that we can use to  **control caching behavior**:

#### Expires

The  _Expires_  HTTP header specifies an absolute expiry time for a cached representation. Beyond that time, a cached representation is considered stale and must be re-validated with the origin server.

To indicate that a representation never expires, a service can include a time up to one year in the future.

```bash
Expires: Fri, 20 May 2016 19:20:49 GMT
```

#### Cache-Control

The header value comprises one or more comma-separated  [directives](https://tools.ietf.org/html/rfc7234#page-24). These directives determine whether a response is cacheable, and if so, by whom, and for how long e.g.  `max-age`  or  `s-maxage`  directives.

```bash
Cache-Control: max-age=3600
```

Cacheable responses (whether to a GET or to a POST request) should also include a validator — either an  _ETag_  or a  _Last-Modified_  header.

#### ETag

An  _ETag_  value is an opaque string token that a server associates with a resource to uniquely identify the state of the resource over its lifetime.

If the resource at a given URL changes, a new `Etag` value _must_ be generated. A comparison of them can determine whether two representations of a resource are the same.

While requesting a resource, client sends the  _ETag_  in  _If-None-Match_  header field to the server. The server matches the  _Etag_  of the requested resource and the value sent in  _If-None-Match_  header. If both values match the server sends back a `304` `Not Modified` status, without a body, which tells the client that the cached version of the response is still good to use (_fresh_).

```bash
ETag: "abcd1234567n34jv"
```

#### Last-Modified

Whereas a response’s  _Date_  header indicates when the response was generated, the  _Last-Modified_  header indicates when the associated resource was last changed.

This header is used as a validator to determine if the resource is the same as the previously stored one by the client’s cache. Less accurate than an `_ETag_` header, it is a fallback mechanism.

The  _Last-Modified_  value cannot be greater than  _Date_  value. Note that Date header is listed in the  [forbidden header names](https://fetch.spec.whatwg.org/#forbidden-header-name).

```bash
Last-Modified: Fri, 10 May 2016 09:17:49 GMT
```

## 2.5 Layered System
🔼 [Back to Top](#table-of-contents)

- **Layered architecture**: REST systems are designed to have multiple layers, with each layer handling specific tasks. The client cannot tell if it is connected directly to the server or through an intermediary (e.g., load balancer, cache server, or security proxy).
- **Advantages**:
  - Improves scalability by allowing load balancers, proxies, and firewalls to be introduced between clients and servers.
  - Enhances security by hiding the underlying system architecture from clients.

- **Example**
    - A layman’s example of a layered system is the MVC pattern. The MVC pattern allows for a clear separation of concerns, making it easier to develop, maintain, and scale the application.
    - A REST client may send a request to an API gateway that forwards the request to different backend services, such as a database or third-party API, based on the request type.

## 2.6 Code on Demand (Optional)
🔼 [Back to Top](#table-of-contents)

REST allows servers to extend client functionality by sending executable code (e.g., JavaScript) as part of the response.
- **Use case**: Typically used in browsers, where the server sends JavaScript code that the client can execute to enhance the user experience.
- **Optional constraint**: Code on Demand is optional in REST, and not all APIs will implement it.

- **Example**  
A web page might request HTML from a server and also receive JavaScript code to be executed in the client’s browser, enhancing user interaction on the page.



# 3. What is a Resource?
🔼 [Back to Top](#table-of-contents)

In  [REST](https://restfulapi.net/), the primary data representation is called  **resource**. Having a consistent and robust REST resource naming strategy – will prove one of the best design decisions in the long term.

> The key abstraction of information in REST is a resource. Any information that can be named can be a resource: a document or image, a temporal service (e.g. “today’s weather in Los Angeles”), a collection of other resources, a non-virtual object (e.g., a person), and so on.
> 
> In other words, any concept that might be the target of an author’s hypertext reference must fit within the definition of a resource.
> 
> A resource is a conceptual mapping to a set of entities, not the entity that corresponds to the mapping at any particular point in time.
> 
> —  [Roy Fielding’s dissertation](http://www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm#sec_5_2_1_1)

## 3.1 Singleton and Collection Resources
🔼 [Back to Top](#table-of-contents)

A  **resource can be a singleton or a collection**.

For example, “`customers`” is a collection resource and “`customer`” is a singleton resource (in a banking domain).

We can identify “`customers`” collection resource using the URI “`/customers`“. We can identify a single “`customer`” resource using the URI “`/customers/{customerId}`“.

```
/customers			//is a collection resource

/customers/{id}		// is a singleton resource
```

## 3.2 Collection and Sub-collection Resources
🔼 [Back to Top](#table-of-contents)

A  **resource may contain sub-collection resources**  also.

For example, sub-collection resource “`accounts`” of a particular “`customer`” can be identified using the URN “`/customers/{customerId}/accounts`” (in a banking domain).

Similarly, a singleton resource “`account`” inside the sub-collection resource “`accounts`” can be identified as follows: “`/customers/{customerId}/accounts/{accountId}`“.

```
/customers						//is a collection resource

/customers/{id}/accounts		// is a sub-collection resource
```

## 3.3 URI
🔼 [Back to Top](#table-of-contents)

REST APIs use  [Uniform Resource Identifiers](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier)  (URIs) to address resources. REST API designers should create URIs that convey a REST API’s resource model to the potential clients of the API. When resources are named well, an API is intuitive and easy to use. If done poorly, that same API can be challenging to use and understand.

> _The constraint of a uniform interface is partially addressed by the combination of URIs and HTTP verbs and using them in line with the standards and conventions._

Below are a few tips to get you going when creating the resource URIs for your new API.

## 3.4 Best Practices
🔼 [Back to Top](#table-of-contents)

### Use nouns to represent resources

RESTful URI should refer to a resource that is a thing (noun) instead of referring to an action (verb) because nouns have properties that verbs do not have – similarly, resources have attributes. Some examples of a resource are:

-   Users of the system
-   User Accounts
-   Network Devices etc.

and their resource URIs can be designed as below:

```
/device-management/managed-devices 

/device-management/managed-devices/{device-id} 

/user-management/users

/user-management/users/{id}
```

For more clarity, let’s divide the  **resource archetypes**  into three categories (document, collection, and store). Then it would be best if  **you always targeted to put a resource into one archetype and then use its naming convention consistently**.

_For uniformity’s sake, resist the temptation to design resources that are hybrids of more than one archetype._

### document

A document resource is a singular concept that is akin to an object instance or database record.

In REST, you can view it as a single resource inside a resource collection. A document’s state representation typically includes both fields with values and links to other related resources.

Use the “_singular_” name to denote the document resource archetype.

```
http://api.example.com/device-management/managed-devices/{device-id}
http://api.example.com/user-management/users/{id}
http://api.example.com/user-management/users/admin
```

### collection

A collection resource is a server-managed directory of resources.

Clients may propose new resources to be added to a collection. However, it is up to the collection resource to choose whether to create a new resource or not.

A collection resource chooses what it wants to contain and also decides the URIs of each contained resource.

**Use the “plural” name to denote the collection resource archetype.**

```
/device-management/managed-devices
/user-management/users
/user-management/users/{id}/accounts
```

### store

A store is a client-managed resource repository. A store resource lets an API client put resources in, get them back out, and decide when to delete them.

A store never generates new URIs. Instead, each stored resource has a URI. The URI was chosen by a client when the resource was initially put into the store.

Use “plural” name to denote store resource archetype.

```
/song-management/users/{id}/playlists
```

## 3.5 Consistency is the key
🔼 [Back to Top](#table-of-contents)

Use consistent resource naming conventions and URI formatting for minimum ambiguity and maximum readability and maintainability. You may implement the below design hints to achieve consistency:

### Use forward slash (/) to indicate hierarchical relationships

The forward-slash (/) character is used in the path portion of the URI to indicate a hierarchical relationship between resources. e.g.

```
/device-management
/device-management/managed-devices
/device-management/managed-devices/{id}
/device-management/managed-devices/{id}/scripts
/device-management/managed-devices/{id}/scripts/{id}
```

### Do not use trailing forward slash (/) in URIs

As the last character within a URI’s path, a forward slash (/) adds no semantic value and may confuse. It’s better to drop it from the URI.

```
http://api.example.com/device-management/managed-devices/ 
http://api.example.com/device-management/managed-devices         /*This is much better version*/
```

### Use hyphens (-) to improve the readability of URIs

To make your URIs easy for people to scan and interpret, use the hyphen (-) character to improve the readability of names in long-path segments.

```
http://api.example.com/devicemanagement/manageddevices/
http://api.example.com/device-management/managed-devices 	/*This is much better version*/
```

### Do not use underscores ( _ )

It’s possible to use an underscore in place of a hyphen to be used as a separator – But depending on the application’s font, it is possible that the underscore (_) character can either get partially obscured or completely hidden in some browsers or screens.

To avoid this confusion, use hyphens (-) instead of underscores ( _ ).

```
http://api.example.com/inventory-management/managed-entities/{id}/install-script-location  //More readable

http://api.example.com/inventory-management/managedEntities/{id}/installScriptLocation  //Less readable
```

### Use lowercase letters in URIs

When convenient, lowercase letters should be consistently preferred in URI paths.

```
http://api.example.org/my-folder/my-doc       //1
HTTP://API.EXAMPLE.ORG/my-folder/my-doc     //2
http://api.example.org/My-Folder/my-doc       //3
```

In the above examples, 1 and 2 are the same, but 3 is not as it uses **My-Folder** in capital letters.

## 3.6 Do not use file extensions

File extensions look bad and do not add any advantage. Removing them decreases the length of URIs as well. No reason to keep them.

Apart from the above reason, if you want to highlight the media type of API using file extension, then you should  **rely on the media type, as communicated through the** `**Content-Type**` **header, to determine how to process the body’s content**.

```
/device-management/managed-devices.xml  /*Do not use it*/

/device-management/managed-devices 	/*This is correct URI*/
```

## 3.7 Never use CRUD function names in URIs
🔼 [Back to Top](#table-of-contents)

We should not use URIs to indicate a CRUD function. URIs should only be used to identify the resources and not any action upon them uniquely.

We should use HTTP request methods to indicate which CRUD function is performed.

```
HTTP GET /device-management/managed-devices  			//Get all devices
HTTP POST /device-management/managed-devices  			//Create new Device

HTTP GET /device-management/managed-devices/{id}  		//Get device for given Id
HTTP PUT /device-management/managed-devices/{id}  		//Update device for given Id
HTTP DELETE /device-management/managed-devices/{id}  	//Delete device for given Id
```

### 3.8 Use query component to filter URI collection
🔼 [Back to Top](#table-of-contents)

Often, you will encounter requirements where you will need a collection of resources sorted, filtered, or limited based on some specific resource attribute.

For this requirement, do not create new APIs – instead, enable sorting, filtering, and pagination capabilities in resource collection API and pass the input parameters as query parameters. e.g.

```
/device-management/managed-devices
/device-management/managed-devices?region=USA
/device-management/managed-devices?region=USA&brand=XYZ
/device-management/managed-devices?region=USA&brand=XYZ&sort=installation-date
```

## 3.9 Do not Use Verbs in the URI
🔼 [Back to Top](#table-of-contents)

It is not correct to put the verbs in REST URIs. REST uses nouns to represent resources, and HTTP methods (GET, POST, PUT, DELETE, etc.) are then used to perform actions on those resources, effectively acting as verbs.

If we use verbs in the URI, we are most probably creating an RPC-style method call having a JSON or XML request/response format. It would be incorrect to call it REST.

```
/device-management/managed-devices/{id}/scripts/{id}/execute    //It is RPC, and not REST
```

In cases, where we need to perform some action that does not apply naturally to the definition of resources, we can create the custom URIs that can be considered nouns/resources and perform an action over them.

For example, instead of invoking  `/scripts/{id}/execute`  , we can create a resource for all scripts currently executing and submit a script to it if we want to execute a script.

```
/device-management/managed-devices/{id}/scripts/{id}/execute	//DON't DO THIS!

/device-management/managed-devices/{id}/scripts/{id}/status		//POST request with action=execute
```

> You may also consider using the custom methods with colon(:) as described in  [custom methods by Google Cloud Docs](https://cloud.google.com/apis/design/custom_methods), although many may find it in violation of REST principles.


# 4. HTTP Methods
🔼 [Back to Top](#table-of-contents)

REST APIs enable you to develop all kinds of web applications having all possible CRUD (create, retrieve, update, delete) operations.

REST guidelines suggest using a specific HTTP method on a particular type of call made to the server (though technically it is possible to violate this guideline, yet it is highly discouraged).

## 4.1 HTTP GET
🔼 [Back to Top](#table-of-contents)

Use  _GET_  requests  **to retrieve resource representation/information only**  – and not modify it in any way. As GET requests do not change the resource’s state, these are said to be  **safe methods**.

Additionally,  **GET APIs should be idempotent.** Making multiple identical requests must produce the same result every time until another API (POST or PUT) has changed the state of the resource on the server.

If the Request-URI refers to a data-producing process, it is the produced data that shall be returned as the entity in the response and not the source text of the process, unless that text happens to be the output of the process.

### 4.1.1 GET API Response Codes

-   For any given HTTP GET API, if the resource is found on the server, then it must return HTTP response code  `[200 (OK)](https://restfulapi.net/http-status-200-ok/)`  – along with the response body, which is usually either XML or JSON content (due to their platform-independent nature).
-   In case the resource is NOT found on the server then API must return HTTP response code  `404 (NOT FOUND)`.
-   Similarly, if it is determined that the GET request itself is not correctly formed then the server will return the HTTP response code  `400 (BAD REQUEST)`.

### 4.1.2 Example URIs

```java
HTTP GET http://www.appdomain.com/users
HTTP GET http://www.appdomain.com/users?size=20&page=5
HTTP GET http://www.appdomain.com/users/123
HTTP GET http://www.appdomain.com/users/123/address
```

## 2. HTTP POST
🔼 [Back to Top](#table-of-contents)

Use POST APIs  **to create new subordinate resources**, e.g., a file is subordinate to a directory containing it or a row is subordinate to a database table.

When talking strictly about REST, POST methods are used to  **create a new resource**  into the collection of resources.

Responses to this method are  **not cacheable**  unless the response includes appropriate  [Cache-Control](https://en.wikipedia.org/wiki/Web_cache#Cache_control)  or  [Expires](https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html)  header fields.

Please note that POST is  **neither safe nor idempotent**, and invoking two identical POST requests will result in two different resources containing the same information (except resource ids).

### 4.2.1 POST API Response Codes

-   Ideally, if a resource has been created on the origin server, the response SHOULD be HTTP response code  [201 (Created)](https://restfulapi.net/http-status-201-created/)  and contain an entity that describes the status of the request and refers to the new resource, and a  [Location](https://en.wikipedia.org/wiki/HTTP_location)  header.
-   Many times, the action performed by the POST method might not result in a resource that can be identified by a URI. In this case, either HTTP response code  `200 (OK)`  or  `204 (No Content)`  is the appropriate response status.

### 4.2.2. Example URIs

```java
HTTP POST http://www.appdomain.com/users
HTTP POST http://www.appdomain.com/users/123/accounts
```

## 3. HTTP PUT
🔼 [Back to Top](#table-of-contents)

Use PUT APIs primarily  **to update an existing resource (if the resource does not exist, then API may decide to create a new resource or not)**.

If the request passes through a cache and the Request-URI identifies one or more currently cached entities, those entries SHOULD be treated as stale. Responses to PUT method are  **not cacheable**.

### 4.3.1 PUT API Response Codes

-   If a new resource has been created by the PUT API, the origin server MUST inform the user agent via the HTTP response code  `201 (Created)`  response.
-   If an existing resource is modified, either the  `200 (OK)`  or  `204 (No Content`) response codes SHOULD be sent to indicate successful completion of the request.

### 4.3.2 Example URIs

```java
HTTP PUT http://www.appdomain.com/users/123
HTTP PUT http://www.appdomain.com/users/123/accounts/456
```

> The  [difference between the POST and PUT APIs](https://restfulapi.net/rest-put-vs-post/)  can be observed in request URIs. POST requests are made on resource collections, whereas PUT requests are made on a single resource.

## 4. HTTP DELETE
🔼 [Back to Top](#table-of-contents)

As the name applies, DELETE APIs  **delete the resources**  (identified by the Request-URI).

DELETE operations are  **idempotent**. If you DELETE a resource, it’s removed from the collection of resources.

Some may argue that it makes the DELETE method non-idempotent. It’s a matter of discussion and personal opinion.

If the request passes through a cache and the Request-URI identifies one or more currently cached entities, those entries SHOULD be treated as stale. Responses to this method are  **not cacheable**.

### 4.4.1 DELETE API Response Codes

-   A successful response of DELETE requests SHOULD be an HTTP response  `code 200 (OK)`  if the response includes an entity describing the status.
-   The status should be  `202 (Accepted)`  if the action has been queued.
-   The status should be  `204 (No Content)`  if the action has been performed but the response does not include an entity.
-   Repeatedly calling DELETE API on that resource will not change the outcome – however, calling DELETE on a resource a second time will return a 404 (NOT FOUND) since it was already removed.

### 4.4.2 Example URIs

```java
HTTP DELETE http://www.appdomain.com/users/123
HTTP DELETE http://www.appdomain.com/users/123/accounts/456
```

## 4.5 HTTP PATCH
🔼 [Back to Top](#table-of-contents)

HTTP PATCH requests are  **to make a partial update**  on a resource.

If you see PUT requests modify a resource entity too. So to make it more precise – the PATCH method is the correct choice for partially updating an existing resource, and you should only use PUT if you’re replacing a resource in its entirety.

Please note that there are some challenges if you decide to use PATCH APIs in your application:

Support for PATCH in browsers, servers, and web application frameworks is not universal. IE8, PHP, Tomcat, Django, and lots of other software have missing or broken support for it.

Request payload of a PATCH request is not straightforward as it is for a PUT request. e.g.

```bash
HTTP GET /users/1
```

produces below response:

```json
{ "id": 1, "username": "admin", "email": "email@example.org"}
```

A sample patch request to update the email will be like this:

```bash
HTTP PATCH /users/1
[{ "op": "replace", "path": "/email", "value": "new.email@example.org" }]
```

There may be the following possible operations are per the HTTP specification.

```json
[
 { "op": "test",  "path": "/a/b/c",  "value": "foo"  },
 { "op": "remove",  "path": "/a/b/c"  },
 { "op": "add",  "path": "/a/b/c",  "value": [ "foo", "bar" ] },
 { "op": "replace", "path": "/a/b/c",  "value": 42 },
 { "op": "move",  "from": "/a/b/c",  "path": "/a/b/d" },
 { "op": "copy", "from": "/a/b/d",  "path": "/a/b/e" }
 ]
```

> The PATCH method is not a replacement for the POST or PUT methods. It applies a delta (diff) rather than replacing the entire resource.

## 4.6 Glossary
🔼 [Back to Top](#table-of-contents)

### 4.6.1 Safe Methods

Request methods are considered  _safe_  if their defined semantics are essentially read-only. The client does not request, and does not expect, any state change on the origin server as a result of applying a safe method to a target resource.

**The GET, HEAD, OPTIONS, and TRACE methods are considered safe methods**. As per HTTP specification, the GET and HEAD methods should be used only for retrieval of resource representations – and they do not update/delete the resource on the server.

The  **purpose of distinguishing between safe and unsafe methods**  is to allow automated retrieval processes (spiders) and cache performance optimization (pre-fetching) to work without fear of causing harm.

Safe methods allow user agents to represent other methods, such as  _POST, PUT and DELETE_, in a unique way so that the user is made aware of the fact that a possibly unsafe action is being requested – and they can update/delete the resource on the server and so should be used carefully.

### 4.7 Idempotent Methods

The term idempotent is used more comprehensively to describe an  **operation that will produce the same results if executed once or multiple times**.

In HTTP specification, the  **PUT, DELETE and safe methods (**GET, HEAD, OPTIONS, TRACE**) are idempotent methods**.

Idempotence is a handy property in many situations, as it means that an operation can be repeated or retried as often as necessary without causing unintended effects.

With non-idempotent operations, the algorithm may have to keep track of whether the operation was already performed or not.

Like the definition of  _safe methods_, the idempotent property only applies to what has been requested by the user; a server is free to log each request separately or retain a revision control history.

# 5. REST Specific HTTP Status Codes

REST APIs use the  **Status-Line**  part of an HTTP response message to inform clients of their request’s overarching result.  [RFC 2616](https://www.ietf.org/rfc/rfc2616.txt)  defines the  [Status-Line syntax](https://www.w3.org/Protocols/rfc2616/rfc2616-sec6.html#sec6.1)  as shown below:

> Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF

HTTP defines these standard status codes that can be used to convey the results of a client’s request. The status codes are divided into five categories.

-   **[1xx: Informational](https://restfulapi.net/http-status-codes/#1xx)**  – Communicates transfer protocol-level information.
-   **[2xx: Success](https://restfulapi.net/http-status-codes/#2xx)**  – Indicates that the client’s request was accepted successfully.
-   **[3xx: Redirection](https://restfulapi.net/http-status-codes/#3xx)**  – Indicates that the client must take some additional action in order to complete their request.
-   **[4xx: Client Error](https://restfulapi.net/http-status-codes/#4xx)**  – This category of error status codes points the finger at clients.
-   **[5xx: Server Error](https://restfulapi.net/http-status-codes/#5xx)**  – The server takes responsibility for these error status codes.

#### [200 (OK)](https://restfulapi.net/http-status-200-ok/)

It indicates that the REST API successfully carried out the client’s requested action and that no more specific code in the 2xx series is appropriate.

Unlike the 204 status code, a 200 response should include a response body. The information returned with the response is dependent on the method used in the request, for example:

-   GET an entity corresponding to the requested resource is sent in the response;
-   HEAD the entity-header fields corresponding to the requested resource are sent in the response without any message-body;
-   POST an entity describing or containing the result of the action;
-   TRACE an entity containing the request message as received by the end server.

#### [201 (Created)](https://restfulapi.net/http-status-201-created/)

A REST API responds with the 201 status code whenever a resource is created inside a collection. There may also be times when a new resource is created as a result of some controller action, in which case 201 would also be an appropriate response.

The newly created resource can be referenced by the URI(s) returned in the entity of the response, with the most specific URI for the resource given by a Location header field.

The origin server MUST create the resource before returning the 201 status code. If the action cannot be carried out immediately, the server SHOULD respond with a 202 (Accepted) response instead.

#### [202 (Accepted)](https://restfulapi.net/http-status-202-accepted/)

A 202 response is typically used for actions that take a long while to process. It indicates that the request has been accepted for processing, but the processing has not been completed. The request might or might not be eventually acted upon, or even maybe disallowed when processing occurs.

Its purpose is to allow a server to accept a request for some other process (perhaps a batch-oriented process that is only run once per day) without requiring that the user agent’s connection to the server persist until the process is completed.

The entity returned with this response SHOULD include an indication of the request’s current status and either a pointer to a status monitor (job queue location) or some estimate of when the user can expect the request to be fulfilled.

#### [204 (No Content)](https://restfulapi.net/http-status-204-no-content/)

The 204 status code is usually sent out in response to a  `PUT`,  `POST`, or  `DELETE`  request when the REST API declines to send back any status message or representation in the response message’s body.

An API may also send 204 in conjunction with a GET request to indicate that the requested resource exists but has no state representation to include in the body.

If the client is a user agent, it SHOULD NOT change its document view from that which caused the request to be sent. This response is primarily intended to allow input for actions to take place without causing a change to the user agent’s active document view. However, any new or updated metainformation SHOULD be applied to the document currently in the user agent’s dynamic view.

The 204 response MUST NOT include a message body and is thus always terminated by the first empty line after the header fields.

#### [301 (Moved Permanently)](https://restfulapi.net/http-status-301-moved-permanently/)

The 301 status code indicates that the REST API’s resource model has been significantly redesigned, and a new permanent URI has been assigned to the client’s requested resource. The REST API should specify the new URI in the response’s Location header, and all future requests should be directed to the given URI.

You will hardly use this response code in your API as you can always use the API versioning for the new API while retaining the old one.

#### 302 (Found)

The HTTP response status code 302 Found is a common URL redirection method. An HTTP response with this status code will additionally provide a URL in the Location header field. The user agent (e.g., a web browser) is invited by a response with this code to make a second. Otherwise, an identical request to the new URL specified in the location field.

Many web browsers implemented this code in a manner that violated this standard, changing the request type of the new request to GET, regardless of the type employed in the original request (e.g., POST). RFC 1945 and RFC 2068 specify that the client is not allowed to change the method on the redirected request. The status codes 303 and 307 have been added for servers that wish to make unambiguously clear what kind of reaction the client expects.

#### 303 (See Other)

A 303 response indicates that a controller resource has finished its work. Instead of sending a potentially unwanted response body, it sends the client the URI of a response resource. The response can be the URI of the temporary status message or the URI of some already existing, more permanent resource.

Generally speaking, the 303 status code allows a REST API to send a reference to a resource without forcing the client to download its state. Instead, the client may send a GET request to the value of the Location header.

The 303 response MUST NOT be cached, but the response to the second (redirected) request might be cacheable.

#### 304 (Not Modified)

This status code is similar to 204 (“No Content”) in that the response body must be empty. The critical distinction is that 204 is used when nothing is to be sent in the body, whereas 304 is used when the resource has not been modified since the version specified by the request headers If-Modified-Since or If-None-Match.

In such a case, there is no need to retransmit the resource since the client still has a previously downloaded copy.

Using this saves bandwidth and reprocessing on both the server and client, as only the header data must be sent and received in comparison to the entirety of the page being re-processed by the server, then sent again using more bandwidth of the server and client.

#### 307 (Temporary Redirect)

A 307 response indicates that the REST API is not going to process the client’s request. Instead, the client should resubmit the request to the URI specified by the response message’s Location header. However, future requests should still use the original URI.

A REST API can use this status code to assign a temporary URI to the client’s requested resource. For example, a 307 response can be used to shift a client request over to another host.

The Location field SHOULD give the temporary URI in the response. Unless the request method was HEAD, the entity of the response SHOULD contain a short hypertext note with a hyperlink to the new URI(s). If the 307 status code is received in response to a request other than GET or HEAD, the user agent MUST NOT automatically redirect the request unless the user can confirm it since this might change the conditions under which the request was issued.

#### 400 (Bad Request)

400 is the generic client-side error status, used when no other 4xx error code is appropriate. Errors can be malformed request syntax, invalid request message parameters, deceptive request routing, etc.

The client SHOULD NOT repeat the request without modifications.

#### 401 (Unauthorized)

A 401 error response indicates that the client tried to operate on a protected resource without providing the proper authorization. It may have provided the wrong credentials or none at all. The response must include a WWW-Authenticate header field containing a challenge applicable to the requested resource.

The client MAY repeat the request with a suitable Authorization header field. If the request already included Authorization credentials, then the 401 response indicates that authorization has been refused for those credentials. If the 401 response contains the same challenge as the prior response, and the user agent has already attempted authentication at least once, then the user SHOULD be presented the entity that was given in the response since that entity might include relevant diagnostic information.

#### 403 (Forbidden)

A 403 error response indicates that the client’s request is formed correctly, but the REST API refuses to honor it, i.e., the user does not have the necessary permissions for the resource. A 403 response is not a case of insufficient client credentials; that would be 401 (“Unauthorized”).

Authentication will not help, and the request SHOULD NOT be repeated. Unlike a 401 Unauthorized response, authenticating will make no difference.

#### 404 (Not Found)

The 404 error status code indicates that the REST API can’t map the client’s URI to a resource but may be available in the future. Subsequent requests by the client are permissible.

No indication is given of whether the condition is temporary or permanent. The 410 (Gone) status code SHOULD be used if the server knows, through some internally configurable mechanism, that an old resource is permanently unavailable and has no forwarding address. This status code is commonly used when the server does not wish to reveal exactly why the request has been refused or when no other response is applicable.

#### 405 (Method Not Allowed)

The API responds with a 405 error to indicate that the client tried to use an HTTP method that the resource does not allow. For instance, a read-only resource could support only GET and HEAD, while a controller resource might allow GET and POST, but not PUT or DELETE.

A 405 response must include the Allow header, which lists the HTTP methods that the resource supports. For example:

Allow: GET, POST

#### 406 (Not Acceptable)

The 406 error response indicates that the API is not able to generate any of the client’s preferred media types, as indicated by the Accept request header. For example, a client request for data formatted as  `application/xml`  will receive a 406 response if the API is only willing to format data as  `application/json`.

If the response is unacceptable, a user agent SHOULD temporarily stop receiving more data and query the user for a decision on further actions.

#### 412 (Precondition Failed)

The 412 error response indicates that the client specified one or more preconditions in its request headers, effectively telling the REST API to carry out its request only if certain conditions were met. A 412 response indicates that those conditions were not met, so the API sends this status code instead of carrying out the request.

#### 415 (Unsupported Media Type)

The 415 error response indicates that the API is not able to process the client’s supplied media type, as indicated by the Content-Type request header. For example, a client request including data formatted as  `application/xml`  will receive a 415 response if the API is only willing to process data formatted as  `application/json`.

For example, the client uploads an image as image/svg+xml, but the server requires that images use a different format.

#### 500 (Internal Server Error)

500 is the generic REST API error response. Most web frameworks automatically respond with this response status code whenever they execute some request handler code that raises an exception.

A 500 error is never the client’s fault, and therefore, it is reasonable for the client to retry the same request that triggered this response and hope to get a different response.

The API response is the generic error message, given when an unexpected condition was encountered and no more specific message is suitable.

#### 501 (Not Implemented)

The server either does not recognize the request method, or it cannot fulfill the request. Usually, this implies future availability (e.g., a new feature of a web-service API).

---
