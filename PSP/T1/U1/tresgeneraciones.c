
#include <sys/types.h> 
#include <stdio.h>
#include <unistd.h>
main(void)
{
 pid_t pid1,pid2;
 int status1,status2;
  
  pid1=fork();  
  if(pid1==0)
  {
      pid2=fork();
      if(pid2 == 0)
      {
	// SOY EL NIETO Y NO TENGO QUE ESPERAR A NADIE
        printf("\nSoy el NIETO (%d, hijo de %d)\n",getpid(),getppid());      
      }
      else
      {
	  // SOY EL PADRE TENGO QUE ESPERAR AL NIETO
	  waitpid(pid2, &status2  ,0);     
	  printf("\nSoy el PADRE (%d, hijo de %d)\n",getpid(),getppid());        
      }     
  }
  else
  {
	// SOY EL ABUELO Y TENGO QUE ESPERAR  AL PADRE
  	waitpid(pid1, &status1  ,0);  
        printf("\nSoy el ABUELO (%d, hijo de %d)\n",getpid(),getppid());
   }  
 return 0;
}
