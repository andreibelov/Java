@echo off
SETLOCAL
if not "%COMPILE_TARGET%" == "" goto gotCompileTarget
set COMPILE_TARGET=..\out

:gotCompileTarget
if not "%SRC_ROOT%" == "" goto gotSrcRoot
set SRC_ROOT=src\main\java

:gotSrcRoot
if not "%MAIN_CLASS_PATH%" == "" goto gotLibFldr
set MAIN_CLASS_PATH=%SRC_ROOT%\ru\belov\javase01\t06\Main.java

:gotLibRoot
if not "%LIB_CLASS_PATH%" == "" goto gotMainClass
set LIB_CLASS_PATH=..\lib\annotations-15.0.jar

:gotMainClass
javac -encoding utf8 -d %COMPILE_TARGET% -cp %LIB_CLASS_PATH% %MAIN_CLASS_PATH%
ENDLOCAL