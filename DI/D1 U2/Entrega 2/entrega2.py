#! /usr/bin/env python
import gtk
import pygtk
import sqlite3

bbdd = 'tEjercicio'
class mundo:

	# conexión a la base de datos si está en el mismo directorio que la aplicación

conex = sqlite3.connect(bbdd)
c = conex.cursor()



def listar(self):
	
