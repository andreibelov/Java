chcp 1251
@echo off
SETLOCAL
if not "%COMPILE_TARGET%" == "" goto gotCompileTarget
set COMPILE_TARGET=..\out
:gotCompileTarget

if not "%MAIN_CLASS%" == "" goto gotLibClass
set MAIN_CLASS=ru.belov.javase01.t06.Main
:gotLibRoot

if not "%LIB_CLASS_PATH%" == "" goto gotMainClass
set LIB_CLASS_PATH=..\lib\annotations-15.0.jar
:gotMainClass

java -Dfile.encoding=UTF8 -cp \"%LIB_CLASS_PATH%;%COMPILE_TARGET%\" %MAIN_CLASS%
ENDLOCAL
pause
exit