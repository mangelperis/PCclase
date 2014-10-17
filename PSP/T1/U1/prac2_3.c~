#include <sys/types.h> 
#include <unistd.h> 
#include <stdio.h> 
 
int main(int argc, char *argv[]) 
{ 
pid_t pid1, pid2; 
int status1, status2; 
pid1=fork(); 
if ( pid1== 0 ) 
    
{ /* padre */ 
printf("Soy el padre (%d, hijo de %d)\n",getpid(), getppid()); 

    } 
else 
    {
    if ( (pid2=fork()) == 0 ) 
   {
/* nieto */ 
    printf("Soy el nieto (%d, hijo de %d)\n",    getpid(), getppid()); 
	
    } 
    else 
    {
 /* abuelo */ 



 /* Esperamos al nieto termine, status 0*/ 
   waitpid(pid1, &status1, 0); 
  /* Esperamos al padre termine, status 0 */ 
   waitpid(pid2, &status2, 0); 


    printf("Soy el abuelo (%d, hijo de %d)\n",getpid(), getppid()); 
    } 
} 
return 0; 
}
