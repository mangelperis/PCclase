/*
Realiza un programa en C en donde un hijo
envíe 3 señales SIGUSR1 a su padre que
provoquen un manejador(handler) que escribirá
el mensaje “soy el manejador del padre” y
después envíe una señal SIGKILL para que el
proceso padre termine.

*/


#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <fcntl.h>

/*--------------------------------------------------------------*/
/*gestion de señales en proceso padre (handler)*/
void gestion_padre (int segnal) 
{
	printf ("Soy el manejador del padre...ID : %d\n",segnal);
}
/*--------------------------------------------------------------*/

int main(){
	int pid_hijo,pid_padre;
	pid_padre=getpid();
	pid_hijo=fork(); //se crea al hijo

	switch(pid_hijo){
		case -1: //error
			printf("Error al crear proceso  hijo..\n");
			exit(-1);	
		
		case 0: //hijo
				//Envio 3 señales SIGUSR1 y despues la de SIGKILL				
				kill(pid_padre,SIGUSR1); 
				sleep(1);
				kill(pid_padre,SIGUSR1); 					sleep(1);
				kill(pid_padre,SIGUSR1); 					sleep(1);
				kill(pid_padre,SIGKILL); 
				sleep(1);
			break;

		default: //padre
			signal(SIGUSR1,gestion_padre); //tratamiento señal en proceso padre
			while (1) { //bucle infinito
				
				
				
				}
			break;
		}//fin switch

return 0;
}//fin programa
