#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "minipseudtree.h"

# define PRINTTAB 2

Node* createNode(int type) {
	Node* newnode = (Node *) malloc(sizeof(Node));
	newnode->val=0;
	newnode->var=NULL;
	newnode->type = type;
	newnode->children = NULL;
	return newnode;
}
void printNode (Node *node,int n){
	int i=0;
	printf("\n");
		for (  i=0;i<n;i++)printf("   .");
	printf("node->type=%s \n",node2String(node));
		for (  i=0;i<n;i++)printf("   .");
	printf("node->val=%f\n",node->val);
		for (  i=0;i<n;i++)printf("   .");
	printf("node->var=%s\n",node->var);
		for (  i=0;i<n;i++)printf("   .");
	if(node->children==NULL){
		printf("node->children=NULL\n");
		for (  i=0;i<n;i++)printf("   .");

	}else{

		printf("node->children[0]->type=%s \n",node2String(node->children[0]));
		for (  i=0;i<n;i++)printf("   .");
		printf("node->children[1]->type=%s \n",node2String(node->children[1]));
		printf("\n");
		printNode(node->children[0],n+1);
		printNode(node->children[1],n+1);


	}
}
Node* nodeChildren(Node* father, Node *child1, Node *child2) { 
	father->children = (Node **) malloc(sizeof(Node*) * 2);
	father->children[0] = child1;
	father->children[1] = child2;
	return father;
}

const char* node2String(Node *node) {	
	char *res;
	switch ( node->type ) {
	case NTEMPTY:    return "NTEMPTY";
	case NTINSTLIST: return "NTINSTLIST";

	 

	case NTNUM:
		res = (char *)malloc(sizeof(char) * 32);
		sprintf(res, "NTNUM -> %f", node->val);
		return res;
	 

	case NTPLUS:  return "NTPLUS";
	case NTMIN:   return "NTMIN";
	case NTMULT:  return "NTMULT";
	case NTDIV:   return "NTDIV";
	case NTPOW:   return "NTPOW";
	case NTIF :   return "NTIF";
	case NTTRAIT_OP:return "NTTRAIT_OP";
	case NTTRAIT_CL:return "NTTRAIT_CL";
	case NTEQU:   return "NTNEQU";
	case NTNEQU:  return "NTNEQU";
	case NTAFF:	  return "NTAFF";
	case NTVAR:	  return "NTVAR";
	case NTINST:  return "NTINST";
	case NTFCTAFF:	  return "NTFCTAFF";
	case NTFCTVAR:	  return "NTFCTVAR";
	case NTVARLIST:	  return "NTVARLIST";
	case NTPRINT:	  return "NTPRINT";
	case NTFOR:		return "NTFOR";
	case NTEXFCT:   return "NTEXFCT";
	case NTPPETIT:	return "NTPPETIT";
	case NTPGRAND:	return "NTPGRAND";
	case NTPPETITEG:	return "NTPPETITEG";
	case NTPGRANDEG:	return "NTPGRANDEG";


	default: printf("\n#####################################\nnode non reconu : %d\n",node->type); return "UNK";
	};
}



void printGraph(Node * root) {
	 printf("\n\n-------------------------arbre de node ---------------------------------\n\n");
	printNode(root,0);
}

