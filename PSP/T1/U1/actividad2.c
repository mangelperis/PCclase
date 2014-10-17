#include <sys/types.h> 
#include <unistd.h> 
#include <stdio.h> 
int main(int argc, char *argv[]) 
{ 
pid_t pid; 
int cont = 6;

printf("\n EXPLICACION: \nComo son dos contextos (dos procesos diferentes) cada uno está cogiendo el valor de la variable de forma independiente , pues para ambos el valor de la variable va a ser siempre '6' al inicio. \n ");

if ( (pid=fork()) == 0 ) //Estoy en el hijo 
    { /* hijo */ 
	cont += 5;
      printf("\nSoy el hijo. Valor variable %d\n", cont);
    } 
else 
    { /* padre */ 
	cont -= 5;
    printf("\nSoy el padre. Valor variable %d\n", cont); 
    } 
	printf("\n");
	sleep(1);
return 0; 
} 


