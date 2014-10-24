/*1. Implementa  un programa que cree un hijo. El hijo debe escribir “Soy el hijo” diez veces. 
El padre debe escribir “Soy el padre” diez veces. El padre debe esperar a que termine el 
hijo y mostrar el mensaje “Mi proceso hijo ya ha terminado”. */


#include <sys/types.h> 
#include <unistd.h> 
#include <stdio.h> 

int main(int argc, char *argv[]) 
{ 
pid_t pid; 
int i;
int status1;
if ( (pid=fork()) == 0 ) 
    { /* hijo */ 
    for( i=0; i < 10 ; i++){
	printf("Soy el hijo (%d, hijo de %d)\n", getpid(),     getppid()); 
	}
    
    } 
else 
    { /* padre */ 
	waitpid(pid, &status1, 0);
	printf("++Mi proceso hijo ya ha terminado++ \n");
    for(i=0; i < 10; i++){
	
	printf("Soy el padre (%d, hijo de %d)\n", getpid(),     getppid()); 
	}
	
	
}
return 0; 
} 
