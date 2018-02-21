//
//  StackOperations.h
//  AlgoAvancee
//
//  Created by Robert Mogos on 6/23/16.
//  Copyright © 2016 Eventually. All rights reserved.
//

#ifndef StackOperations_h
#define StackOperations_h

#include <stdio.h>
#include "Stack.h"


/**
  Insert the value as the first item in the stack.
  Returns 0 for success, 1 in case of an error
 */
int stack_push(Stack *stack, int value);

/**
 Removes the first item from the stack
 Returns the item or -1 in case of empty stack
 */
int stack_pop(Stack *stack);

/**
 Returns the first item in the stack WITHOUT removing it or -1 in case of empty stack
 */
int stack_peek(Stack *stack);


/**
  Return the maximum value in the stack or -1 if the stack is empty
 */
int stack_max_value(Stack *stack);


/**
  Return the value of the operation in a Reverse Polish Notation expression or INT_MIN in case of an error
 */
int stack_reverse_polish_notation_evaluator(char* values, int size);



#endif /* StackOperations_h */
