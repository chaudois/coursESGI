#include <stdio.h>
#include <stdlib.h>

int fibonnacci_recursif(int n){
    if(n==1)return 1;
    if(n==0)return 0;
    return fibonnacci_recursif(n-1)+fibonnacci_recursif(n-2);
}
int fibonnacci_iteratif(int n){

    if(n==0)return 0;
    if(n==1)return 1;

    int i=0;
    int total1=0;
    int total2=1;
    int totaltemp=0;
    for(i;i<n-1;i++){
        totaltemp=total2;
        total2=total1+total2;
        total1=totaltemp;
    }
    return total2;
}

int factoriel_recursif(int n){

    if(n>0){
        return n*factoriel_recursif(n-1);
    }else{
        return 1;
    }
}
int factoriel_iteratif(int n){
    if(n<=1)return 1;
    int result=1;
    int i=1;
    for(i;i<n+1;i++){
        result*=i;
    }
    return result;
}
int main()
{
    int number=8;
    printf("factoriel de %d = %d",number, factoriel_iteratif(number));
    return 0;
}
