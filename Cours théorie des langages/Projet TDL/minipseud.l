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
var   [a-zA-Z]+
real      {int}("."{int})?{exp}?
fonction {var}\(
 		 

%%

{empty}  { /* Nothing */ }

 

"print"	{yylval.node = createNode(NTPRINT);return (PRINT);}

{var}    {
	yylval.node = createNode(NTVAR);
	yylval.node->var = strdup(yytext);
	return(VAR);
	}
	
{real}    {
    yylval.node = createNode(NTNUM);
    yylval.node->val = atof(yytext);
    return(NUM);
    }

","	  {return (VIRG);}
"("   {return(OP_PAR);}
")"   {return(CL_PAR);}
"+"   { yylval.node = createNode(NTPLUS); return(PLUS); }
"-"   { yylval.node = createNode(NTMIN); return(MIN); }
"*"   { yylval.node = createNode(NTMULT); return(MULT); }
"/"   { yylval.node = createNode(NTDIV); return(DIV); }
"^"   { yylval.node = createNode(NTPOW); return(POW); }
"<"   { yylval.node = createNode(NTPPETIT); return(PPETIT); }
"<="   { yylval.node = createNode(NTPPETITEG); return(PPETITEG); }
">="   { yylval.node = createNode(NTPGRANDEG); return(PGRANDEG); }
">"   { yylval.node = createNode(NTPGRAND); return(PGRAND); }
"=="  { yylval.node = createNode(NTEQU); return (EQU);}
"="   { yylval.node = createNode(NTAFF); return(AFF); }
"if(" { yylval.node = createNode(NTIF); return (IF); }
"if(\n" { yylval.node = createNode(NTIF); return (IF); }
"#for(" { yylval.node = createNode(NTFOR); return (FOR); }
"#for(\n" { yylval.node = createNode(NTFOR); return (FOR); }
"){"  { yylval.node = createNode(NTTRAIT_OP); return (TRAIT_OP);}
"){\n"  { yylval.node = createNode(NTTRAIT_OP); return (TRAIT_OP);}
")\n{\n"  { yylval.node = createNode(NTTRAIT_OP); return (TRAIT_OP);}
")\n{"  { yylval.node = createNode(NTTRAIT_OP); return (TRAIT_OP);}
"\n){\n"  { yylval.node = createNode(NTTRAIT_OP); return (TRAIT_OP);}
"\n){"  { yylval.node = createNode(NTTRAIT_OP); return (TRAIT_OP);}
"}"	  { yylval.node = createNode(NTTRAIT_CL); return (TRAIT_CL);}
"\n}"	  { yylval.node = createNode(NTTRAIT_CL); return (TRAIT_CL);}
"!="  { yylval.node = createNode(NTNEQU); return (NEQU);} 

";"    { return(COLON); }

