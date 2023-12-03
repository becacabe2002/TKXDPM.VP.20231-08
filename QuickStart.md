# TKXDPM.VP.20231-08
Capstone Project cho môn học Thiết kế &amp; xây dựng phần mềm, Nhóm Việt Pháp 08

## Sử dụng dbmate để migrate database
### Cài đặt dbmate
- Cài đặt dbmate theo hướng dẫn tại [đây](https://github.com/amacneil/dbmate.git)

### Config dbmate
* Thay đổi địa chỉ DATABASE_URL trong file .env tương ứng
* Chạy các lệnh ở folder root của project để tạo database và các bảng:
    ```bash
    dbmate up
    ```
## Sử dụng mongodb tools để migrate database
### Cài đặt mongodb tools
- Cài đặt mongodb tools theo hướng dẫn tại [đây](https://www.mongodb.com/docs/database-tools/installation/installation/)

### Sử dụng mongodb tools
- Dump dữ liệu từ collection về path .\mongodata
  ```bash
  mongodump --uri="mongodb://localhost:27017/aims_2023" --out=".\mongodata\"
  ```

- Restore dữ liệu từ path .\mongodata vào db và các collection (tạo collection nếu chưa có)
  ```bash
  mongorestore --uri="mongodb://localhost:27017/aims_2023" ".\mongodata\aims_2023"
  ```

(_Thay đổi Uri tương ứng với uri của người dùng_)
