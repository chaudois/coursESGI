//
//  Stack.c
//  AlgoAvancee
//
//  Created by Robert Mogos on 6/23/16.
//  Copyright © 2016 Eventually. All rights reserved.
//
#include "LinkedList.h"
#include "Stack.h"
#include "StackOperations.h"
#include <stdlib.h>

Stack *newStack() {
  Stack *stack = malloc(sizeof(Stack));
  stack->size = 0;
  return stack;
}
int stack_push(Stack *stack, int value){
    Node *addingNode = newNode(value);
    addingNode->next=stack;
    stack->top=addingNode;
    stack->size++;
    return 1;
}
int stack_pop(Stack *stack){
    if(stack == NULL || stack->size == 0){

    }
    Node *nodeToReturn = stack->top;
    stack->top=stack->top->next;


}
