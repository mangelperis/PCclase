#! /usr/bin/env python
# -*- coding: UTF-8 -*-

# Importamos el módulo pygtk y le indicamos que use la versión 2 (en caso de  que existan varias versiones de pygtk instaladas en el sistema)
import pygtk
pygtk.require("2.0")
# Luego importamos el módulo de gtk para poder acceder a los controles de Gtk+
import gtk
# Creamos una clase que contenga la ventana principal del programa y los
# métodos de cada una las señales
class MainWin:
# Primero definimos como sera la ventana:
	def __init__(self):
# Creamos una ventana toplevel (o sea que esta al frente de todas las
# ventanas) llamada "main_win" y fijamos su titulo como "Ejemplo 1"
		main_win = gtk.Window(gtk.WINDOW_TOPLEVEL)
		main_win.set_title("Ejemplo 1")
# A main_win le conectamos una señal (destroy), esto hará que cada
# vez que se presione el botón salir (la cruz del manejador de
# ventanas) se llamará al método on_quit que cerrara la ventana27

		main_win.connect("destroy", self.on_quit)
# Para agregar widgets (controles como botones, etiquetas, etc.) a la
# ventana, primero es necesario crear contenedores como cajas que
# contengan las widgets. En este ejemplo creamos una caja vertical con
# un espacio entre widgets de 5px y con la propiedad homogéneo en False
		vbox = gtk.VBox(False, 5)
# Creamos una etiqueta con el texto "Hola mundo", se usa la palabra
# reservada "self" de python para poder hacer referencia a esta
# etiqueta desde otros métodos
		self.label = gtk.Label("Hola mundo")
# Creamos un cuadro donde escribir (una entrada de texto en blanco)
# y luego le conectamos la señal "activate" que llama al método
# "on_button1_clicked", esto producirá que cuando se haga click en el
# botón Ok (que se creara mas adelante) la entrada de texto reaccione
		self.entry = gtk.Entry()
		self.entry.connect("activate", self.on_button1_clicked)
# Ahora creamos el botón, que sera el botón OK del inventario de
# botones de GNOME.
# Y luego le indicamos al botón que cuando le hagan click emita la
# señal "clicked" que llamará a "on_button1_clicked"
		button = gtk.Button(stock=gtk.STOCK_OK)
		button.connect("clicked", self.on_button1_clicked)
# Ahora que ya creamos las widgets (la etiqueta, la entrada de texto y
# el botón) hay que añadirlos a la caja vertical creada anteriormente
# Primero le añadimos la etiqueta llamada label a la caja vertical
		vbox.add(self.label)
# Luego añadimos al inicio de la segunda fila la entrada de texto
# activando las propiedades de expandir, rellenar y espaciado.
		vbox.pack_start(self.entry, True, True, 0)
# Finalmente en la tercer fila agregamos el botón.
		vbox.pack_start(button, False, False, 0)
# Ahora agregamos la caja vertical a la ventana y luego se muestra
# la caja (y todo lo que contiene) en la ventana principal.
		main_win.add(vbox)
		main_win.show_all()
# Ahora dentro de nuestra clase principal "MainWin" tenemos que definir74 # que hacen cada uno de los métodos que se llamaron anteriormente

# Primero definamos el método "on_button1_clicked"
	def on_button1_clicked(self, widget):

# Primero obtenemos el texto que se escriba en la entrada de texto

		texto = self.entry.get_text()

# Luego fijamos ese texto a la etiqueta de la forma "Hola texto".

		self.label.set_text("Hola %s" % texto)

  #Ahora se define el método "on_quit" que destruye la aplicación
	def on_quit(self, widget):

		gtk.main_quit()


# Para terminar iniciamos el programa
if __name__ == "__main__":
 # Iniciamos la clase.
	MainWin()
 # Además iniciamos el método gtk.main, que genera un ciclo que se utiliza para recibir todas las señales emitidas por los botones y demás widgets.
	gtk.main()
