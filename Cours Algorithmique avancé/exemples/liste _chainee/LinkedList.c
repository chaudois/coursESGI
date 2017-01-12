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
  return list;
}

Node *newNode(int value) {
  Node *node = malloc(sizeof(Node));
  node->value = value;
  return node;
}
/**
 Returns 0 if succeded, -1 for an error
 **/
int linked_list_insert_at_beginning(LinkedList* list, int value){
    if (list == NULL){
        return -1;
    }
    Node *newFirstNode = newNode(value);
    Node *oldFirstNode = list->first;
    newFirstNode->next = oldFirstNode;
    list->first = newFirstNode;
    if (oldFirstNode == NULL){
        list->last = newFirstNode;
    }
    list->size += 1;
    return 0;
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
    }while(newNode->next!=NULL);
}
