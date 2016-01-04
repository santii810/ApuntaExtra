#!/usr/bin/python
import json, httplib


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


tablas = {'Locales',
          'Usuarios',
          'Camareros',
          'Cocinas'}

for nombreTabla in tablas:
    escribirEnFichero(nombreTabla + '.json', obtenerDatos(nombreTabla))
