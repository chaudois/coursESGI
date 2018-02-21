#include <stdio.h>
#include <stdlib.h>
#include "IntArray.h"
#include "ArrayOperations.h"
#include "LinkedList.h"
#include "LinkedListOperations.h"

int main()
{
    Node *rdmNode=newNode(5);
    rdmNode->next=newNode(7);
    rdmNode->next->next=newNode(90);
    rdmNode->next->next->next=newNode(145);

    LinkedList *llist=newLinkedList();
    llist->first=rdmNode;
    llist->last=rdmNode->next->next->next;

    linked_list_reverse(llist);
    linked_list_print(llist);


    return 0;
}
