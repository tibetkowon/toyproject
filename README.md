#간단한 주문 서비스

###1. entity
* Restaurant 와 Order 를 만들 때, 각각의 공통점인 id, 생성일, 수정일, 삭제여부 를
BaseEntity 로 만들어 식당과 주문에 extend.
* 삭제 시, 실제로 데이터가 삭제되는 것 보다는, 로그성 데이터로 남겨두는 것이 좋다고 생각되어
삭제 여부 컬럼을 만들어 데이터를 관리

>  * BaseEntity
>    ```java
>    @MappedSuperclass
>    @EntityListeners(AuditingEntityListener.class)
>    public class BaseEntity {
>        @Id
>        @GeneratedValue(strategy = GenerationType.IDENTITY)
>        private Long id;
>
>        @CreatedDate 
>        @Column(updatable = false)
>        private LocalDateTime createdAt = LocalDateTime.now();
>
>        @LastModifiedDate
>        private LocalDateTime modifiedAt = LocalDateTime.now();
>
>        @Column(name = "IS_DEL", length = 1)
>        private boolean deleteFlag;
>
>        public void isDel() {
>            this.deleteFlag = true;
>        }
>    }
>    ```

* 식당의 경우 식당명이 동일 중복 될 수 없도록, 식당명 컬럼에 uniqueKey 제약 조건 생성
> * Restaurant
>   ```java
>   @Entity
>   @Table(name = "TBL_RESTAURANT", uniqueConstraints = {@UniqueConstraint(name = "UK_RESTAURANT_NAME", columnNames = {"NAME"})})
>   @Where(clause = "IS_DEL = 'N'")
>   public class Restaurant extends BaseEntity{
>   
>     //컬럼 생략
>   
>       public void modify(Restaurant restaurant){
>             if(restaurant.getName() != null && !restaurant.getName().isEmpty()){
>                 this.name = restaurant.getName();
>             }
>             if(restaurant.getAddress() != null && !restaurant.getAddress().isEmpty()){
>                 this.address = restaurant.getAddress();
>             }
>             if(restaurant.getPhoneNum() != null && !restaurant.getPhoneNum().isEmpty()){
>                 this.phoneNum = restaurant.getPhoneNum();
>             }
>       }
>   }
>   ```
 
> * Order
>     ```java
>     @Entity
>     @Table(name = "TBL_ORDER")
>     @Where(clause = "IS_DEL='N'")
>     public class Order extends BaseEntity {
> 
>         @Column(name = "CUSTOMER_NAME")
>         private String customerName;
> 
>         @ManyToOne(fetch = FetchType.LAZY)
>         @JoinColumn(name = "RESTAURANT_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_RESTAURANT_ID"))
>         private Restaurant restaurant;
>     }
>     ```
***
###2. controller
 * Enum으로 응답 코드와 메시지를 만들어, ResultEntity 에 해당 코드와 메시지, 데이터를 response.
>   * ResultEntity
>   ```java
>       public class ResultEntity<T> {
> 
>        private String code = ResponseCode.OK.getCode();
>        private String message = ResponseCode.OK.getMessage();
> 
>        private T data;
> 
>        public ResultEntity(T data){
>            this.data = data;
>        }
> 
>        public ResultEntity(ResponseCode responseCode){
>            this.code = responseCode.getCode();
>            this.message = responseCode.getMessage();
>        }
>    }
>  ```

* 식당 정보를 response 할때, `deleteFlag` 와 같은, 안보여도 될 정보를 걸러내기 위한 ResultRestaurant
>    * ResultRestaurant
>  ```java
>    public class ResultRestaurant {
>        private Long id;
>        private LocalDate createDate;
>        private String name;
>        private String address;
>        private String phoneNumber;
>
>        public ResultRestaurant(Restaurant restaurant){
>            this.id = restaurant.getId();
>            this.createDate = restaurant.getCreatedAt().toLocalDate();
>            this.name = restaurant.getName();
>            this.address = restaurant.getAddress();
>            this.phoneNumber = restaurant.getPhoneNum();
>        }
>    }
>    ```

* 주문 생성 시, 식당 id 와 주문자명 을 받을 InsertOrder
* `@Notnull` , `@NotEmpty` 로 유효성체크
>  * InsertOrder
>  ```java
>    public class InsertOrder {
>        @NotEmpty(message = "ORDER_NO_CUSTOMER_NAME")
>        private String customerName;
>
>        @NotNull(message = "ORDER_NO_RESTAURANT")
>        private Long restaurantId;
>    }
>    ```

* 주문 정보 response 시, 주문과 식당 관련 필요한 정보만 거르기 위한 ResultOrder
>  * ResultOrder
>  ```java
>    public class ResultOrder {
>        private Long orderId;
>        private String customerName;
>        private String orderTime;
>        private String restaurantName;
>        private String restaurantPhone;
>        private String restaurantAddress;
>
>        public ResultOrder(Order order){
>            this.orderId = order.getId();
>            this.customerName = order.getCustomerName();
>            this.orderTime = order.getCreatedAt().format(DateTimeFormatter.ofPattern("YYYY-mm-dd HH:MM:ss"));
>            this.restaurantName = order.getRestaurant().getName();
>            this.restaurantPhone = order.getRestaurant().getPhoneNum();
>            this.restaurantAddress = order.getRestaurant().getAddress();
>        }
>    }
>    ```

* 식당
  * 식당 생성 : POST /restaurant
    > Body
    > ```json
    > {
    >  "name": {이름},
    >  "address": {주소},
    >  "phoneNum": {전화번호}
    > }
    > ```

  * 식당 전체 조회 : GET /restaurant
  
  * 식당 조회 : GET /restaurant/{식당 ID}

  * 식당 수정 : PUT /restaurant/{식당 ID} 
      > Body
      > ```json
      > {
      >  "name": [이름],
      >  "address": [주소],
      >  "phoneNum": [전화번호]
      > }
      > ```
  * 식당 삭제 : DELETE /restaurant/{식당 ID}


* 주문
  * 주문 생성 : POST /order
  > Body
  > ```json
  > {
  >  "customerName": {고객명},
  >  "restaurantId": {식당ID}
  > }
  > ```
  * 주문 전체 조회 : GET /order
  * 식당의 주문 조회 : GET /order/{식당ID}
  * 식당 삭제 : DELETE /order/{주문ID}
  
***
###3. service
  * 식당 삭제 시, 해당 식당의 주문들 먼저 삭제 후, 식당 삭제
  ```java
     public ResultEntity<ResultRestaurant> delete(Long id) {
            Optional<Restaurant> optionalRestaurant = repository.findById(id);

            if(!optionalRestaurant.isPresent()){
                return new ResultEntity<>(ResponseCode.RESTAURANT_NO_RESTAURANT);
            }
            List<Order> orders = orderRepository.findByRestaurantId(id);
            orders.stream().forEach(order -> order.isDel());

            Restaurant restaurant = optionalRestaurant.get();
            restaurant.isDel();
            //repository.delete(restaurant);

            return new ResultEntity<>(new ResultRestaurant(restaurant));
        }   
  ```
***
###4. repository
  * 주문 조회시 fetch join 으로 n+1 방지(?)
  ```java
  public interface OrderRepository extends JpaRepository<Order, Long> {
  @Query("select o from Order o join fetch o.restaurant where o.restaurant.id = ?1")
  List<Order> findByRestaurantId(Long id);

  @Query("select o from Order o join fetch o.restaurant")
  List<Order> findAll();

  @Query("select o from Order o join fetch o.restaurant where o.id = ?1")
  Optional<Order> findById(Long id);
}

```