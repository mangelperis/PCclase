#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main()
{
int fd[2]; //creo vector entero
char saludoHijo[]="Hello dad.\0";
char buffer[sizeof(saludoHijo)];
pid_t pid;

pipe(fd); //se crea el pipe

pid=fork(); //creo proceso hijo

	switch(pid)
	{
	case -1: //error
		printf("No se ha podido crear el hijo\n");
		exit(-1);
		break;
	case 0: //hijo envia
	close (fd[0]); //cierra el descriptor de entrada
	write (fd[1],saludoHijo, strlen(saludoHijo));
 	

	printf("El hijo envia mensaje al padre...%s\n",saludoHijo);
	
	break;
	
	default: //padre recibe
	close (fd[1]); //cierra el descriptor de entrada
	read (fd[0],buffer, sizeof(buffer));//leo en el pipe

	printf("El padre lee algo del pipe...%s\n",buffer);
	wait(NULL);//espero al proceso padre
	break;
	}//fin switch
return 0;

}
