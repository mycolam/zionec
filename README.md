# zionec
create new


How to configure the environment to run allure reports on the local computer
========================================================================================
0. clear folder <Project>\target\allure-results  in project
1. run all tests or the one you need (wait for end)
2. go to folder with Certifid-Test project
3. type cmd in address bar of explorer and press enter
4. execute: mvn site
   or (depends where maven in your computer):  "C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2017.1.2\plugins\maven\lib\maven3\bin\mvn.cmd" site
5. execute: mvn -Djetty.port=8081 jetty:run
   or (depends where maven in your computer): :"C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2017.1.2\plugins\maven\lib\maven3\bin\mvn.cmd" -Djetty.port=8081 jetty:run
6. goto http://localhost:8081