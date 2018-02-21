%option noyywrap
%{

#include "calc.h"
#include <stdlib.h>

%}

blancs    [ \t]+

chiffre   [0-9]
entier    {chiffre}+
exposant  [eE][+-]?{entier}

reel    {entier}("."{entier})?{exposant}?
lettre [a-z]
variable  [a-zA-Z]+

%%

{blancs}  { /* On ignore */ }

{reel}    {
	yylval.val=atof(yytext);
	return(NOMBRE);
    }

{variable}    {
	yylval.var=(char*)_strdup(yytext);
	return(VARIABLE);
    }
{lettre}	{
	yylval.var=(char*)_strdup(yytext);
	return(LETTRE);
	}
"+"   return(PLUS);
"-"   return(MOINS);

"*"   return(FOIS);
"/"   return(DIVISE);

"^"   return(PUISSANCE);
"="   return(EQUAL);
"("   return(PARENTHESE_GAUCHE);
")"   return(PARENTHESE_DROITE);

"\n"  return(FIN);
