//
//  IntArray.c
//  AlgoAvancee
//
//  Created by Robert Mogos on 3/16/16.
//  Copyright © 2016 Eventually. All rights reserved.
//

#include <stdlib.h>
#include "IntArray.h"

IntArray * newArray(unsigned int size) {
  IntArray * intArray = malloc(sizeof(IntArray));
  intArray->values = malloc(size * sizeof(int));
  intArray->count = 0;
  intArray->size = size;
  return intArray;
}

