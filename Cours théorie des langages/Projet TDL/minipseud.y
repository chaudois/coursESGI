%{
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "minipseudtree.h"
#include "minipseudeval.h"

extern int  yyparse();
extern FILE *yyin;


Node root;
 


%}

%union {
	struct Node *node;

}


%token   <node> NUM VAR
%token   <node> PLUS MIN MULT DIV POW 
%token   OP_PAR CL_PAR COLON VIRG
%token   EOL
%token   <node> IF
%token   <node> FOR
%token   <node> EQU NEQU
%token   <node> TRAIT_OP TRAIT_CL
%token   <node> AFF
%token   <node> FCT
%token   <node> FCTAFF
%token   <node> FCTVAR
%token   <node> PRINT
%token   <node> PPETITEG
%token   <node> PPETIT
%token   <node> PGRANDEG
%token   <node> PGRAND

%type   <node> Instlist
%type   <node> Inst
%type   <node> Expr
%type   <node> Varlist

  

%left GT LT GET LET
%left PLUS  MIN
%left MULT  DIV
%left NEG NOT
%left AFF
%left PRINT
%left PPETIT
%left PGRAND
%left PPETITEG
%left PGRANDEG
%right  POW

%left OR
%left AND
%left EQU NEQU
%left IF

%start Execution
%%
Execution:
  Instlist  {exec($1);}

Instlist:
    Inst { $$=$1;  } 
  |  Inst Instlist  { $$ = nodeChildren(createNode(NTINSTLIST), $1, $2); }
  ;

Inst:
      Expr COLON { $$ = $1; }
    |PRINT Expr  {$$=nodeChildren(createNode(NTPRINT),$2,createNode(NTEMPTY));}
    | IF  Expr TRAIT_OP Instlist TRAIT_CL { $$ = nodeChildren($1,$2,$4);}
    |VAR AFF Expr COLON  {$$ = nodeChildren($2,$1,$3);}
    |VAR OP_PAR Varlist TRAIT_OP Instlist TRAIT_CL  {$$=nodeChildren(createNode(NTFCTAFF),nodeChildren(createNode(    NTFCTVAR),$1,$3),$5);}
    |VAR OP_PAR Varlist CL_PAR COLON {$$=nodeChildren(createNode(NTEXFCT),$1,$3);}
    |FOR Expr COLON Inst TRAIT_OP Instlist TRAIT_CL {$$=nodeChildren(createNode(NTFOR),$2,nodeChildren(createNode(NTINSTLIST),$4,$6));}

  ;

Varlist:

  {}
  |VAR   {$$=$1;}
  |VAR VIRG Varlist {$$=nodeChildren(createNode(NTVARLIST),$1,$3);}
  ;
Expr:
    NUM			{ $$ = $1; }
  | Varlist   { $$ = $1; }
  | Expr EQU Expr      { $$ = nodeChildren($2, $1, $3); }
  | Expr PPETIT Expr   { $$ = nodeChildren($2, $1, $3); }
  | Expr PPETITEG Expr { $$ = nodeChildren($2, $1, $3); }
  | Expr PGRAND Expr   { $$ = nodeChildren($2, $1, $3); }
  | Expr PGRANDEG Expr { $$ = nodeChildren($2, $1, $3); }
  | Expr PLUS Expr     { $$ = nodeChildren($2, $1, $3); }
  | Expr MIN Expr      { $$ = nodeChildren($2, $1, $3); }
  | Expr MULT Expr     { $$ = nodeChildren($2, $1, $3); }
  | Expr DIV Expr      { $$ = nodeChildren($2, $1, $3); }
  | MIN Expr %prec NEG { $$ = nodeChildren($1, createNode(NTEMPTY), $2); printf("MIN Expr %prec NEG\n");}
  | Expr POW Expr      { $$ = nodeChildren($1, $2, $3); }

  | OP_PAR Expr CL_PAR { $$ = $2; }
  ;


%%

int exec(Node *node) {
 
  printGraph(node);
  eval(node);
}

 

int yyerror(char *s) {
  printf("%s\n", s);
}

 

int main(int arc, char **argv) {
   if ((arc == 3) && (strcmp(argv[1], "-f") == 0)) {
    
    FILE *fp=fopen(argv[2],"r");
    if(!fp) {
      printf("Impossible d'ouvrir le fichier à executer.\n");
      exit(0);
    }      
    yyin=fp;
    yyparse();
		  
    fclose(fp);
  }  
  exit(0);
}
