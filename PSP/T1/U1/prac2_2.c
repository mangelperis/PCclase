#include <sys/types.h> 
#include <unistd.h> 
#include <stdio.h> 
 
int main(int argc, char *argv[]) 
{ 
pid_t pid1, pid2; 
int status1, status2; 
pid1=fork(); 
if ( pid1== 0 ) 
    { /* hijo */ 
    printf("Soy el primer hijo (%d, hijo de %d)\n",    getpid(), getppid()); 
    } 
else 
    { /* padre */ 
    if ( (pid2=fork()) == 0 ) 
    { /* segundo hijo */ 

	printf("Soy el segundo hijo (%d, hijo de %d)\n",getpid(), getppid()); 
    } 
    else 
    { /* padre */ 
 
 /* Esperamos al primer hijo termine, status 0*/ 
   /* waitpid(pid1, &status1, 0); */
 
 /* Esperamos al segundo hijo termine, status 0 */ 
  /*  waitpid(pid2, &status2, 0); */
    printf("Soy el padre (%d, hijo de %d)\n",getpid(), getppid()); 
    } 
} 
return 0; 
}
