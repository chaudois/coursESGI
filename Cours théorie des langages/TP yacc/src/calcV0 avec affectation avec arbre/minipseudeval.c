#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <setjmp.h>
#include "minipseudtree.h"




 



int printDepth = 0;
int funcDepth = 0;

double evalExpr(Node *node) {
	switch ( node->type ) {
	case NTEMPTY:  return 0.0;
	case NTNUM: return node->val;
	case NTEQU: if(evalExpr(node->children[0])==evalExpr(node->children[1])){return 1;}else{return 0;}
	case NTIF: if(evalExpr(node->children[0])==1){ evalInst(node->children[1]);}
	break;
	 
	case NTPLUS: return evalExpr(node->children[0])
			+ evalExpr(node->children[1]);
	case NTMIN: return evalExpr(node->children[0])
			- evalExpr(node->children[1]);
	case NTMULT: return evalExpr(node->children[0])
			* evalExpr(node->children[1]);
	case NTDIV: return evalExpr(node->children[0])
			/ evalExpr(node->children[1]);
	case NTPOW: return pow(evalExpr(node->children[0]),
			evalExpr(node->children[1]));

	default: 
		printf("Error in evalExpr ... Wrong node type: %s\n", node2String(node));
		exit(1);
	};
}


void evalInst(Node* node) {
	double val;
	switch ( node->type ) {
	case NTEMPTY: return;
	case NTINSTLIST:
		evalInst(node->children[0]);
		evalInst(node->children[1]);
		return; 
	case NTNUM:
	case NTPLUS:
	case NTMIN:
	case NTMULT:
	case NTDIV:
	case NTPOW:
		printf("%f\n", evalExpr(node));
		return;
	case NTIF:
		if(evalExpr(node->children[0])==1){printf("%f\n",evalExpr(node->children[1]));}
		break;

	 
	default:
		printf("Error in evalInst ... Wrong node type: %s\n", node2String(node));
		exit (1);
	};
}

void eval(Node *node) {
	 
	evalInst(node);
}