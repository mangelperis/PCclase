#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
int main() {
	int pid;
	int i;
	int estado;
 
	pid = fork();
 
	switch(pid)
	{
		case -1: // Si pid es -1 quiere decir que ha habido un error
			perror("No se ha podido crear el proceso hijo\n");
			break;
		case 0: // Cuando pid es cero quiere decir que es el proceso hijo
			printf("Soy el hijo\n");
			break;
		default: // Cuando es distinto de cero es el padre
			// La función wait detiene el proceso padre y se queda esperando hasta
			// que termine el hijo
			wait(estado);
			printf("Mi proceso hijo ya ha terminado.\n");
			printf("Soy el padre\n");
			break;
	}
 
}
