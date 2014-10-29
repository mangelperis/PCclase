/*4. Crea un programa que simula el paso por un puente de tres procesos (donde sólo puede 
pasar un proceso).  Primero deberá pasar el más joven y por último el más viejo. 
	Salida esperada del programa 
	>Creo proceso 1. 
	> Creo proceso 2 
	> Creo proceso 3 
	> Paso puente proceso 3 y termina. 
	> Paso puente proceso 2 y termina. 
	> Paso puente proceso 1 y termina. 
*/





#include <sys/types.h> 
#include <unistd.h> 
#include <stdio.h> 

int main(int argc, char *argv[]) 
{ 
pid_t pid1, pid2,pid3; 

int status1,status2,status3;

if ( (pid1=fork()) ) {
sleep(0);
	if ( (pid2=fork())  ) {
	      sleep(0);
	     
	   }else{
	 printf("\nSoy el proceso 2, yo soy PID=%d \n",getpid());
	  }

	waitpid(pid2,&status2,0);
		if ( (pid3=fork()) ) {
			 sleep(0);		       
		    
		}else{
			waitpid(pid3,&status3,0);
			printf("\nSoy el proceso 3, yo soy PID=%d \n",getpid());
			
			
		 
			
			
		}
       
}else{
printf("\nSoy el proceso 1, yo soy PID=%d \n",getpid());
}
waitpid(pid1,&status1,0);


    

	
	

return 0; 
} 
