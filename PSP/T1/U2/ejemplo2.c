#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main()
{
int fd[2]; //creo vector entero
char saludoPadre[]="Buenos dias hijo.\0";
char buffer[80];
pid_t pid;

pipe(fd); //se crea el pipe

pid=fork(); //creo proceso hijo

	switch(pid)
	{
	case -1: //error
		printf("No se ha podido crear el hijo\n");
		exit(-1);
		break;
	case 0: //hijo recibe
	close (fd[1]); //cierra el descriptor de entrada
	read (fd[0],buffer, sizeof(buffer));//leo en el pipe
	printf("El hijo recibe algo del pipe...%s\n",buffer);
	
	break;
	
	default: //padre envia
	close (fd[0]); //cierra el descriptor de entrada
	write (fd[1], saludoPadre, strlen(saludoPadre));//escribo en el pipe

	printf("El padre envia mensaje al hijo...%s\n",saludoPadre);
	wait(NULL);//espero al proceso hijo
	break;
	}//fin switch
return 0;

}