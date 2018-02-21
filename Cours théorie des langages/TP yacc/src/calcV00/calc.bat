del calc.lex.c
del calc.exe
del calc.lex.o
del calc.h
del calc.y.c
del calc.y.o

pause
bison -d calc.y
rename calc.tab.h calc.h
rename  calc.tab.c calc.y.c
flex calc.l
rename  lex.yy.c calc.lex.c
gcc -c calc.lex.c -o calc.lex.o
gcc -c calc.y.c -o calc.y.o
gcc -o calc calc.lex.o calc.y.o
calc
