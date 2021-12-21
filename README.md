*นาย ณัฐกานต์ เตชะวงศ์ 6210450067

*Project schedule

*สิ้นเดือน กุมภาพันธ์

*    สร้างหน้า View ของ general กับ งานของ GeneralBasicController

*สัปดาห์ที่ 1 ของเดือน มีนาคม

*    ทำหน้า View ของ general กับ project, และเพิ่มงานใน GeneralBasicController

*สัปดาห์ที่ 2 ของเดือน มีนาคม

*    สร้างหน้า GeneralReadWrite , ProjectGeneralReadWrite 
     
*    ทำหน้า GeneralBasicController , ProjectController
     
*    ทำโมเดล DetailWeekly

*สัปดาห์ที่ 3 ของเดือน มีนาคม

*    ตั้งเงื่อนไข GeneralBasicController จนครบ
     
*    ทำโมเดล DetailForward พร้อมกับเชื่อมโยงหน้าโปรแกรม
     
*    ทำหน้า WeeklyController 

*สัปดาห์ที่ 4 ของเดือน มีนาคม

*    ทำโมเดล DetailCategory
     
*    ทำ controller ของ AllListController, CategoryController, Manual PDF
     
*    AllListController ตั้งเงื่อนไขการทำการโชว์ตารางทั้งหมดของงานทุกประเภท
     
*    CategoryController ทำตารางแสดงหมวดหมู่ของการ Create Category และดักเงื่อนไขต่างๆ
     
*    Manual PDF ทำหน้าเชื่อมปุ่มเพื่อไปเปิดไฟล์ pdf 
     
*    ทำ services PDFFileDataSource, CategoryReadWrite เพื่อให้มาทำการอ่านไฟล์
     
*    ทำการดักเงื่อนไข model และ controller ทั้งหมด

*สัปดาห์ที่ 4 ของเดือน เมษายน (แก้ไขงานตามความเห็นในการปรับปรุงงาน)

*    ได้มีการปรับขนาดของหน้าจอโปรแกรม และไอคอนต่างๆใหม่ทั้งหมด

*    กำหนดการใช้ font ให้เป็น font system พื้นฐาน

*    ทำให้มีการ Sorting priority ของงาน ผู้ใช้จะได้เล็งเห็นถึงงานที่สำคัญได้ก่อนมาเป็นลำดับแรก


---------------------------------------------------------------------------------------------

*การวางโครงสร้างไฟล์

*    directory data
     
*            - เก็บไฟล์ category.csv เก็บข้อมูลของหมวดหมู่
  
*            - เก็บไฟล์ forward.csv เก็บข้อมูลของงานประเภทส่งต่อ
  
*            - เก็บไฟล์ general.csv เก็บข้อมูลของงานประเภททั่วไป
  
*            - เก็บไฟล์ project.csv เก็บข้อมูลของงานประเภทโครงการ
  
*            - เก็บไฟล์ weekly.csv เก็บข้อมูลของงานประเภทประจำสัปดาห์
  
*    directory PDFFile
     
*            - เก็บ pdf file
  
*    directory src
     
*                main (แบ่งเป็น 2 ส่วน)
  
*                    java (แบ่งเป็น 1 ส่วน)

*                        timetable (แบ่งเป็น 4 ส่วน)
  
*                           controllers
  
*                               - AllListController
  
*                               - CategoryController
  
*                               - CreatorController  
  
*                               - ForwardController
  
*                               - ForwardList
  
*                               - GeneralBasicController
  
*                               - GeneralList
  
*                               - HomeController
  
*                               - ProjectController
  
*                               - ProjectList

*                               - StartController

*                               - WeeklyController

*                               - WeeklyList
  
*                           models
  
*                               - DetailCategory
  
*                               - DetailForward
  
*                               - DetailGeneralBasic
  
*                               - DetailProject
  
*                               - DetailWeekly

*                               - WorkList
  
*                           services
  
*                               - CategoryReadWrite
  
*                               - DataSource
  
*                               - ForwardReadWrite
  
*                               - GeneralReadWrite
  
*                               - PDFFileDataSource
  
*                               - ProjectReadWrite
  
*                               - StringConfig
  
*                               - WeeklyReadWrite
  
*                           - Main
  
*                        resource (แบ่งเป็น 1 ส่วน)
  
*                           images  
  
*                               - Background.png
  
*                               - Background 2.png
  
*                               - Background 3.png

*                               - Background 4.png
  
*                               - Background 5.png

*                               - Background 6.png
  
*                               - Welcome to this Application.png

*                               - your_image.jpg
  
*                           - all List.fxml
  
*                           - category.fxml
  
*                           - creator.fxml
  
*                           - forward.fxml

*                           - forward List.fxml
  
*                           - general.fxml
  
*                           - general List.fxml
  
*                           - home.fxml

*                           - project.fxml

*                           - project List.fxml
  
*                           - start.fxml
  
*                           - weekly.fxml
  
*                           - weekly List.fxml

--------------------------------------------------------------------------------

*วิธีการติดตั้งโปรแกรม

*ตัวโปรแกรม .jar จะอยู่ใน directory TimeTable Application

*กรณีที่ไม่สามารถเปิดโปรแกรมได้จากการ double click ที่ jav file ให้เลือกเปิด terminal หรือ bash หรือ command prompt แล้วใช้คำสั่ง java -jar 6210450067.jar
