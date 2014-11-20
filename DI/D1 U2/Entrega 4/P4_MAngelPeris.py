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
bbdd = 'tEjercicio'

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
		
		self.widgets  = gtk.glade.XML("P4_MAngelPeris.glade")
		
		window1 = self.widgets.get_widget("window1")
		#TITULO DE LA VENTANA
		window1.set_title("Entrega 4 - M.Angel Peris - treeView")
		
		#Color de fondo
		window1.modify_bg(gtk.STATE_NORMAL, gtk.gdk.Color('#A0B8BC'))
		# Creamos un pequeño diccionario que contiene las señales definidas en
		# glade y su respectivo método (o llamada)
		signals = {"on_toolbuttonAbrir_clicked" : self.on_toolbuttonAbrir_clicked,
			"gtk_main_quit" : gtk.main_quit,
			"on_toolbuttonAcerca_clicked" : self.on_toolbuttonAcerca_clicked,
			#"on_toolbuttonSalir_clicked" : gtk.main.quit,
			"on_buttonListar_clicked" : self.on_buttonListar_clicked,
			"on_buttonLimpiar_clicked" : self.on_buttonLimpiar_clicked,
			"on_buttonAdd_clicked" : self.on_buttonAdd_clicked,
			"on_buttonBorrar_clicked" : self.on_buttonBorrar_clicked	
			 }
		
		# Luego se auto-conectan las señales.
		self.widgets.signal_autoconnect(signals)

		self.vista = self.widgets.get_widget("vista")
		global listStore
		listStore = gtk.ListStore(str,str,str,str,str,str,str)
		render = gtk.CellRendererText()
		columna1 = gtk.TreeViewColumn("ID",render,text=0)
		columna2 = gtk.TreeViewColumn("Usuario",render,text=1)
		columna3 = gtk.TreeViewColumn("Contraseña",render,text=2)
		columna4 = gtk.TreeViewColumn("E-Mail",render,text=3)
		columna5 = gtk.TreeViewColumn("Nombre",render,text=4)
		columna6 = gtk.TreeViewColumn("Apellido",render,text=5)
		columna7 = gtk.TreeViewColumn("Direccion",render,text=6)
		
		self.vista.set_model(listStore)
		self.vista.append_column(columna1)
		self.vista.append_column(columna2)
		self.vista.append_column(columna3)
		self.vista.append_column(columna4)
		self.vista.append_column(columna5)
		self.vista.append_column(columna6)
		self.vista.append_column(columna7)
				
		self.vista.show()
		
		window1.show_all()
	def on_buttonAdd_clicked(self, widget):
		#Nombre del otro archivo con la funciñón de añadir
		archivo = 'Añadir.py'
		subprocess.Popen(["python", archivo])
		#print("\n::>ERROR. No se encuentra el archivo < "+str(archivo)+" >")

	def on_buttonListar_clicked(self, widget):
		listStore.clear()
		try:
			self.cursor.execute('select * from usuarios')
		
			rows = self.cursor.fetchall()
		
			for row in rows:
				listStore.append([ row[0],row[1],row[2],row[3],row[4],row[5],row[6] ])
			   
	 		# Leemos todos los datos
			self.cursor.execute('SELECT count(*) FROM usuarios')
			    
			self.ctotal = self.widgets.get_widget('cTotal')
			    
			# leemos el sql y rellenamos la lista
			self.ctotal.set_text(str(self.cursor.fetchone()[0]))
		except sqlite3.Error , e:
			mensaje = "ERROR al refrescar los registros"
			print("\n:::>  "+mensaje)
		 
	def dialogoBorrar(self):
		# Obtener el widget del dialogo
       		self.dlg = self.widgets.get_widget("confirmBorrar")

		# ejecuta el dialogo y espera la respuesta
		self.result = self.dlg.run()
		
		# despues de ejecutado lo esconde
		self.dlg.hide()
		
		# devuelve el boton pulsado
		return self.result
		
	
	
	def on_buttonBorrar_clicked(self, widget):
		'''Evento cuando se pulsa borrar un cliente'''
		selection = self.vista.get_selection()
		selection.set_mode(gtk.SELECTION_SINGLE)
		# obtenemos el seleccionado
		self.model, path = selection.get_selected()
		# Si hay seleccionado se muestra el dialogo de confirmacion y se borra
		if(path != None):
		    respuesta = self.dialogoBorrar()
		    if(respuesta == 1):
		        valor = (self.model[path][0],)
			try:
				self.cursor.execute('DELETE FROM usuarios WHERE Id=?', valor)
				self.con.commit()
		        	print("\n:::>  Registro borrado correctamente")
			except sqlite3.Error , e:
				mensaje = "ERROR al borrar el registro"
				print("\n:::>  "+mensaje)
				
		else:
			ventana = gtk.Dialog()
			ok_button = ventana.add_button(gtk.STOCK_OK, gtk.RESPONSE_OK)
			ok_button.grab_default()
			ventana.set_title(".:: Info ::.")
			label = gtk.Label("\nNo has seleccionado ningun registro\n")
			#label.set_property("use-markup", True)
			ventana.vbox.pack_start(label, True, True, 0)
			ventana.show_all()
			ventana.run()
			ventana.destroy()
		
	def file_ok_sel(self, w):
   	        print "Abrir ::> %s" % self.filew.get_filename()

	def on_toolbuttonAbrir_clicked(self, widget):
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
	
	def on_buttonLimpiar_clicked(self, widget):
		listStore.clear()
		self.ctotal = self.widgets.get_widget('cTotal')
		self.ctotal.set_text("")

	def on_toolbuttonAcerca_clicked(self, widget):
		"Muestra la ventana Acerca de"
		about = gtk.AboutDialog()
		about.set_name("Control de usuarios en BDD ")
		about.set_version("2.0")
		about.set_comments("Añadir,Visualizar y Borrar registros\nFalta poder editarlos")
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
