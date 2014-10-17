#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main()
{
int fp;
char saludo[]="Un saludo!!!";
fp=open("FIFO1",1);

if (fp==-1)
{
print("ERROR AL ABRIR ARCHIVO\n");
exit(1);
}

printf("Mandando informacion al fifo...\n");
write(fp,saludo,strlen(saludo));
close(fp);

return 0;
}
