%{
int charNumber=0;
int wordNumber=1;
int lineNumber=1;
%}


regWord	\s
regLine \n
regChar [^\s|^\n]
%%
{regChar} {
charNumber++;
}
{regWord} {
wordNumber++;	
} 
{regLine} {
wordNumber++;
lineNumber++;	
}
%%
#include <stdio.h>
#include <string.h>
int main () {
yyin = fopen("in.txt", "r") ;
yylex() ;
printf("\nnombre de carctere : %d",charNumber);
printf("\nnombre de mot : %d",wordNumber);
printf("\nnombre de ligne : %d",lineNumber);
return 0;
} 
int yywrap(void) {
return 1;
}