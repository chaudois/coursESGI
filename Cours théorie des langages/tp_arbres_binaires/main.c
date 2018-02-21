#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int yylex();
int yyparse();
int yyerror();
double pow(double, double);
double variable[26];

typedef struct Node {
  enum NodeType type;
  union {
  double val;
  char* var;
  struct Node** children;
  } ;
} Node;

typedef union YYSTYPE
{
  struct Node *node;
} YYSTYPE;

extern YYSTYPE yylval;

Node* createNode(int type) {
  Node* newnode = (Node *) malloc(sizeof(Node));
  newnode->type = type;
  newnode->children = NULL;
  return newnode;
}

Node* nodeChildren(Node* father, Node *child1, Node *child2) {
  father->children = (Node **) malloc(sizeof(Node*) * 2);
  father->children[0] = child1;
  father->children[1] = child2;
  return father;
}

enum NodeType {
  NTNUM = 201,
  NTVAR = 202,
  NTPLUS = 321,
  NTMULT = 323
};

void printGraphRec(Node *node, int decalage){
  if(node->type == NTPLUS){
    printf("+");
  }

  if(node->type == NTMULT){
    printf("*");
  }

  if(node->type == NTNUM){
    printf("%d", node->val);
  }

  if(node->type == NTVAR){
    printf("%d", variable[node->var[0]]);
  }

  if(node->type == NTPLUS || node->type == NTMULT){
    printGraphRec(node->children[0], decalage+1);
    printGraphRec(node->children[1], decalage+2);
  }
}

double evalRec(Node *node){
  if(node->type == NTNUM){
    return node->val;
  }else if(node->type == NTVAR){
    return variable[node->var[0]];
  }else if(node->type == NTMULT){
    return evalRec(node->children[0]) * evalRec(node->children[1]);
  }else if(node->type == NTPLUS){
    return evalRec(node->children[0]) + evalRec(node->children[1]);
  }
}
