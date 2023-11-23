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