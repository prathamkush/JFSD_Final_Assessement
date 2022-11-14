<h2> SERVICES </h2>

1. **_Eureka Server_**             -> `Central Eureka Server :8761` 

2. **_admin-service_**             -> `For signup-login : (User table -> MongoDB) :8081` 

3. **_token-service_**             -> `For token generation : 8080`

4. **_ShopManagement-service_**    -> `For Products/Category table (n:n relationship) (h2 Database), 
                                      Authorization Token required through consumer (login credentials),
                                      can add products, categories, link products with categories
                                      : 8083`

5. **_shop-service_**              -> `Proxied shopManagement-service into this,
                                      Public api endpoints for getting products and categories,
                                      Also searching with manual fields of products and categories
                                      eg: get product by name, id, category_name, description etc;
                                      and get category by name, id, description etc 
                                      8084`

6. **_consumer-service_**          -> `Proxied admin-service, token-service, shopManagement-service into this
                                      Generates JwtToken from userId at the time of login, use to filter out 
                                      shopManagement-service API endpoints. Only after getting token after login,
                                      can a user update the details in products and categories table.
                                      : 8082`

<h4> Added Unit Tests & Exceptions in -> admin-service (for User login, signup), shopManagement-service (for product-category) </h4></h4>



<h4> Api Endpoints IN CONSUMER</h4>

-> for admin-service
        
        -> SIGNUP

            Method: POST

            Endpoint: consumer/signup

            Payload:

            {   
                "username": "USERNAME", 
                "password": "PASSWORD", 
                "confirm_password": "PASSWORD", 
                "email": "OPTIONAL_EMAIL" 
            }
        
        -> LOGIN
            Method: POST
            Endpoint: consumer/login
            Payload:

            { 
                "username": "USERNAME", 
                "password": "PASSWORD" 
            }
        
        (for testing)
        -> GET Users
            Method: GET
            Endpoint: consumer/get-users
    
    
-> for Token-service
        
    ->  GET TOKEN
        Method: GET
        Endpoint: consumer/get-token/{id}

-> for ShopManagement  (requires jwt token)
    ADD this in HEADERS -> "Authorization" : token
    
    (for testing)
    ->  GET categories
        Method: GET
        Endpoint: consumer/shop-management/categories
    
    ->  ADD category
        Method: POST
        Endpoint: consumer/shop-management/add-category
        PAYLOAD : { "name": "value", description": "value" }
    
    ->  UPDATE category by ID
        Method: POST
        Endpoint: consumer/shop-management/update-category-by-id/{id}
        PAYLOAD : { “FIELD_NAME” : VALUE }
        allowed fields -> description OR name
    
    ->  DELETE category by ID
        Method: DELETE
        Endpoint: consumer/shop-management/delete-category-by-id/{id}
    
    (for testing)
    ->  GET Products 
        Method: GET
        Endpoint: consumer/shop-management/categories
    
    ->  ADD PRODUCT
        Method: POST
        Endpoint: consumer/shop-management/add-product
        PAYLOAD : { "name": "value", "price": "value", "description": "value" }
    
    ->  ADD PRODUCTS
        METHOD: POST
        Endpoint: consumer/shop-management/add-products
        PAYLOAD : List of products -> see sample-products.json
    
    ->  LINK PRODUCT WITH CATEGORY
        METHOD: PUT
        Endpoint: consumer/shop-management/product-with-category/{product_id}/{category_id}
    
    ->  UPDATE PRODUCT by ID
        Method: POST
        Endpoint: consumer/shop-management/update-product-by-id/{id}
        PAYLOAD : { “FIELD_NAME” : VALUE }
        allowed fields ->  description OR name OR id OR price 
    
    ->  DELETE PRODUCT by ID
        Method: DELETE
        Endpoint: consumer/shop-management/delete-product-by-id/{id}





<h4> Api Endpoints IN SHOP (public endpoints) (IN SHOP-SERVICE)</h4>

    ->  GET categories
        Method: GET
        Endpoint: shop/categories

    ->  GET Products
        Method: GET
        Endpoint: shop/products
    
    ->  GET PRODUCT BY FIELD
        Method: POST
        Endpoint: shop/products
        Payload: { "FIELD_NAME": "VALUE" }
        allowed fields : description OR name OR id OR price OR category_name
    
    ->  GET CATEGORY BY FIELD
        Method: POST
        Endpoint: shop/categories
        Payload: { "FIELD_NAME": "VALUE" }
        allowed fields : description OR name OR id


