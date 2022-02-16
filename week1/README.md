# Week1 Project

## สรุป Flow
flow นี้จะแสดงเฉพาะข้อมูลที่ user เห็นเท่านั้น โดยละ implementation detail อื่น ๆ เอาไว้

**1. login ด้วย _username: `santhapon` password: `Admin1234`_**

**2. ค้นหา product ด้วย keyword: `NMD` ได้ผลลัพธ์ดังนี้**

| name                                | price    |
|-------------------------------------|----------|
| Adidas NMD R1 Pimeknit Core Black   | 9900.00  |
| Adidas NMD R1 PK Japan Triple Black | 12900.00 |
| POCA SHOE NMD Sneakers Fashion      | 399.00   |
| Adidas NMD R1 Color Core Black      | 7990.00  |

**3. เลือกดู product `POCA SHOE NMD Sneakers Fashion` ได้ผลลัพธ์ดังนี้**

| field       | value                                                           |
|-------------|-----------------------------------------------------------------|
| name        | POCA SHOE NMD Sneakers Fashion - Sport Unisex - PSN-Black/White |
| description | These are the best shoes.                                       |
| brand       | Poca Shoes                                                      |
| occasion    | Casual                                                          |
| price       | 399.00                                                          |

**4. เพิ่มลงในตะกร้า**

**5. เรียกดูข้อมูลสินค้าในตะกร้า ได้ผลลัพธ์ดังนี้**

```json
{
  "products": [
    {
      "name": "POCA SHOE NMD Sneakers Fashion - Sport Unisex - PSN-Black/White",
      "quantity": 1,
      "price": 399.00
    }
  ],
  "totalPrice": 399.00
}
```

**6. เลือกชำระสินค้า**

**7. ระบบ load ที่อยู่ที่จะจัดส่ง default ขึ้นมาแสดง เป็นดังนี้**

| field     | value                            |
|-----------|----------------------------------|
| fullName  | Santhapon Sripilaipong           |
| address   | Somewhere in Thailand            |
| postCode  | 12345                            |
| district  | Somewhere                        |
| province  | Krung Thep Maha Nakhon (Bangkok) |
| phone     | 0999999999                       |

**8. ดำเนินการต่อด้วย default address นั้น**

**9. ระบบ load ตัวเลือกการชำระเงิน default ขึ้นมาแสดง เป็นดังนี้**

| field         | value                  |
|---------------|------------------------|
| method        | creditCard             |
| ownerName     | Santhapon Sripilaipong |
| cardNumber    | 1111222233334444       |
| expire        | 11/12                  |
| cvv           | 999                    |

**10. สั่งซื้อสินค้าด้วยตัวเลือกชำระเงิน default นั้น**

**11. เรียกดูสรุปการชำระเงิน ได้ผลลัพธ์ดังนี้**

| field  | value                  |
|--------|------------------------|
| payer  | Santhapon Sripilaipong |
| amount | 399.00                 |
