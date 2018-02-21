%option noyywrap
%{

#include "minipseud.h"
#include <stdlib.h>
#include "minipseudtree.h"

%}

empty    [ \t]+

num   [0-9]
int    {num}+
exp  [eE][+-]?{int}
var   [a-z]+
real      {int}("."{int})?{exp}?



%%

{empty}  { /* Nothing */ }

 

"("   return(OP_PAR);
")"   return(CL_PAR);

{var}    {
    yylval.node = createNode(NTVAR);
    yylval.node->var = (char*)_strdup(yytext);
    return(VAR);
    }
	
{real}    {
    yylval.node = createNode(NTNUM);
    yylval.node->val = atof(yytext);
    return(NUM);
    }

"+"   { yylval.node = createNode(NTPLUS); return(PLUS); }
"-"   { yylval.node = createNode(NTMIN); return(MIN); }
"*"   { yylval.node = createNode(NTMULT); return(MULT); }
"/"   { yylval.node = createNode(NTDIV); return(DIV); }
"^"   { yylval.node = createNode(NTPOW); return(POW); }
"="   { yylval.node = createNode(NTAFF); return(AFF); }
"IF(" { yylval.node = createNode(NTIF); return (IF); }
"){"  { yylval.node = createNode(NTTRAIT_OP); return (TRAIT_OP);}
"}"	  { yylval.node = createNode(NTTRAIT_CL); return (TRAIT_CL);}
"=="  { yylval.node = createNode(NTEQU); return (EQU);}
"!="  { yylval.node = createNode(NTNEQU); return (NEQU);} 

";"    { return(COLON); }

"\n"   { return(EOL); }
