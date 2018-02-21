//
//  Stack.h
//  AlgoAvancee
//
//  Created by Robert Mogos on 6/23/16.
//  Copyright © 2016 Eventually. All rights reserved.
//

#ifndef Stack_h
#define Stack_h

#include <stdio.h>
#include "LinkedList.h"

typedef struct s_Stack{
  Node *top;
  unsigned int size;
} Stack;

Stack *newStack();

#endif /* Stack_h */
