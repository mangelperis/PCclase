/*3. Realiza lo mismo que en el ejercicio anterior pero con la siguiente configuración de 
procesos:
*/



#include <sys/types.h> 
#include <unistd.h> 
#include <stdio.h> 

int main(int argc, char *argv[]) 
{ 
pid_t pid1, pid2; 

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
				
		
		if(pid2=fork()){
		 sleep(1);	
		}else{
		
		
		printf("\nYo soy el hijo del hijo 2, mi padre es PID=%d, yo soy PID=%d\n", getppid(),getpid());	
						
		}
	}else{

		/*padre*/
		waitpid(pid1,&status1,0);
		waitpid(pid2,&status2,0);
		
	   printf("\nYo soy el abuelo , yo soy PID = %d\n", getpid());
		
	}

	
	
}
return 0; 
} 
