#!/usr/bin/env python
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
import subprocess

#Nombre del archivo de BDD SQLite3
bbdd = 'bMatricula'

# Creamos la clase de la ventana principal del programa
class MainWin:
	def __init__(self):
		
		try:
			self.con = sqlite3.connect(bbdd)
		   	self.cursor = self.con.cursor()
		    		    
		except sqlite3.Error , e:
			#No se porque este error nunca salta
			self.error("La BBDD no esta disponible.\n Confirma que existe el archivo '"+str(bdd)+"'.bdd")
		        print("La BBDD no esta disponible.\n Confirma que existe el archivo '"+str(bdd)+"'.bdd")

		self.widgets  = gtk.glade.XML("Menu.glade")
		
		window1 = self.widgets.get_widget("window1")
		#TITULO DE LA VENTANA
		window1.set_title("Sistema de matrícula - M.Angel Peris")
		
		#Color de fondo
		window1.modify_bg(gtk.STATE_NORMAL, gtk.gdk.Color('#A0B8BC'))
		# Creamos un pequeño diccionario que contiene las señales definidas en
		# glade y su respectivo método (o llamada)
		signals = {"on_Salir_activate": gtk.main_quit,
			"gtk_main_quit" : gtk.main_quit,
			"on_Abrir_activate" : self.on_Abrir_activate,
			"on_Imprimir_activate" : self.on_Imprimir_activate,
			"on_menuAcerca_activate" : self.on_menuAcerca_activate,
			"on_Usuarios_clicked" : self.on_Usuarios_clicked
			 }
		
		# Luego se auto-conectan las señales.
		self.widgets.signal_autoconnect(signals)

		
		
		window1.show_all()
	###############################################
	def on_Usuarios_clicked(self,widget):
		#Nombre del otro archivo con la funciñón de añadir
		
		subprocess.Popen(["python",'usuarios.py'])
		#print("\n::>ERROR. No se encuentra el archivo < "+str(archivo)+" >")

	################## MENU ########################
	def file_ok_sel(self, w):
   	        print "Abrir ::> %s" % self.filew.get_filename()
		#ABRIR
	def on_Abrir_activate(self, widget):
		self.filew = gtk.FileSelection("Abrir... ((SOLO DEMO, NO ABRE NADA))")
   	
   	        #self.filew.connect("destroy", self.destroy)
   	        # Conectar ok_button al método file_ok_sel
   	        self.filew.ok_button.connect("clicked", self.file_ok_sel)
   	    
   	        # Conectar cancel_button para destruir el control
   	        self.filew.cancel_button.connect("clicked",
   	                                         lambda w: self.filew.destroy())
   	        # Fijamos el nombre de fichero, como si fuese un diálogo de guardado,
   	        # y damos un nombre por defecto
   	        self.filew.set_filename("<<filename>>.py")
   	    
   	        self.filew.show()

		#IMPRIMIR
	def on_Imprimir_activate(self,widget):
		print_op = gtk.PrintOperation()
		print_op.set_n_pages(1)
		print_op.connect("draw_page", self.print_text)
		res = print_op.run(gtk.PRINT_OPERATION_ACTION_PRINT_DIALOG, None)

	def draw_text(self):
		self.pangolayout = self.area.create_pango_layout("")
		self.format_text()
		self.area.window.draw_layout(self.gc, 10, 10, self.pangolayout)
		return
 
   	def print_text(self, operation=None, context=None, page_nr=None):
		self.pangolayout = context.create_pango_layout()
		self.format_text()
		cairo_context = context.get_cairo_context()
		cairo_context.show_layout(self.pangolayout)
		return
 
	def format_text(self):
		self.pangolayout.set_text(unicode("""Esta es una prueba de texto.\nFunciona bien y demuestra que PyGTK puede imprimir lo que escribiste en un DrawingArea.\nPara esta aplicacion, es suficiente.""", "latin-1"))
 		
		
   	
	
	####################################################

	def on_menuAcerca_activate(self, widget):
		"Muestra la ventana Acerca de"
		about = gtk.AboutDialog()
		about.set_name("Sistema de matricula ")
		about.set_version("2.0")
		about.set_comments("Añadir,Visualizar, Editar y Borrar registros\nVarias tablas\nVarios Archivos")
		about.set_copyright("DI @ IES Serpis (Valencia)")
		
		def openHomePage(widget, url, url2):  # Para abrir el sitio
		    import webbrowser
		    webbrowser.open_new(url)
		
		gtk.about_dialog_set_url_hook(openHomePage, "http://www.institutoserpis.org/")
		about.set_website("http://www.institutoserpis.org/")
		about.set_authors(["Miguel Angel Peris (mangelperis@gmail.com)"])
		about.run()
		about.destroy()

# Para terminar iniciamos el programa
if __name__ == "__main__":
	MainWin()
	gtk.main()
