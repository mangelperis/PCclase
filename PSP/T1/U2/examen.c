#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
//3 generaciones abuelo-padre-hijo
int main(int argc, char *argv[])
{
pid_t pid,pid_hijo;
int fortuna=0;
pid=fork(); //soy el abuelo creo a padre
	if ( pid== 0) //Estoy en padre
	{
		pid_hijo=fork(); //creo a hijo
			switch(pid_hijo) {
			case -1:
			printf("ERROR");
			return 0;
			case 0: //hijo
			//actualizo fortuna
			fortuna=fortuna+10;
		printf ("Soy el hijo dejo fortuna de %d\n",fortuna);
			break;
			default: //padre
			//actualizo fortuna
			wait(NULL);
			fortuna=fortuna+20;
		printf ("Soy el padre dejo fortuna de %d\n",fortuna);
			break;
			}//fin switch
	}
	else
	{ /* abuelo */
	wait(NULL);
	//actualizo fortuna
	fortuna=fortuna-10;
	printf ("Soy el abuelo dejo fortuna de %d\n",fortuna);
	}
return 0;
}
