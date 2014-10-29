/*2. Realice un programa en C que genere la siguiente configuración de procesos: 

Además, cada hijo deberá mostrar el mensaje "Yo soy el hijo x, mi padre es PID=Y, yo 
soy PID=z".

*/

#include <sys/types.h> 
#include <unistd.h> 
#include <stdio.h> 

int main(int argc, char *argv[]) 
{ 
pid_t pid1, pid2,pid3; 

int status1,status2,status3;

if ( (pid1=fork()) == 0 ) 
    { /* hijo */ 
    printf("\nYo soy el hijo 1, mi padre es PID=%d, yo soy PID=%d \n",getppid(),getpid());
    
    } 
else 
    { /* padre */ 
	if ((pid2=fork()) == 0){
	/*segundo hijo*/
   printf("\nYo soy el hijo 2, mi padre es PID=%d, yo soy PID=%d\n", getppid(),getpid());
	}else{

		if ((pid3=fork()) == 0){
		printf("\nYo soy el hijo 3, mi padre es PID=%d, yo soy PID=%d\n", getppid(),getpid());
		}else{
		/*padre*/
		waitpid(pid1,&status1,0);
		waitpid(pid2,&status2,0);
		waitpid(pid3,&status3,0);
	   printf("\nYo soy el padre , yo soy PID = %d\n", getpid());
		}
	}

	
	
}
return 0; 
} 
