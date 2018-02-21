#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <setjmp.h>
#include "minipseudtree.h"

typedef struct VariableNum{
  char* name;
  double value;
  struct VariableNum* next;
}VariableNum ;

typedef struct Fonction{
  char* name;
  struct VariableNum* variables;
  Node* Instlist;
  struct Fonction* next;
  struct Fonction* porteeSup;
}Fonction ;

void evalInst(Node* node,Fonction* fonctionInst);
double evalExpr(Node *node,Fonction* fonctionPortee);
int idFor=0;
Fonction* mainFct=NULL;
Fonction* sousFonction=NULL;

void printVariable(VariableNum* variable){
	printf("%s=%f\n",variable->name,variable->value);
	if(variable->next!=NULL){
		printVariable(variable->next);
	}
}
void printFonctionTree(Fonction* fct,int n){
	int i=0;
	for(i=0;i<n;i++)printf("	");
	printf("->%s\n",fct->name);
	if(fct->porteeSup!=NULL){
		printFonctionTree(fct->porteeSup,n+1);
	}
}

void printFonction(Fonction* fct){
	printf("\nvariables de la fonction %s : \n",fct->name);
	printVariable(fct->variables);
	printf("\nportee de la fonction %s : \n",fct->name);
	printFonctionTree(fct,0);
}



VariableNum* createVariableNum(int value, char* name) {
  VariableNum *var = malloc(sizeof(VariableNum));
  var->value = value;
  var->name  =name;
  return var;
}




VariableNum *findVariableNum(char* name,Fonction* baseDeRecherche){

	VariableNum *varTemp=baseDeRecherche->variables;
	while (varTemp!=NULL){

		if(strcmp(varTemp->name,name)==0){
			return varTemp;
		}
		varTemp=varTemp->next;
	}
	if(baseDeRecherche->next==NULL){

	}else{
		return findVariableNum(name,baseDeRecherche->next);
	}

	return varTemp;
}

Fonction* findFonction(char* name,Fonction* fonctionBase){

	Fonction* fct=fonctionBase;

	if(name=="mainFct"){return mainFct;}


	if(fct==NULL){return NULL;}

	if(strcmp(fct->name,name)==0){return fct;}
	if(fct->next==NULL){
	}else{return findFonction(name,fct->next);}
	
	return NULL;
}
double evalExpr(Node *node,Fonction* fonctionPortee) {
	VariableNum *varTemp;
	switch ( node->type ) {
	case NTEMPTY:  return 0.0;
	case NTNUM: /*printf("			%f\n",node->val);*/return node->val;
	case NTEQU: if(evalExpr(node->children[0],fonctionPortee)==evalExpr(node->children[1],fonctionPortee)){return 1;}else{return 0;}
	case NTIF: if(evalExpr(node->children[0],fonctionPortee)==1){ evalInst(node->children[1],fonctionPortee);}
	break;
	 
	case NTPLUS: return evalExpr(node->children[0],fonctionPortee)
			+ evalExpr(node->children[1],fonctionPortee);
	case NTMIN: return evalExpr(node->children[0],fonctionPortee)
			- evalExpr(node->children[1],fonctionPortee);
	case NTMULT: return evalExpr(node->children[0],fonctionPortee)
			* evalExpr(node->children[1],fonctionPortee);
	case NTDIV: return evalExpr(node->children[0],fonctionPortee)
			/ evalExpr(node->children[1],fonctionPortee);
	case NTPOW: return pow(evalExpr(node->children[0],fonctionPortee),
			evalExpr(node->children[1],fonctionPortee));
	case NTPGRAND: 	if(evalExpr(node->children[0],fonctionPortee)>evalExpr(node->children[1],fonctionPortee)){return 1;}else{return 0;}
	case NTPPETIT: 	if(evalExpr(node->children[0],fonctionPortee)<evalExpr(node->children[1],fonctionPortee)){return 1;}else{;return 0;}
	case NTPGRANDEG: 	if(evalExpr(node->children[0],fonctionPortee)>=evalExpr(node->children[1],fonctionPortee)){return 1;}else{return 0;}
	case NTPPETITEG: 	if(evalExpr(node->children[0],fonctionPortee)<=evalExpr(node->children[1],fonctionPortee)){return 1;}else{return 0;}

	case NTVAR:
		varTemp=findVariableNum(node->var,fonctionPortee);
		if(varTemp==NULL){
				varTemp=createVariableNum(0,node->var);	

		}
		return varTemp->value;


	default: 
		printf("Error in evalExpr ... Wrong node type: %s\n", node2String(node));
		exit(1);
	};
}

