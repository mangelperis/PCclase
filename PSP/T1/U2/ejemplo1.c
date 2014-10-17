#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
int fd[2]; //creo vector entero
char buffer[30];
pid_t pid;

pipe(fd); //se crea el pipe

pid=fork(); //creo proceso hijo

	switch(pid)
	{
	case -1: //error
		printf("No se ha podido crear el hijo\n");
		exit(-1);
		break;
	case 0: //hijo escribe descriptor 1
	printf("El hijo escribe en el pipe...\n");
	write (fd[1], "Hola papi",10);
	break;
	
	default: //padre lee de descriptor 0
	wait(NULL); //padre espera hijo termina escribir
	printf("El padre lee del pipe...\n");
	read (fd[0], buffer, 10);
	printf ("\tMensaje ledio:%s\n", buffer);
	break;
	}//fin switch


}
