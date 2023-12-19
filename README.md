# TKXDPM.VN.20231-01

This is a Capstone's source code for Software Design and Construction project

## Team member

| Name           | Role        |
| :------------- | :---------- |
| Ngô Minh Tú    | Team Leader |
| Dương Nhật Thành| Member      |
| Nguyễn Quốc Thành| Member      |

## Report Content

<details>
  <summary>W10: 27/11/2023~03/12/2023 </summary>
<br>
<details>
<summary>Ngô Minh Tú</summary>
<br>

- Assigned tasks:
  - Create tests for specific functions

- Implementation details:
  - Pull Request(s): [Request 1](https://github.com/becacabe2002/TKXDPM.VP.20231-08/pull/3)
  - Specific implementation details:
    - Create specific class for each test (ImageUpload, MediaRepo, WebView)
      - ImageUpload: test ImageRepo to upload and pull image from local to mongodb database (**Deprecated**)
      - MediaRepo: test query actions with Media Table in MySQL database
      - WebView: test view for payment portal (VNPay)

</details>

<details>
<summary>Nguyễn Quốc Thành</summary>
<br>

- Assigned tasks:
  - Làm Screen Handle
  - (Bonus , cần comment để dê hiểu hơn code của repo và controller )
  - ...

- Implementation details:
  - Pull Request(s): [Attach links to your pull requests here. You can attach multiple pull requests]()
  - Specific implementation details:
    - Describe specific in detail what you did last week
    - You can attach images if you want

</details>

</details>

---
<details>
  <summary>W11: 04/12/2023~10/12/2023 </summary>
<br>
<details>
<summary>Ngô Minh Tú</summary>
<br>

- Assigned tasks:
  - migrate db
  - refactor repositories to reduce coupling
  - fix image unable to load error

- Implementation details:
  - [PR1](https://github.com/becacabe2002/TKXDPM.VP.20231-08/pull/4)
    - add migrate tool for mysql database, import sample data
    - create controller package for controller classes
    - disable `@lombok` annotations (error)

  - [PR2](https://github.com/becacabe2002/TKXDPM.VP.20231-08/pull/5)
    - Refactor repo class -> repo interface
    - Seperate implementation of that repo to a different file.
   
  - [PR3](https://github.com/becacabe2002/TKXDPM.VP.20231-08/pull/10)
    - fix error cant load image by fetching and saving image locally (**deprecated**)


</details>

<details>
<summary>Team Member 2</summary>
<br>

- Assigned tasks:
  - Task 1
  - Task 2
  - ...

- Implementation details:
  - Pull Request(s): [Attach links to your pull requests here. You can attach multiple pull requests]()
  - Specific implementation details:
    - Describe specific in detail what you did last week
    - You can attach images if you want

</details>

</details>

---

<details>
  <summary>W12: 11/12/2023~17/10/2023 </summary>
<br>
<details>
<summary>Ngô Minh Tú</summary>
<br>

- Assigned tasks:
  - Change Interaction between Frontend and Backend
  - New way to store images temporary (not by saving to local storage)
  - Seperate `Controllers` and `Services`

- Implementation details:
  - Pull Request(s): [PR](https://github.com/becacabe2002/TKXDPM.VP.20231-08/pull/12)
  - Specific implementation details:
    - Create package response, request, service
    - New class ImageSession for store image temporary while app is running
    - Change interaction between Frontend and Backend:
	    - Now Frontend needs to create request, sends it to controller and handle returned response.


</details>

<details>
<summary>Team Member 2</summary>
<br>

- Assigned tasks:
  - Task 1
  - Task 2
  - ...

- Implementation details:
  - Pull Request(s): [Attach links to your pull requests here. You can attach multiple pull requests]()
  - Specific implementation details:
    - Describe specific in detail what you did last week
    - You can attach images if you want

</details>

</details>

---
