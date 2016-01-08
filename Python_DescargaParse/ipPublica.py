#!/usr/bin/python
import os
from Email import Email

ip= os.popen('curl icanhazip.com').read()
email = Email()
email.asunto = 'IpPublica ' + ip
email.send_mail()
