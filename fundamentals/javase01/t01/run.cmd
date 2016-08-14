chcp 1251 >nul
@echo off
SETLOCAL
if not "%COMPILE_TARGET%" == "" goto gotCompileTarget
set COMPILE_TARGET=..\out

:gotCompileTarget
if not "%MAIN_CLASS%" == "" goto gotMainClass
set MAIN_CLASS=ru.belov.javase01.t01.main.Main

:gotMainClass
java -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Dfile.encoding=UTF8 -cp %COMPILE_TARGET% %MAIN_CLASS% | clip
ENDLOCAL