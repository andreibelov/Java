@echo off
SETLOCAL
if not "%COMPILE_TARGET%" == "" goto gotCompileTarget
set COMPILE_TARGET=..\out

:gotCompileTarget
if not "%SRC_ROOT%" == "" goto gotSrcRoot
set SRC_ROOT=src\main\java

:gotSrcRoot
if not "%MAIN_CLASS_PATH%" == "" goto gotMainClass
set MAIN_CLASS_PATH=%SRC_ROOT%\ru\belov\javase01\t06\Main.java

:gotMainClass
javac -encoding utf8 -d %COMPILE_TARGET% -cp %SRC_ROOT% %MAIN_CLASS_PATH%
ENDLOCAL