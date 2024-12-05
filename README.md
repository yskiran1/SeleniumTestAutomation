# SeleniumTestAutomation
to-do url automation

Steps to Execute :-

1) Import project in eclipse/IntelliJ with JDK configuration.

2) Configure chromedriver as per your local chrome browser version.

3) Update chromedriver path in ToDoMVCTesting class Line 20.

4) Configure the library folder jars in project execution path.

   a) IntelliJ Steps for lib configuration.
   b) Select on project -> project structure -> dependencies -> add dependency -> add jar -> select all jars in src/lib files -> click on apply -> click on OK button.
   
5) configuration of testng file execution 
   Run/Debug configurations -> add TestNG -> enter any name under text field of name -> TestKind as class -> class map as "com.pages.ToDoMVCTesting" -> click on OK button.
   
6) Run TestNG class of ToDoMVCTesting from Run configuration.

7) You can see automation output as followed in recorded video output as well.
