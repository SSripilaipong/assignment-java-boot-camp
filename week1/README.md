# Week1 Project

- [สรุป Flow](#สรุป-flow)
- [cURL script สำหรับรัน demo](#cURL-script-สำหรับรัน-demo)

## สรุป Flow
flow นี้จะแสดงเฉพาะข้อมูลที่ user เห็นเท่านั้น โดยละ implementation detail อื่น ๆ เอาไว้

#### 1. login ด้วย _username: `santhapon` password: `Admin1234`_
[cURL script](#1-login-เข้าระบบ-และ-store-authorization-token)

#### 2. ค้นหา product ด้วย keyword: `NMD` ได้ผลลัพธ์ดังนี้
[cURL script](#2-ค้นหา-product-ด้วย-keyword)

| name                                | price    |
|-------------------------------------|----------|
| Adidas NMD R1 Pimeknit Core Black   | 9900.00  |
| Adidas NMD R1 PK Japan Triple Black | 12900.00 |
| POCA SHOE NMD Sneakers Fashion      | 399.00   |
| Adidas NMD R1 Color Core Black      | 7990.00  |

#### 3. เลือกดู product `POCA SHOE NMD Sneakers Fashion` ได้ผลลัพธ์ดังนี้
[cURL script](#3-เลือกรายละเอียดของ-product-id2)

| field       | value                                                           |
|-------------|-----------------------------------------------------------------|
| name        | POCA SHOE NMD Sneakers Fashion - Sport Unisex - PSN-Black/White |
| description | These are the best shoes.                                       |
| brand       | Poca Shoes                                                      |
| occasion    | Casual                                                          |
| price       | 399.00                                                          |

#### 4. เพิ่มลงในตะกร้า
[cURL script](#4-เพิ่ม-product-id2-ลงในตะกร้า-1-ชิ้น)

#### 5. เลือกชำระสินค้า ระบบแสดงข้อมูลสินค้าในตะกร้า ได้ผลลัพธ์ดังนี้
[cURL script](#5-ดูสรุปสินค้าในตำกร้า)

```json
{
  "items": [
    {
      "name": "POCA SHOE NMD Sneakers Fashion - Sport Unisex - PSN-Black/White",
      "quantity": 1,
      "price": 399.00
    }
  ],
  "totalPrice": 399.00
}
```

#### 6. ระบบ load ที่อยู่ที่จะจัดส่ง default ขึ้นมาแสดง เป็นดังนี้

| field     | value                            |
|-----------|----------------------------------|
| fullName  | Santhapon Sripilaipong           |
| address   | Somewhere in Thailand            |
| postCode  | 12345                            |
| district  | Somewhere                        |
| province  | Krung Thep Maha Nakhon (Bangkok) |
| phone     | 0999999999                       |

#### 7. ดำเนินการต่อด้วย default address นั้น

#### 8. ระบบ load ตัวเลือกการชำระเงิน default ขึ้นมาแสดง เป็นดังนี้

| field         | value                  |
|---------------|------------------------|
| method        | creditCard             |
| ownerName     | Santhapon Sripilaipong |
| cardNumber    | 1111222233334444       |
| expire        | 11/12                  |
| cvv           | 999                    |

#### 9. สั่งซื้อสินค้าด้วยตัวเลือกชำระเงิน default นั้น

#### 10. เรียกดูสรุปการสั่งซื้อ ได้ผลลัพธ์ดังนี้

```json
{
  "payer": "Santhapon Sripilaipong",
  "items": [
    {
      "name": "POCA SHOE NMD Sneakers Fashion - Sport Unisex - PSN-Black/White",
      "quantity": 1
    }
  ],
  "amount": 399.00,
  "address": "Somewhere in Thailand",
  "cardNumber": "1111222233334444"
}
```

## cURL script สำหรับรัน demo
#### 1. login เข้าระบบ และ store Authorization Token
[ดูรายละเอียด flow](#1-login-ด้วย-username-santhapon-password-admin1234)
```shell
AUTH_TOKEN=$(curl -X POST http://localhost:8080/login \
  -H 'Content-Type: application/json' \
  -d '{"username": "Santhapon", "password": "Admin1234"}' \
  | grep -Po 'Bearer \K[^"]*')
```

#### 2. ค้นหา product ด้วย keyword
[ดูรายละเอียด flow](#2-ค้นหา-product-ด้วย-keyword-nmd-ได้ผลลัพธ์ดังนี้)
```shell
curl -X GET http://localhost:8080/products?keyword=NMD
```

#### 3. เลือกรายละเอียดของ product (id=2)
[ดูรายละเอียด flow](#3-เลือกดู-product-poca-shoe-nmd-sneakers-fashion-ได้ผลลัพธ์ดังนี้)
```shell
curl -X GET http://localhost:8080/products/2
```

#### 4. เพิ่ม product (id=2) ลงในตะกร้า 1 ชิ้น
[ดูรายละเอียด flow](#4-เพิ่มลงในตะกร้า)
```shell
curl -X POST http://localhost:8080/cart/items \
-H 'Content-Type: application/json' \
-H 'Authorization: Bearer $AUTH_TOKEN' \
-d '{"productId": 2, "quantity": 1}'
```

#### 5. ดูสรุปสินค้าในตำกร้า
[ดูรายละเอียด flow](#5-เลือกชำระสินค้า-ระบบแสดงข้อมูลสินค้าในตะกร้า-ได้ผลลัพธ์ดังนี้)
```shell
curl -X GET http://localhost:8080/cart \
-H 'Authorization: Bearer $AUTH_TOKEN'
```
