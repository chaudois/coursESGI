regComment \/\*(.|\n)\*\/|\/\/.*
%%
{regComment}+ {

char*retourReg=yytext;

printf("\ncomment : %s",retourReg);
} 
%%
#include <stdio.h>
#include <string.h>
int main () {
yyin = fopen("in.txt", "r") ;
yylex() ;
return 0;
} 
int yywrap(void) {
return 1;
}


