import json
import httplib


def obtenerDatos(nombreTabla):
    connection = httplib.HTTPSConnection('api.parse.com', 443)
    connection.connect()
    connection.request('GET', '/1/classes/' + nombreTabla, '', {
        "X-Parse-Application-Id": "7fd4572Fnf1o4JDhU5Nm571XmL9EvwESk07Lzay8",
        "X-Parse-REST-API-Key": "kVED5jk3IL3yVYKiRH6yP8wvEKqvDtU6ZMXCZAJh"
    })
    retorno = json.loads(connection.getresponse().read())
    connection.close()
    return retorno

def escribirEnFichero(nombreFichero, datos):
    with open(nombreFichero, 'w') as outfile:
        json.dump(datos, outfile)

class ParseTable:
    nombreTabla = ""
    nombreFichero = ""

    def __init__(self, nombre):
        self.nombreTabla = nombre
        self.nombreFichero = nombre + '.json'


    def descargar_datos(self, ruta):
        escribirEnFichero(ruta, obtenerDatos(self.nombreTabla))

    def descargar_datos(self):
        escribirEnFichero(self.nombreFichero, obtenerDatos(self.nombreTabla))


