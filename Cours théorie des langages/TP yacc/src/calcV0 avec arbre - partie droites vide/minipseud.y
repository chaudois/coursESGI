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


%token   <node> NUM 
%token   <node> PLUS MIN MULT DIV POW
%token   OP_PAR CL_PAR COLON
%token   EOL


%type   <node> Instlist
%type   <node> Inst
%type   <node> Expr
  



%start Input
%%

Input:
      {/* Nothing ... */ }
  | Line Input { /* Nothing ... */ }


Line:
    EOL {  }
  | Instlist EOL { exec($1);    }
  ; 

Instlist:
    Inst {; } 
  | Instlist Inst { ; }
  ;

Inst:
    Expr COLON { $$ = $1; } 
  ;


Expr:
  NUM			{ ; }
    | Expr PLUS Expr     {; }
  | Expr MIN Expr      { ; }
  | Expr MULT Expr     { ; }
  | Expr DIV Expr      { ; }
  | MIN Expr %prec NEG { ; }
  | Expr POW Expr      { ; }
  | OP_PAR Expr CL_PAR { ; }
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
