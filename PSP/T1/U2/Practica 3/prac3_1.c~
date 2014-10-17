/* 1. Realiza   un   programa   que   permita   elegir   si   la   comunicación   es   de   padre   a   hijo   o   de   hijo   a  
padre   (ya   sea   en   el   programa   o   pasándole   un   parámetro).   Además   el   programa   debe  
permitir la introducción del mensaje de comunicación en ambos tipos de comunicación.  */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main()
{

int fd[2]; //creo vector entero
int respuesta; 

char mensaje[80]="";

 printf( "\nElige la direccion de la comunicacion:  \n" );
 printf( "1. Padre a Hijo  \n" );
 printf( "2. Hijo a Padre \n" );
 printf( "--------------------------------------  \n" );

scanf( "%d", &respuesta );
	

pid_t pid;

pipe(fd); //se crea el pipe


//printf("%d",respuesta);

if (respuesta == 1){
//PADRE -> HIJO
	printf("Escribe el mensaje para la comunicacion: \n");
	scanf("%s",mensaje);
	
	
	
	
	char buffer[80];
	
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
	printf("Estoy RECIBIENDO de mi padre el mensaje:   %s\n",buffer);
	
	break;
	
	default: //padre envia
	close (fd[0]); //cierra el descriptor de entrada
	write (fd[1], mensaje, strlen(mensaje));//escribo en el 	pipe

	printf("Estoy ENVIANDO a mi hijo el mensaje:  %s\n",mensaje);
	wait(NULL);//espero al proceso hijo
	break;
	}//fin switch

} else{
	if (respuesta == 2){
	//HIJO -> PADRE

	printf("Escribe el mensaje para la comunicacion: \n");
	scanf("%s",mensaje);
	
	
	
	
	char buffer[80];
	
	pid=fork(); //creo proceso hijo	
	
	
	switch(pid)
	{
	case -1: //error
		printf("No se ha podido crear el hijo\n");
		exit(-1);
		break;
	case 0: //hijo envia
	close (fd[0]); //cierra el descriptor de entrada
	write (fd[1],mensaje, strlen(mensaje));
 	

	printf("El HIJO envia al padre : %s\n",mensaje);
	
	break;
	
	default: //padre recibe
	close (fd[1]); //cierra el descriptor de entrada
	read (fd[0],buffer, sizeof(buffer));//leo en el pipe

	printf("El PADRE recibe el mensaje del hijo: %s\n",buffer);
	wait(NULL);//espero al proceso padre
	break;
	}//fin switch



	}else{
	printf("RESPUESTA INCORRECTA ... Saliendo\n");
	}

};





return 0;
}


