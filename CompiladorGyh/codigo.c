#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include<string.h>


int main(void){

	int fatorial;
	int numFatorial;
	int aliasNumFatorial;
{
	printf("Insira um valor para calcular o fatorial: ");
	scanf("%d",&numFatorial);
	fatorial = 1;
	aliasNumFatorial = numFatorial;
	while(aliasNumFatorial > 1) {
	fatorial = fatorial*aliasNumFatorial;
	aliasNumFatorial = aliasNumFatorial-1;
	}
	printf("Fatorial calculado: ");
	printf("%d\n",fatorial);
	}
	return 0;
}
