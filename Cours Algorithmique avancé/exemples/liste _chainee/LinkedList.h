//
//  LinkedList.h
//  AlgoAvancee
//
//  Created by Robert Mogos on 4/7/16.
//  Copyright © 2016 Eventually. All rights reserved.
//

#ifndef LinkedList_h
#define LinkedList_h

typedef struct s_Node{
  int value;
  int max;
  struct s_Node *next;
} Node;

typedef struct s_LinkedList{
  Node *first;
  Node *last;
  unsigned int size;
} LinkedList;

LinkedList* newLinkedList();
Node* newNode(int value);

typedef struct s_DoubleNode{
  int value;
  struct s_DoubleNode *next;
  struct s_DoubleNode *previous;
} DoubleNode;

typedef struct s_DoubleLinkedList{
  DoubleNode *first;
  DoubleNode *last;
  unsigned int size;
} DoubleLinkedList;

#endif /* LinkedList_h */
