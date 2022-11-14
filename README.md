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

<h4> Added Unit Tests & Exceptions in -> admin-service (for User login, signup), shopManagement-service (for product-category)
