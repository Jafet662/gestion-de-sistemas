@echo off
setlocal
cd /d %~dp0
where mvn >nul 2>&1
if errorlevel 1 (
  echo Maven no encontrado. Ejecuta desde NetBeans con Run Project.
  exit /b 1
)
mvn -q -DskipTests package
if errorlevel 1 (
  echo Fallo el build.
  exit /b 1
)
set JAR=target\usuario-sistema-1.0.0-shaded.jar
if not exist "%JAR%" set JAR=target\usuario-sistema-1.0.0.jar
if not exist "%JAR%" (
  echo No se encontro el JAR.
  dir /b target
  exit /b 1
)
java -jar "%JAR%"
endlocal

