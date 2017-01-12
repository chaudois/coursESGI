//
//  ArrayOperations.h
//  AlgoAvancee
//
//  Created by Robert Mogos on 3/16/16.
//  Copyright © 2016 Eventually. All rights reserved.
//

#ifndef ArrayOperations_h
#define ArrayOperations_h
#include "IntArray.h"

/**
 Returns 0 if succeded, -1 for an error
 **/
int array_insert(IntArray *array, int value);

/**
 Returns 0 if succeded, -1 for an error
 **/
int array_insert_at_position(IntArray *array, int value, int postion);

/**
 Returns 0 if succeded, -1 if not found
 **/
int array_remove_first_occurence(IntArray *array, int value);

/**
  Returns the number of occurence found
 **/
int array_remove_all_occurences(IntArray *array, int value);

/**
 Returns 0 if succeded, -1 if not found
 **/
int array_remove_at_position(IntArray *array, int position);

/**
 Returns the position where was found, -1 if not found
 **/
int array_find(IntArray *array, int element);

void array_shift_right(IntArray *array, int start);

void array_shift_left(IntArray *array, int start);

void array_print(IntArray *array);

/**
 Returns the position where was found, -1 if not found for a sorted array
 **/
int array_find_binary_search(IntArray *array, int value);

/**

 !!! Not implemented
 Returns the position where was found, -1 if not found for a sorted array
 **/
int array_find_binary_search_iterative(IntArray *array, int value);

IntArray sort_buble(IntArray *array);
/**

 !!! Not implemented
 Finds the item and returns a struct containing the first postion and the number of occurences
 **/
typedef struct t_IndexPath{
  int position;
  int length;
} IndexPath;
IndexPath array_find_binary_search_with_duplicates(IntArray *array, int value);

int array_find_binary_search_with_boundries(IntArray *array, int value, int start, int end);

void array_swap(IntArray *array, int idx1, int idx2);

/**
 Merge 2 sorted arrays into 1 sorted array
 **/
IntArray * array_sorted_merge(IntArray *array1, IntArray *array2);
#endif /* ArrayOperations_h */
