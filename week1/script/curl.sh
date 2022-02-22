#!/bin/bash

#### 1. login เข้าระบบ และ store Authorization Token
AUTH_TOKEN=$(curl -X POST http://localhost:8080/login \
  -H 'Content-Type: application/json' \
  -d '{"username": "Santhapon", "password": "Admin1234"}' \
  | grep -Po 'Bearer \K[^"]*')

#### 2. ค้นหา product ด้วย keyword
curl -X GET http://localhost:8080/products?keyword=NMD

#### 3. เลือกรายละเอียดของ product (id=2)
curl -X GET http://localhost:8080/products/2

#### 4. เพิ่ม product (id=2) ลงในตะกร้า 1 ชิ้น
curl -X POST http://localhost:8080/cart/items \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer '$AUTH_TOKEN \
  -d '{"productId": 2, "quantity": 1}'

#### 5. ดูสรุปสินค้าในตะกร้า
curl -X GET http://localhost:8080/cart \
  -H 'Authorization: Bearer '$AUTH_TOKEN

#### 6. load ที่อยู่จัดส่ง default มาแสดง
ADDRESS_ID=$(curl -X GET http://localhost:8080/delivery/address/default \
  -H 'Authorization: Bearer '$AUTH_TOKEN \
  | grep -Po 'id": *\K[^,]*')

#### 7. เลือกใช้ที่อยู่จัดส่ง default สำหรับตะกร้านี้
curl -X PUT http://localhost:8080/cart/address \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer '$AUTH_TOKEN \
  -d '{"addressId": '$ADDRESS_ID'}'

#### 8. load ตัวเลือกการชำระเงิน default ขึ้นมาแสดง และ store Payment ID
PAYMENT_ID=$(curl -X GET http://localhost:8080/payment/method/default \
  -H 'Authorization: Bearer '$AUTH_TOKEN \
  | grep -Po 'id": *\K[^,]*')

#### 9. เลือกใช้ตัวเลือกการชำระเงิน default สำหรับตะกร้านี้
curl -X PUT http://localhost:8080/cart/paymentMethod \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer '$AUTH_TOKEN \
  -d '{"paymentMethodId": '$PAYMENT_ID'}'

#### 10. สรุปตะกร้า
curl -X GET http://localhost:8080/cart \
  -H 'Authorization: Bearer '$AUTH_TOKEN
