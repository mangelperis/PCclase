#! /usr/bin/env python
# -*- coding: UTF-8 -*-

""" Para que la cosa compile!!!:

    cambiar <interface> por <glade-interface>
    comentar <!-- interface-requires gtk+ 2.16 -->
    cambiar los <object> por <widget>
    quitar el atributo swapped de las <signal> aunque se regenera al grabar otra vez el glade"""

import pygtk
pygtk.require("2.0")
import gtk
import gtk.glade
import sqlite3

bbdd = 'BaseDatosFormularioDI'
conex = sqlite3.connect(bbdd) #da error en eclipse pero funciona igual
c = conex.cursor() 


class MainWin: 

    def __init__(self):
        
        self.widgets= gtk.glade.XML("Practica2.glade")
        
        #Diccionario con Signals(llamadas) y Handlers(metodos) de Glade
        signals = {"on_buttonListar_clicked" : self.on_buttonListar_clicked,
                   "on_buttonGuardar_clicked" : self.on_buttonGuardar_clicked,
                   "on_buttonLimpiar_clicked" : self.on_buttonLimpiar_clicked,
                   "on_buttonSalir_clicked" : self.on_buttonSalir_clicked,
                   "gtk_main_quit" : gtk.main_quit }
        
        #Conectar las señales
        self.widgets.signal_autoconnect(signals)
        
        #Obtener los objetos del archivo Glade
        self.label1 = self.widgets.get_widget("label1")
        self.entryUsuario = self.widgets.get_widget("entryUsuario")
        self.entryContrasena = self.widgets.get_widget("entryContrasena")
        self.entryMail = self.widgets.get_widget("entryMail")
        self.entryNombre = self.widgets.get_widget("entryNombre")
        self.entryApellido = self.widgets.get_widget("entryApellido")
        self.textviewDireccion = self.widgets.get_widget("textviewDireccion")

        
    def on_buttonGuardar_clicked(self, widget):
        
        #Relleno automatico de texto deprueba
        usuario = self.entryUsuario.get_text()
        password=self.entryContrasena.get_text()
        mail=self.entryMail.get_text()
        nombre=self.entryNombre.get_text()
        apellido=self.entryApellido.get_text()
        #El textview es un poco especialito y necesita 2 metodos
        textbuffer=self.textviewDireccion.get_buffer()        
        direccion=textbuffer.get_text(*textbuffer.get_bounds())
        
        #Mensage emergente
        message = gtk.MessageDialog(parent=None, 
                            type=gtk.MESSAGE_INFO, 
                            buttons=gtk.BUTTONS_YES_NO)
        message.set_markup("¿Guardar cambios antes de salir?")
        
        response = message.run();    
        message.destroy()
        
        #Resultado del mensage si/no
        if response == gtk.RESPONSE_YES:
            self.grabar_user(bbdd,usuario,password,mail,nombre,apellido,direccion)
            print("Has dicho si")
        else:
            print("Has dicho no")
  
    def on_buttonLimpiar_clicked(self,widget):

        self.entryUsuario.set_text("")
        self.entryContrasena.set_text("")
        self.entryMail.set_text("")
        self.entryNombre.set_text("")
        self.entryApellido.set_text("")   
        #El textview es un poco especialito y necesita 2 metodos
        textbuffer=self.textviewDireccion.get_buffer()        
        textbuffer.set_text("")
        print("limpiando")

    def on_buttonSalir_clicked(self,widget):  
        gtk.main_quit()  
        print("saliendo")
     
    def on_buttonListar_clicked(self,widget): 
        
        #Consultas columna por columna a la base de datos
        
        c.execute('select usuario from usuarios')
        usuarios=""
        rows = c.fetchall()
        for row in rows:
            usuarios=usuarios+''.join(row)+"\n"

        c.execute('select password from usuarios')
        password=""
        rows = c.fetchall()
        for row in rows:
            password=password+''.join(row)+"\n"
               
        c.execute('select mail from usuarios')
        mail=""
        rows = c.fetchall()
        for row in rows:
            mail=mail+''.join(row)+"\n"
                     
        c.execute('select nombre from usuarios')
        nombre=""
        rows = c.fetchall()
        for row in rows:
            nombre=nombre+''.join(row)+"\n"
               
        c.execute('select apellido from usuarios')
        apellido=""
        rows = c.fetchall()
        for row in rows:
            apellido=apellido+''.join(row)+"\n"
        
        c.execute('select direccion from usuarios')
        direccion=""
        rows = c.fetchall()
        for row in rows:
            direccion=direccion+''.join(row)+"\n"
        
        #Creacion de la ventana
        ventana = gtk.Window()
                
        #Creacion de las label
        labelUsuarios = gtk.Label("<b>Usuarios</b>\n\n"+usuarios)
        labelUsuarios.set_property("use-markup", True)
        
        labelPassword = gtk.Label("<b>Contraseña</b>\n\n"+password)
        labelPassword.set_property("use-markup", True)
        
        labelmail = gtk.Label("<b>Mail</b>\n\n"+mail)
        labelmail.set_property("use-markup", True)
        
        labelNombre = gtk.Label("<b>Nombre</b>\n\n"+nombre)
        labelNombre.set_property("use-markup", True)
                
        labelApellido = gtk.Label("<b>Apellido</b>\n\n"+apellido)
        labelApellido.set_property("use-markup", True)
  
        labelDireccion = gtk.Label("<b>Direccion</b>\n\n"+direccion)
        labelDireccion.set_property("use-markup", True)
        
        #Hbox para que aparezcan una al lado de la otra con borde 20
        hbox = gtk.HBox(False, 20)

        hbox.pack_start(labelUsuarios, False, False, 0)
        hbox.pack_start(labelPassword, False, False, 0)
        hbox.pack_start(labelmail, False, False, 0)
        hbox.pack_start(labelNombre, False, False, 0)
        hbox.pack_start(labelApellido, False, False, 0)
        hbox.pack_start(labelDireccion, False, False, 0)

        ventana.set_border_width(10)
        ventana.add(hbox)
        ventana.show_all()

    
    def grabar_user(self,bbdd,usuario,password,mail,nombre,apellido,direccion):

        c.execute('''INSERT INTO usuarios(usuario,password,mail,nombre,apellido,direccion) VALUES(?,?,?,?,?,?)''',(usuario,password,mail,nombre,apellido,direccion))

        conex.commit()
    
if __name__ == "__main__":
    MainWin()
    gtk.main()
    
