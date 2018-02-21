%{

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int yylex();
int yyparse();
int yyerror();
double pow(double, double);
double variable[26];
%}

%union { 
	double val;
	char* var;
  char lettre;
}

%token  <val> NOMBRE
%token  <var> VARIABLE
%token  <lettre>LETTRE
%token   EQUAL
%token   PLUS  MOINS FOIS  DIVISE  PUISSANCE
%token   PARENTHESE_GAUCHE PARENTHESE_DROITE
%token   FIN

%type   <val> Expression

%left PLUS  MOINS
%left FOIS  DIVISE
%left NEG
%right  PUISSANCE

%start Input
%%

Input:
    /* Vide */
  | Input Ligne
  ;

Ligne:
    FIN
  | Expression FIN    { printf("%f\n",$1); }
  | VARIABLE EQUAL Expression FIN {printf("%c=%f\n",$1[0],$3);variable[$1[0]-97]=$3;}
  ;

Expression:
    NOMBRE      { $$=$1}
  | VARIABLE {$$=variable[((int)$1[0])-97];}
  | Expression PLUS Expression  { $$=$1+$3; }
  | Expression MOINS Expression { $$=$1-$3; }
  | Expression FOIS Expression  { $$=$1*$3; }
  | Expression DIVISE Expression  { $$=$1/$3; }
  | MOINS Expression %prec NEG  { $$=-$2; }
  | Expression PUISSANCE Expression { $$=pow($1,$3); }
  | PARENTHESE_GAUCHE Expression PARENTHESE_DROITE  { $$=$2; }
  ;

%%

int yyerror(char *s) {
  printf("%s\n",s);
}

int main(void) {
  yyparse();
}
