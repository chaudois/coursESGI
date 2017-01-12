//
//  IntArray.h
//  AlgoAvancee
//
//  Created by Robert Mogos on 3/16/16.
//  Copyright © 2016 Eventually. All rights reserved.
//

#ifndef IntArray_h
#define IntArray_h

typedef struct s_IntArray{
  int *values;
  unsigned int size;
  unsigned int count;
} IntArray;

IntArray *newArray(unsigned int size);

#endif /* IntArray_h */
