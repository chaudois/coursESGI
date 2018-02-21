//
//  LinkedList.c
//  AlgoAvancee
//
//  Created by Robert Mogos on 4/7/16.
//  Copyright © 2016 Eventually. All rights reserved.
//

#include "LinkedList.h"
#include <stdlib.h>

LinkedList *newLinkedList() {
  LinkedList *list = malloc(sizeof(LinkedList));
  list->size = 0;
  list->first=NULL;
  list->last=NULL;
  return list;
}
Variable *newVariable(int value, char* name) {
  Variable *var = malloc(sizeof(Variable));
  var->value = value;
  var->name  =name;
  var->next = NULL;
  return var;
}
/**
 Returns 0 if succeded, -1 for an error
 **/
void linked_list_insert_variable_at_beginning(LinkedList* list, double value,char* name){
    if (list == NULL){
        return -1;
    }
    
    Variable *oldFirstVariable = list->first;
    Variable newFirstVariable=newVariable(value,name);
    
    newFirstVariable->next = oldFirstVariable;
    list->first = newFirstVariable;
    if (oldFirstVariable == NULL){
        list->last = newFirstVariable;
    }
    list->size += 1;
}
int linked_list_insert_at_end(LinkedList* list, int value){
    if(list == NULL){
        return -1;
    }

    Node *newLastNode = newNode(value);
    if(list->first == NULL){
        list->first = newLastNode;
    }else{
        list->last->next = newLastNode;
    }
    list->last = newLastNode;
    list->size +=1;
    return 0;
}
/**
 Returns 0 if succeded, -1 for an error
 **/
int linked_list_insert_at_postion(LinkedList* list, int value, unsigned int position){
    if(list == NULL){
        return -1;
    }
    Node *currentNode=list->first;
    int i=0;
    for(i;i<position-1;i++){
        currentNode=currentNode->next;
    }
    Node *addNode= newNode(value);
    addNode->next=currentNode->next;
    currentNode->next=addNode;
}
void linked_list_print(LinkedList* list){
    Node *newNode=list->first;
    int i=0;
    do{
        printf("[%d] = %d\n",i,newNode->value);
        newNode=newNode->next;
        i++;
    }while(newNode!=NULL);
}
int linked_list_remove_occurence_stop_at_first(LinkedList* list, int value, unsigned short stopAtFirst){
    Node* _node=list->first;
    Node *previousNode=NULL;
    while(_node!=NULL){
        if(_node->value==value && previousNode==NULL){// on a trouvé le bon node mais c'est le premier de la liste
            list->first=_node->next;
            Node *tmp=_node;
            _node=_node->next;
            free(tmp);
            if(stopAtFirst==1){
                return 1;
            }
        }else if (_node->value==value &&_node==list->last){
            list->last=previousNode;
            previousNode->next=NULL;
            free(_node);
            list->size--;
            return 1;
        }else if(_node->value==value){//on a trouvé le node
            previousNode->next=_node->next;
             Node *tmp=_node;
            _node=_node->next;
            free(tmp);
            if(stopAtFirst==1){
                return 1;
            }
        }else{//on as pas trouvé le node, on avance
            previousNode=_node;
            _node=_node->next;
        }
    }
    return 0;
}
void linked_list_reverse(LinkedList *list){
    Node *begin = list->first;
    Node *newBegin = NULL;
    while(begin!=NULL){
        Node *_next=begin->next;
        begin->next=newBegin;
        newBegin=begin;
        begin=_next;
    }
    list->first=newBegin;
}
short linked_list_contains_loop(Node *first){


    return 0;
}