VariableNum* createVariableTree(Node* VARLIST,Fonction* fonctionPortee){
	VariableNum* var1=NULL;

	VariableNum* var2=NULL;

	if(VARLIST==NULL)return NULL;
	if(VARLIST->children==NULL){
		var1=findVariableNum(VARLIST->var,fonctionPortee);
		var1->next=var2;

		return var1;


	}else{
		var1=findVariableNum(VARLIST->children[0]->var,fonctionPortee);
	}

	if(var1==NULL){
		if(VARLIST->children==NULL){
			var1=createVariableNum(evalExpr(VARLIST,fonctionPortee),VARLIST->var);

		}else{
			var1=createVariableNum(evalExpr(VARLIST->children[0],fonctionPortee),VARLIST->children[0]->var);

		}
	}
	if(VARLIST->children != NULL && VARLIST->children[1]->type==NTVAR){
	 	var2=createVariableNum(evalExpr(VARLIST->children[1],fonctionPortee),VARLIST->children[1]->var);

	}else{
		var2=createVariableTree(VARLIST->children[1],fonctionPortee);

	}
	var1->next=var2;
	//printf("\n--variable1 '%s'=%f     variable2 '%s'=%f ",var1->name,var1->value,var2->name,var2->value);
	//if(var2->next!=NULL)printf("          variable2 '%s'=%f ",var2->next->name,var2->next->value);
	//	printf("\n");
	return var1;
}

Fonction* createFonction( char* name,Node* Instlist, Node *variables,Fonction* fonctionSup) {

  Fonction *fct = malloc(sizeof(Fonction));
  fct->variables = createVariableTree(variables,fonctionSup);
  fct->Instlist=Instlist;
  fct->name  =name;
  fct->porteeSup = fonctionSup;
  fct->next=sousFonction;
  sousFonction=fct;

  return fct;
}


void evalInst(Node* node,Fonction* fonctionPortee) {
	double val;
	VariableNum *varTemp;
	Fonction* fct=NULL;
	switch ( node->type ) {
	case NTVAR:
	case NTEMPTY: return;

	case NTINSTLIST:
		evalInst(node->children[0],fonctionPortee);
		evalInst(node->children[1],fonctionPortee);
		break;
	case NTINST:
		evalInst(node->children[0],fonctionPortee);
		return; 
	case NTNUM:
	case NTPLUS:
	case NTMIN:
	case NTMULT:
	case NTDIV:
	case NTPOW:
		printf("%f\n", evalExpr(node,fonctionPortee));
		return;
	case NTAFF:
		varTemp=findVariableNum(node->children[0]->var,fonctionPortee);
		if(varTemp==NULL){
			varTemp=createVariableNum(evalExpr(node->children[1],fonctionPortee),node->children[0]->var);
			varTemp->next=fonctionPortee->variables;
			fonctionPortee->variables=varTemp;

			//printf("creation de la variable '%s' avec la valeur %f sur la portee %s\n",fonctionPortee->variables->name,fonctionPortee->variables->value,fonctionPortee->name);

		}else{
			varTemp->value=evalExpr(node->children[1],fonctionPortee);
			//printf("affectation de la variable '%s' avec la valeur %f\n",varTemp->name,varTemp->value);

		}
		return;
	case NTIF:
		if(evalExpr(node->children[0],fonctionPortee)==1){evalInst(node->children[1],fonctionPortee);}
		return;
	case NTFCTAFF:
		fct=createFonction(node->children[0]->children[0]->var,
							node->children[1],
							node->children[0]->children[1],
							fonctionPortee);
		//printf("creation de la fonction %s\n",fct->name);
		return;
	case NTPRINT: 
		if(node->children[0]->var==NULL){
			printf("\n%f\n",evalExpr(node->children[0],fonctionPortee));
			return;
		}else{
			varTemp=findVariableNum(node->children[0]->var,fonctionPortee);
			if(varTemp==NULL){
				fct=findFonction(node->children[0]->var,fonctionPortee);
				if(fct!=NULL){
					printFonction(fct);
				}else{
					printf("\nimpossible de trouver la variable/fonction '%s'\n",node->children[0]->var);
				}
			}else{
				printf("\n'%s'=%f\n",varTemp->name,varTemp->value);
			}
		}

		//printf("\n%f\n",evalExpr(node->children[0],fonctionPortee));
	
	break;
	case NTEXFCT:
		fct=findFonction(node->children[0]->var,sousFonction);
		if(fct==NULL){printf("\nERREUR : la fonction '%s' n'as pas ete declaree\n",node->children[0]->var);}
		else{
			//printNode(node,0);
			fct->variables=createVariableTree(node->children[1],fonctionPortee);
			evalInst(fct->Instlist,fct);
		}
		break;
	case NTFOR:
		//printf("\nHELLO\n");
		idFor+=1;
		//printNode(node,0);

		while(evalExpr(node->children[0],fonctionPortee)==1){

			//printf("evalExpr(node->children[1]->)=%f",evalExpr(node->children[1]->children[1],fonctionPortee));
			//printNode(node->children[1],0);
			evalInst(node->children[1]->children[1],fonctionPortee);

			evalInst(node->children[1]->children[0],fonctionPortee);
		}
		//printFonction(fct);
		//Fonction* createFonction( char* name,Node* Instlist, Node *variables,Fonction* fonctionSup) {

		break;
	default:
		printf("Error in evalInst ... Wrong node type: %s\n", node2String(node));
		exit (1);
	};
}

void eval(Node *node) {
	mainFct=createFonction("mainFct",node,NULL,NULL);
	printf("\n\n-------------------------resultat de l'execution---------------------------------\n\n");
	evalInst(node,mainFct);

	printf("\n-----------------------------fin de l'execution---------------------------------\n");


}