//
//  LinkedListOperations.h
//  AlgoAvancee
//
//  Created by Robert Mogos on 4/7/16.
//  Copyright © 2016 Eventually. All rights reserved.
//

#ifndef LinkedListOperations_h
#define LinkedListOperations_h

#include "LinkedList.h"
#include "IntArray.h"

/**
 Returns 0 if succeded, -1 for an error
 **/
int linked_list_insert_at_beginning(LinkedList* list, int value);

/**
 Returns 0 if succeded, -1 for an error
 **/
int linked_list_insert_at_end(LinkedList* list, int value);


/**
 Returns 0 if succeded, -1 for an error
 **/
int linked_list_insert_at_postion(LinkedList* list, int value, unsigned int position);

/**
 Returns 0 if succeded, -1 if not found
 **/
int linked_list_remove_first_occurence(LinkedList* list, int value);

/**
 Returns the number of occurence found
 **/
int linked_list_remove_all_occurences(LinkedList* list, int value);

int linked_list_remove_occurence_stop_at_first(LinkedList* list, int value, unsigned short stopAtFirst);

/**
 Returns 0 if succeded, -1 if not found
 **/
int linked_list_remove_at_position(LinkedList* list, int position);

/**
 Returns the position where was found, -1 if not found
 **/
int linked_list_find_value(LinkedList* list, int element);

/**
 Reverse the list
 **/
void linked_list_reverse(LinkedList *list);

/**
 Return 1 if the linked list contains a loop and 0 if not
 **/
short linked_list_contains_loop(Node *first);

/**
 Returns the node , null if not found
 **/
Node * linked_list_find_node(LinkedList* list, int element);

void linked_list_print(LinkedList* list);

IntArray * linked_list_to_array(LinkedList *list);

LinkedList * array_to_linked_list(IntArray *array);



#endif /* LinkedListOperations_h */
