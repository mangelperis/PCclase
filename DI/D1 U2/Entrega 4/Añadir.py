#! /usr/bin/env python
# -*- coding: UTF-8 -*-


# Importamos el módulo pygtk y le indicamos que use la versión 2
import pygtk
pygtk.require("2.0")

# Luego importamos el módulo de gtk y el gtk.glade, este ultimo que nos sirve
# para poder llamar/utilizar al archivo de glade
import gtk
import gtk.glade
import gtk.gdk
import sqlite3

bbdd = 'tEjercicio'

# Creamos la clase de la ventana principal del programa
class MainWin:
	#conex = sqlite3.connect(bbdd)
	#c = conex.cursor()

	def __init__(self):
		# Indicamos el archivo SQLite
		try:
		    self.con = sqlite3.connect(bbdd)
		    self.cursor = self.con.cursor()
		    
		    
		except:
		    self.error("La BBDD no esta disponible.\n Confirma que existe el archivo 'db'.")
		
		# Le decimos a nuestro programa que archivo de glade usar (puede tener
		# un nombre distinto del script). Si no esta en el mismo directorio del
		# script habría que indicarle la ruta completa en donde se encuentra
		self.widgets  = gtk.glade.XML("Añadir.glade")

		
		window1 = self.widgets.get_widget("window1")
		#TITULO DE LA VENTANA
		window1.set_title(".::: Añadir Usuario :::.")
		
		#Color de fondo
		window1.modify_bg(gtk.STATE_NORMAL, gtk.gdk.Color('#E8E0F7'))
		
		# Creamos un pequeño diccionario que contiene las señales definidas en
		# glade y su respectivo método (o llamada)
		signals = {"on_button1_clicked" : self.on_button1_clicked,
			"gtk_main_quit" : gtk.main_quit,
			"on_buttonCancelar_clicked" : gtk.main_quit,
			"on_boton_grabar" : self.on_button_clicked,
			#"on_buttonRellenar_clicked" :self.on_buttonRellenar_clicked,
			"on_buttonLimpiar_clicked" : self.on_buttonLimpiar_clicked
					
			 }

		# Luego se auto-conectan las señales.
		self.widgets.signal_autoconnect(signals)

	# Se definen los métodos, en este caso señales como "destroy" ya fueron
	# definidas en el .glade, así solo se necesita definir "on_button1_clicked"
	def on_button1_clicked(self, widget):
		"Muestra la ventana con los datos"
		ventana = gtk.Dialog()
		ok_button = ventana.add_button(gtk.STOCK_OK, gtk.RESPONSE_OK)
		#BOTON "GRABAR" 
		ok_button.connect("clicked", self.on_button_clicked)

		cancelar_button = ventana.add_button(gtk.STOCK_CANCEL, gtk.RESPONSE_CANCEL)
		ok_button.grab_default()
		ventana.set_title("Confirma")
		label = gtk.Label("<b>¿Guardar Datos?</b>\n")
		label.set_property("use-markup", True)
		entry1 = gtk.Label("Usuario: "+self.widgets.get_widget("entry1").get_text())
		entry2 = gtk.Label("Contraseña: "+self.widgets.get_widget("entry2").get_text())
		entry3 = gtk.Label("E-mail: "+self.widgets.get_widget("entry3").get_text())
		entry4 = gtk.Label("Nombres: "+self.widgets.get_widget("entry4").get_text())
		entry5 = gtk.Label("Apellidos: "+self.widgets.get_widget("entry5").get_text())

		#Obtenemos datos del textview
		textview1 = self.widgets.get_widget("textview1")
		buffer = textview1.get_buffer()
		textview = gtk.Label("Direccion: "+buffer.get_text(buffer.get_start_iter(),buffer.get_end_iter())+"\n\n")
		
		ventana.vbox.pack_start(label, True, True, 0)
		ventana.vbox.pack_start(entry1, True, True, 0)
		ventana.vbox.pack_start(entry2, True, True, 0)
		ventana.vbox.pack_start(entry3, True, True, 0)
		ventana.vbox.pack_start(entry4, True, True, 0)
		ventana.vbox.pack_start(entry5, True, True, 0)
		ventana.vbox.pack_start(textview, True, True, 0)

		# Con show_all() mostramos el contenido del cuadro de dialogo (en este
		# caso solo tiene la etiqueta) si no se hace el dialogo aparece vacio
		ventana.show_all()
		# El run y destroy hace que la ventana se cierre al apretar el boton
		ventana.run()
		ventana.destroy()
		
				
	def on_buttonLimpiar_clicked(self,widget):	
		en1=self.widgets.get_widget("entry1").set_text("")
		en2= self.widgets.get_widget("entry2").set_text("")
		en3=self.widgets.get_widget("entry3").set_text("")
		en4=self.widgets.get_widget("entry4").set_text("")
		en5=self.widgets.get_widget("entry5").set_text("")
		en6=self.widgets.get_widget("textview1")
		bff =en6.get_buffer()
		bff.set_text("")
		
		
	#def on_buttonCancelar_clicked(self, widget):
		
		
	def on_buttonCerrar_clicked(self,widget):
	 	"NADA AQUI"
	#FUNCION GRABAR
	def on_button_clicked(self, widget):
		entry1 = self.widgets.get_widget("entry1").get_text()
		entry2 = self.widgets.get_widget("entry2").get_text()
		entry3 = self.widgets.get_widget("entry3").get_text()
		entry4 = self.widgets.get_widget("entry4").get_text()
		entry5 = self.widgets.get_widget("entry5").get_text()
		#TODO sacar el texto del textview para el insert...
		textview1 = self.widgets.get_widget("textview1")
		buffer = textview1.get_buffer()
		textview = buffer.get_text(buffer.get_start_iter(),buffer.get_end_iter())
		
		registro =(entry1, entry2, entry3, entry4, entry5, textview)
		
		try:	
			
			sql = "INSERT INTO usuarios (usuario,password,email,nombre,apellido,direccion) VALUES ('%s','%s','%s','%s','%s','%s')" % (entry1, entry2, entry3, entry4, entry5, textview)
			self.cursor.execute(sql)
			self.con.commit()
			
			print("\n:::>  Registro introducido correctamente")

			#Pop-UP de confirmacion
			ventana = gtk.Dialog()
			ok_button = ventana.add_button(gtk.STOCK_OK, gtk.RESPONSE_OK)
			ok_button.grab_default()
			ventana.set_title("Info")
			label = gtk.Label("\nAñadido registro\n <b><u>correctamente</u></b>\n")
			label.set_property("use-markup", True)
			ventana.vbox.pack_start(label, True, True, 0)
			ventana.show_all()
			ventana.run()
			ventana.destroy()
				
		except sqlite3.Error , e:
			mensaje = "ERROR al introducir el registro"
			print("\n:::>  "+mensaje)
			cursor.rollback()		
		
		

	

# Para terminar iniciamos el programa
if __name__ == "__main__":
	MainWin()
	gtk.main()
