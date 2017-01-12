#include <stdio.h>
#include <stdlib.h>
#include "ArrayOperations.h"
#include "IntArray.h"
int main()
{
    IntArray *newTab = newArray(20);

    array_insert(newTab,78500);
    array_insert(newTab,10);
    array_insert(newTab,78500);
    array_insert(newTab,9985);
    array_insert(newTab,100);
    array_insert(newTab,8935);
    array_insert(newTab,785);
    array_insert(newTab,1008);
    array_insert(newTab,2014);
    array_insert(newTab,7896);
    array_insert(newTab,7897);
    array_insert(newTab,7980);
    array_insert(newTab,15400);
    array_insert(newTab,9985);
    array_insert(newTab,15400);
    array_insert(newTab,9985);
    array_insert(newTab,78500);
    array_insert(newTab,100);
    array_insert(newTab,35);
    array_insert(newTab,2014);

    array_print(newTab);

    array_bubble_sort(newTab);
    printf("\n\n****************SORTING*********************\n\n");

    array_print(newTab);

    int valueSearched=2014;

    int indexFounded=array_find_binary_search(newTab,valueSearched);

    if(indexFounded>=0){
        printf("\nocurence de la valeur %d trouvee a la case %d\n",valueSearched,indexFounded);
    }
    else{
        printf("\nocurence de la valeur %d non trouvee\n",valueSearched);

    }
    return 1;

}
