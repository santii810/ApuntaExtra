#!/usr/bin/python
from Email import Email
from Parse import ParseTable
import zipfile
from datetime import datetime
from os import remove

tablas = {
    'Locales',
    'Cocinas',
    'Camareros',
    'Usuarios'
}

nombreZip = 'BD_Parse_' + datetime.now().strftime('%y_%m_%d-%Hh') + '.zip'
zf = zipfile.ZipFile(nombreZip, 'w')
try:
    for item in tablas:
        ParseTable(item).descargar_datos()
        zf.write(ParseTable(item).nombreFichero)
finally:
    zf.close()
    for item in tablas:
        remove(ParseTable(item).nombreFichero)

email = Email()
email.asunto = nombreZip.split('.')[0]
email.fileName = nombreZip
email.send_mail()

