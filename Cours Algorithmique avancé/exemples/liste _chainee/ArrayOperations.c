#include "ArrayOperations.h"
#include <stdio.h>
int array_insert(IntArray *array, int value){
    if(array->count < array->size){
        array->values[array->count]=value;
        array->count++;
        return 0;
    }
    else{
        return -1;
    }
}
int array_insert_at_position(IntArray *array, int value, int postion){
    if(postion>array->size)return -1;
    int i=array->count-1;
    for(i;i>postion;i--){
        array->values[i]=array->values[i-1];
    }
    array->values[i]=value;
    return 1;
}
int array_remove_first_occurence(IntArray *array, int value){

    int i=0;
    for(i;i<array->count;i++){
        if(array->values[i]==value){
            for(i;i<array->count-1;i++){
                array->values[i]=array->values[i+1];
            }
            array->count--;
            break;
        }
    }
}
int array_remove_all_occurences(IntArray *array, int value){
    int i=0;
    for(i;i<array->count;i++){
        if(array->values[i]==value){
            int a=i;
            for(a;a<array->count-1;a++){
                array->values[a]=array->values[a+1];
            }
            array->count--;
        }
    }
}
int array_remove_at_position(IntArray *array, int position){
    int i=position;
    for(i;i<array->count;i++){
        array->values[i]=array->values[i+1];
    }
    array->count--;
    return 1;
}
int array_find(IntArray *array, int element){

    int i=0;
    for(i;i<array->count;i++){

        if(array->values[i]==element){
            return i;
        }
    }

    return -1;
}
void array_shift_right(IntArray *array, int start){
    int i=array->count-1;
    for(i;i>start;i--){
        array->values[i]=array->values[i-1];
    }
}
void array_shift_left(IntArray *array, int start){
    int i=1;
    for(i;i<start;i++){
        array->values[i-1]=array->values[i];
    }
}
void array_print(IntArray *array){
    int i=0;
    for(i;i<array->count;i++){
        printf("\ntab[%d]=%d\n",i,array->values[i]);
    }
}

int array_find_binary_search(IntArray *array, int value){
    return array_find_binary_search_with_boundries(array,value,0,array->count-1);
}
int array_find_binary_search_with_boundries(IntArray *array, int value, int start, int end){

    if(start>end){
        return -1;
    }
    int middle=(start+end)/2;
    if(array->values[middle]== value){
        return middle;
    }else if(array->values[middle]>value){
        return array_find_binary_search_with_boundries(array,value,start,middle-1);
    }else if(array->values[middle]<value){
        return array_find_binary_search_with_boundries(array,value,middle+1,end);
    }else{
        return -1;
    }
}
void array_swap(IntArray *array, int idx1, int idx2){

    int temp1=array->values[idx1];
    int temp2=array->values[idx2];

    array_remove_at_position(array,idx1);
    array_insert_at_position(array,temp2,idx1);
    array_remove_at_position(array,idx2);
    array_insert_at_position(array,temp1,idx2);
}

void array_bubble_sort(IntArray *array){
    int i = 0;
    int j = 0;
    for (i = array->count-1; i > 0; --i) {
        short sorted = 1;
        for(j = 0; j < i; ++j){
            if (array->values[j] > array->values[j+1]){
                array_swap(array, j, j+1);
                sorted = 0;
            }
        }
        if(sorted){
            return;
        }
    }
}


