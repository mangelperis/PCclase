#!/usr/bin/env python
#MANGELPERIS

import sys
import random


tablero =  [ ['_','_','_','_','_','_','_','_','_','_'],  ['_','_','_','_','_','_','_','_','_','_'], ['_','_','_','_','_','_','_','_','_','_'], ['_','_','_','_','_','_','_','_','_','_'], ['_','_','_','_','_','_','_','_','_','_'], ['_','_','_','_','_','_','_','_','_','_'] ]
 

filas = range(6)
columnas = range(10)

def mostrar(tablero):
	for i in filas:
		for j in columnas:
			if str(j) != '9':
				sys.stdout.write('|')
				sys.stdout.write(tablero[i][j])
			else:	
				sys.stdout.write('|'+str(i)+"\n")
		if str(i) == '5':
			sys.stdout.write(' 0 1 2 3 4 5 6 7 8'+"\n\n")	

mostrar(tablero)

def tiraUSER():
	for i in filas:
			for j in columnas:
				
					if str(j) != '9':
						if str(i) == '5':
							tablero[5][col] = USER
							sys.stdout.write('|')
							sys.stdout.write(tablero[i][j])
						else:
							sys.stdout.write('|')
							sys.stdout.write(tablero[i][j])
					else:	
						sys.stdout.write('|'+str(i)+"\n")
				if str(i) == '5':
					sys.stdout.write(' 0 1 2 3 4 5 6 7 8'+"\n\n")

#def tiraIA():


ficha = ''
IA = ''
USER =''
turno =''
while ficha <>'#' and ficha <>'@':
	ficha = raw_input("Elige ficha #/@  (Sale #): ")

	if ficha == '#':
		USER = '#'
		IA = '@'
		turno = '#'
	elif ficha == '@':
		USER = '@'
		IA = '#'
		turno = '@'
	else:
		print("ERROR: No has elegido ficha correctamente")


#
mov = 1
print('\n***** TURNO:: '+str(mov)+' ****')

# TURNO 1
if turno =='#':
	#Mueve USUARIO
	#Escoger columna
	
	col = input("\nEscoge columna (0-8): ")
	print("\n.....Mueven # .....")
	for i in filas:
		for j in columnas:
			if str(j) != '9':
				if str(i) == '5':
					
					tablero[5][col] = USER
					sys.stdout.write('|')
					sys.stdout.write(tablero[i][j])
				else:
					sys.stdout.write('|')
					sys.stdout.write(tablero[i][j])
			else:	
				sys.stdout.write('|'+str(i)+"\n")
		if str(i) == '5':
			sys.stdout.write(' 0 1 2 3 4 5 6 7 8'+"\n\n")
	#MUEVE MAQUINA
	print("\n.....Mueven @ .....")
	col = random.randint(0,8)
	for i in filas:
		for j in columnas:
			if str(j) != '9':
				if str(i) == '5':
					if tablero[5][col] == '#':
						
						col = random.randint(0,8)
						tablero[5][col] = IA
						sys.stdout.write('|')
						sys.stdout.write(tablero[i][j]) 
					else:
						tablero[5][col] = IA
						sys.stdout.write('|')
						sys.stdout.write(tablero[i][j]) 
				else:
					sys.stdout.write('|')
					sys.stdout.write(tablero[i][j])
			else:	
				sys.stdout.write('|'+str(i)+"\n")
		if str(i) == '5':
			sys.stdout.write(' 0 1 2 3 4 5 6 7 8'+"\n\n")	
else:
	#MUEVE MAQUINA
	print("\n.....Mueven # .....")
	col = random.randint(0,8)
	for i in filas:
		for j in columnas:
			if str(j) != '9':
				if str(i) == '5':
					tablero[5][col] = IA
					sys.stdout.write('|')
					sys.stdout.write(tablero[i][j])
				else:
					sys.stdout.write('|')
					sys.stdout.write(tablero[i][j])
			else:	
				sys.stdout.write('|'+str(i)+"\n")
		if str(i) == '5':
			sys.stdout.write(' 0 1 2 3 4 5 6 7 8'+"\n\n")	
	#MUEVE USUARIO
	print("\n.....Mueven @ .....")
	col = input("\nEscoge columna (0-8): ")
	for i in filas:
		for j in columnas:
			if str(j) != '9':
				if str(i) == '5':
					if tablero[5][col] != '_':
										
						tablero[5][col] = USER
						sys.stdout.write('|')
						sys.stdout.write(tablero[i][j]) 
					else:
						tablero[5][col] = USER
						sys.stdout.write('|')
						sys.stdout.write(tablero[i][j]) 
				else:
					
					sys.stdout.write('|')
					sys.stdout.write(tablero[i][j])
			else:	
				sys.stdout.write('|'+str(i)+"\n")
		if str(i) == '5':
			sys.stdout.write(' 0 1 2 3 4 5 6 7 8'+"\n\n")	
print('\n* FIN TURNO:: '+str(mov)+' *\n')
#Fin TURNO 1
mov += 1




