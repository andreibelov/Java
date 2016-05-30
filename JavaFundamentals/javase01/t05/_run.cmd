chcp 65001
@echo off
SETLOCAL
if not "%COMPILE_TARGET%" == "" goto gotCompileTarget
set COMPILE_TARGET=..\out

:gotCompileTarget
if not "%MAIN_CLASS%" == "" goto gotMainClass
set MAIN_CLASS=ru.belov.javase01.t05.Main

:gotMainClass
java -Dfile.encoding=UTF8 -cp %COMPILE_TARGET% %MAIN_CLASS%
ENDLOCAL
pause
exit