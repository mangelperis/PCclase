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


class MainWin:
	def __init__(self):
		global edicion		
		try:
			self.con = sqlite3.connect(bbdd)
			global cursor
		   	self.cursor = self.con.cursor()
		    		    
		except sqlite3.Error , e:
			#No se porque este error nunca salta
			self.error("La BBDD no esta disponible.\n Confirma que existe el archivo '"+str(bdd)+"'.bdd")
		        print("La BBDD no esta disponible.\n Confirma que existe el archivo '"+str(bdd)+"'.bdd")
		
		self.widgets  = gtk.glade.XML("Usuarios.glade")
		
		window1 = self.widgets.get_widget("window1")
		#TITULO DE LA VENTANA
		window1.set_title("Administración de Usuarios")
		
		#Color de fondo
		window1.modify_bg(gtk.STATE_NORMAL, gtk.gdk.Color('#A0B8BC'))
		# Creamos un pequeño diccionario que contiene las señales definidas en
		# glade y su respectivo método (o llamada)
		signals = {"gtk_main_quit" : gtk.main_quit,
			"on_buttonCancelar_clicked" : self.on_buttonCancelar_clicked,
			"on_treeviewUsers_cursor_changed" : self.on_treeviewUsers_cursor_changed,
			"on_Nuevo_clicked": self.on_Nuevo_clicked,
			"on_Editar_clicked": self.on_Editar_clicked,
			"on_Limpiar_clicked": self.on_Limpiar_clicked,
			"on_Borrar_clicked": self.on_Borrar_clicked,
			"on_Guardar_clicked": self.on_Guardar_clicked,
			"on_Actualizar_clicked": self.on_Actualizar_clicked,
			"on_buttonSalir_clicked" : gtk.main_quit
			 }
		
		# Luego se auto-conectan las señales.
		self.widgets.signal_autoconnect(signals)

		self.vista = self.widgets.get_widget("treeviewUsers")
		global listStore
		listStore = gtk.ListStore(str,str,str,str)
		render = gtk.CellRendererText()
		columna1 = gtk.TreeViewColumn("ID",render,text=0)
		columna2 = gtk.TreeViewColumn("Nombre",render,text=1)
		columna3 = gtk.TreeViewColumn("Password",render,text=2)
		columna4 = gtk.TreeViewColumn("E-Mail",render,text=3)
		
		
		self.vista.set_model(listStore)
		self.vista.append_column(columna1)
		self.vista.append_column(columna2)
		self.vista.append_column(columna3)
		self.vista.append_column(columna4)
						
		self.vista.show()
				
		window1.show_all()

	def on_buttonCancelar_clicked(self,widget):
				
		#Activar la edicion
		self.widgets.get_widget("entryNombre").set_editable(False)
		self.widgets.get_widget("entryNombre").set_text("")
		self.widgets.get_widget("entryPassword").set_text("")
		self.widgets.get_widget("entryPassword").set_editable(False)
		self.widgets.get_widget("entryMail").set_editable(False)
		self.widgets.get_widget("entryMail").set_text("")
		self.widgets.get_widget("Nuevo").set_sensitive(True)
		self.widgets.get_widget("Editar").set_sensitive(True)
		self.widgets.get_widget("Actualizar").set_sensitive(True)
		self.widgets.get_widget("Borrar").set_sensitive(True)
		self.widgets.get_widget("Guardar").set_sensitive(False)
		self.widgets.get_widget("buttonCancelar").set_sensitive(False)
		self.widgets.get_widget("label3").set_text("<b>Información  General</b>")
		self.widgets.get_widget("label3").set_property("use-markup", True)
		self.widgets.get_widget("labelId").set_text("")
	def on_Nuevo_clicked(self, widget):
		ventana = gtk.Dialog()
		ok_button = ventana.add_button(gtk.STOCK_OK, gtk.RESPONSE_OK)
		ok_button.grab_default()
		ventana.set_title(".:: Instrucciones ::.")
		label = gtk.Label("\n1º > Introduce los datos\n2º > Confirma el registro con el botón de 'Guardar'\n\n<b><i>*Se requieren todos los campos</i></b>")
		label.set_property("use-markup", True)
		ventana.vbox.pack_start(label, True, True, 0)
		ventana.show_all()
		ventana.run()
		ventana.destroy()
		listStore.clear()
		self.widgets.get_widget("labelId").set_text("")
		#Activar la edicion
		self.widgets.get_widget("entryNombre").set_editable(True)
		self.widgets.get_widget("entryNombre").set_text("")
		self.widgets.get_widget("entryPassword").set_text("")
		self.widgets.get_widget("entryPassword").set_editable(True)
		self.widgets.get_widget("entryMail").set_editable(True)
		self.widgets.get_widget("entryMail").set_text("")
		self.widgets.get_widget("label3").set_text("<b>Información  General</b> .::. <i>Modo añadir</i> .::.")
		self.widgets.get_widget("label3").set_property("use-markup", True)
		self.widgets.get_widget("cTotal").set_text("")
		self.widgets.get_widget("Nuevo").set_sensitive(False)
		self.widgets.get_widget("Editar").set_sensitive(False)
		self.widgets.get_widget("Actualizar").set_sensitive(False)
		self.widgets.get_widget("Borrar").set_sensitive(False)
		self.widgets.get_widget("Guardar").set_sensitive(True)
		self.widgets.get_widget("buttonCancelar").set_sensitive(True)
		
		
	def on_Guardar_clicked(self, widget):
		entry1 = self.widgets.get_widget("entryNombre").get_text()
		entry2 = self.widgets.get_widget("entryPassword").get_text()
		entry3 = self.widgets.get_widget("entryMail").get_text()
		if(self.widgets.get_widget("label3").get_text() != "Información  General .::. Modo edicion .::."):
			#ESTOY PULSANDO NUEVO REGISTRO
			if (str(entry1) == "" or str(entry2) == "" or str(entry2) == "" ):
				ventana = gtk.Dialog()
				ok_button = ventana.add_button(gtk.STOCK_OK, gtk.RESPONSE_OK)
				ok_button.grab_default()
				ventana.set_title(".:: ERROR ::.")
				label = gtk.Label("\n<u>Todos</u> los campos son <b>requeridos</b>\n")
				label.set_property("use-markup", True)
				ventana.vbox.pack_start(label, True, True, 0)
				ventana.show_all()
				ventana.run()
				ventana.destroy()
			else:
				try:	
			
					sql = "INSERT INTO usuarios (Nombre,Password,Mail) VALUES ('%s','%s','%s')" % (entry1, entry2, entry3)
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

					listStore.clear()
					self.ctotal = self.widgets.get_widget('cTotal')
					self.ctotal.set_text("")
					#Desactivar la edicion
					self.widgets.get_widget("entryNombre").set_editable(False)
					self.widgets.get_widget("entryNombre").set_text("")
					self.widgets.get_widget("entryPassword").set_text("")
					self.widgets.get_widget("entryPassword").set_editable(False)
					self.widgets.get_widget("entryMail").set_editable(False)
					self.widgets.get_widget("entryMail").set_text("")				
					self.widgets.get_widget("labelId").set_text("")
					self.widgets.get_widget("Nuevo").set_sensitive(True)
					self.widgets.get_widget("Editar").set_sensitive(True)
					self.widgets.get_widget("Actualizar").set_sensitive(True)
					self.widgets.get_widget("Guardar").set_sensitive(False)
					self.widgets.get_widget("Borrar").set_sensitive(True)
					self.widgets.get_widget("buttonCancelar").set_sensitive(False)

					self.widgets.get_widget("label3").set_text("<b>Información  General</b>")
					self.widgets.get_widget("label3").set_property("use-markup", True)
				except sqlite3.Error , e:
					mensaje = "ERROR al introducir el registro"
					print("\n:::>  "+mensaje)
		else:
			#ESTOY PULSANDO EDITAR 
			if (str(entry1) == "" or str(entry2) == "" or str(entry2) == "" ):
				ventana = gtk.Dialog()
				ok_button = ventana.add_button(gtk.STOCK_OK, gtk.RESPONSE_OK)
				ok_button.grab_default()
				ventana.set_title(".:: ERROR ::.")
				label = gtk.Label("\n<u>Todos</u> los campos son <b>requeridos</b>\n")
				label.set_property("use-markup", True)
				ventana.vbox.pack_start(label, True, True, 0)
				ventana.show_all()
				ventana.run()
				ventana.destroy()
			else:
				try:	
					
					#Esto viene de treeViewSelection como variable Global
					entry4 = int(ID)

					sql ="UPDATE usuarios SET Nombre='%s',Password='%s',Mail='%s' WHERE Id='%s'" %(entry1,entry2,entry3,entry4)			
					self.cursor.execute(sql)
					self.con.commit()
		
					print("\n:::>  Registro Modificado correctamente")

					#Pop-UP de confirmacion
					ventana = gtk.Dialog()
					ok_button = ventana.add_button(gtk.STOCK_OK, gtk.RESPONSE_OK)
					ok_button.grab_default()
					ventana.set_title("Info")
					label = gtk.Label("\nRegistro Modificado\n <b><u>correctamente</u></b>\n")
					label.set_property("use-markup", True)
					ventana.vbox.pack_start(label, True, True, 0)
					ventana.show_all()
					ventana.run()
					ventana.destroy()

					listStore.clear()
					self.ctotal = self.widgets.get_widget('cTotal')
					self.ctotal.set_text("")
					#Desactivar la edicion
					self.widgets.get_widget("entryNombre").set_editable(False)
					self.widgets.get_widget("entryNombre").set_text("")
					self.widgets.get_widget("entryPassword").set_text("")
					self.widgets.get_widget("entryPassword").set_editable(False)
					self.widgets.get_widget("entryMail").set_editable(False)
					self.widgets.get_widget("entryMail").set_text("")				
					self.widgets.get_widget("labelId").set_text("")
					self.widgets.get_widget("Nuevo").set_sensitive(True)
					self.widgets.get_widget("Editar").set_sensitive(True)
					self.widgets.get_widget("Actualizar").set_sensitive(True)
					self.widgets.get_widget("Borrar").set_sensitive(True)
					self.widgets.get_widget("Guardar").set_sensitive(False)
					self.widgets.get_widget("buttonCancelar").set_sensitive(False)

					self.widgets.get_widget("label3").set_text("<b>Información  General</b>")
					self.widgets.get_widget("label3").set_property("use-markup", True)
				except sqlite3.Error , e:
					mensaje = "ERROR al modificar el registro"
					print("\n:::>  "+mensaje)
			
			
	def on_Editar_clicked(self, widget):
		entry1 = self.widgets.get_widget("entryNombre").get_text()
		entry2 = self.widgets.get_widget("entryPassword").get_text()
		entry3 = self.widgets.get_widget("entryMail").get_text()
		
		if (str(entry1) == "" or str(entry2) == "" or str(entry2) == "" ):
			ventana = gtk.Dialog()
			ok_button = ventana.add_button(gtk.STOCK_OK, gtk.RESPONSE_OK)
			ok_button.grab_default()
			ventana.set_title(".:: Info ::.")
			label = gtk.Label("\n<u>Debes</u> seleccionar el registro a <b>editar</b>\n")
			label.set_property("use-markup", True)
			ventana.vbox.pack_start(label, True, True, 0)
			ventana.show_all()
			ventana.run()
			ventana.destroy()
		else:
			ventana = gtk.Dialog()
			ok_button = ventana.add_button(gtk.STOCK_OK, gtk.RESPONSE_OK)
			ok_button.grab_default()
			ventana.set_title(".:: Instrucciones ::.")
			label = gtk.Label("\n1º > Modifica los datos\n2º > Confirma el registro con el botón de 'Guardar'\n\n<b><i>*Se requieren todos los campos</i></b>")
			label.set_property("use-markup", True)
			ventana.vbox.pack_start(label, True, True, 0)
			ventana.show_all()
			ventana.run()
			ventana.destroy()
			listStore.clear()
			#Activar la edicion
			self.widgets.get_widget("entryNombre").set_editable(True)
			self.widgets.get_widget("entryPassword").set_editable(True)
			self.widgets.get_widget("entryMail").set_editable(True)
			self.widgets.get_widget("cTotal").set_text("")
			self.widgets.get_widget("label3").set_text("<b>Información  General</b> .::. <i>Modo edicion</i> .::.")
			self.widgets.get_widget("label3").set_property("use-markup", True)
			self.widgets.get_widget("Nuevo").set_sensitive(False)
			self.widgets.get_widget("Guardar").set_sensitive(True)
			self.widgets.get_widget("Editar").set_sensitive(False)
			self.widgets.get_widget("Actualizar").set_sensitive(False)
			self.widgets.get_widget("Borrar").set_sensitive(False)
			self.widgets.get_widget("buttonCancelar").set_sensitive(True)
					
		

	def on_treeviewUsers_cursor_changed(self, widget):
		'''Evento cuando se pulsa un elemento del treeview'''
		selection = self.vista.get_selection()
		selection.set_mode(gtk.SELECTION_SINGLE)
		# obtenemos el seleccionado
		self.model, path = selection.get_selected()
		self.widgets.get_widget("entryNombre").set_text(self.model[path][1])
		self.widgets.get_widget("entryPassword").set_text(self.model[path][2])
		self.widgets.get_widget("entryMail").set_text(self.model[path][3])
		#PASAR EL VALOR DEL ID SELECCIONADO PARA LA EDICION
		global cId
		global ID
		#Este va para el UPDATE
		ID = self.model[path][0]
		#Este va para las etiquetas
		cId = "ID:: "+self.model[path][0]
		self.widgets.get_widget("labelId").set_text(str(cId))
		
	def on_Limpiar_clicked(self, widget):
		listStore.clear()
		self.ctotal = self.widgets.get_widget('cTotal')
		self.ctotal.set_text("")
		self.widgets.get_widget("entryNombre").set_text("")
		self.widgets.get_widget("entryPassword").set_text("")
		self.widgets.get_widget("entryMail").set_text("")
		self.widgets.get_widget("labelId").set_text("")

	def on_Actualizar_clicked(self, widget):
		listStore.clear()
		self.widgets.get_widget("entryNombre").set_text("")
		self.widgets.get_widget("entryPassword").set_text("")
		self.widgets.get_widget("entryMail").set_text("")
		self.widgets.get_widget("labelId").set_text("")
		try:
			self.cursor.execute('select * from usuarios')
		
			rows = self.cursor.fetchall()
		
			for row in rows:
				listStore.append([ row[0],row[1],row[2],row[3] ])
			   
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
		
	
	
	def on_Borrar_clicked(self, widget):
		'''Evento cuando se pulsa borrar un usuario'''
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
		
	'''def file_ok_sel(self, w):
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
	'''
	

	'''def on_toolbuttonAcerca_clicked(self, widget):
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
	'''
	


# Para terminar iniciamos el programa
if __name__ == "__main__":
	MainWin()
	gtk.main()
