#!/usr/bin/env python

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
col = random.randint(0,8)

if turno =='#':
	#Escoger columna
	print("nothing")
else:
	
	for i in filas:
		for j in columnas:
			if str(j) != '9':
				if tablero[i][j] == col:
					sys.stdout.write('@')
					
				else:
					sys.stdout.write('|')
					sys.stdout.write(tablero[i][j])
			else:	
				sys.stdout.write('|'+str(i)+"\n")
		if str(i) == '5':
			sys.stdout.write(' 0 1 2 3 4 5 6 7 8'+"\n\n")	

	



