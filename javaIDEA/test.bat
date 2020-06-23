@echo off

cd MyWebTester
cd out
cd artifacts
cd MyWebTester_jar
java -jar -Xms4000m -Xmx4000m -Xmn2000m MyWebTester.jar
@pause
