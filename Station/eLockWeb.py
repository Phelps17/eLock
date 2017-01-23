#!/usr/bin/env python
import json

QR_CODE_TAG_1 = "<img src='https://chart.googleapis.com/chart?cht=qr&chl="
QR_CODE_MESSGE = ""
QR_CODE_TAG_2 = "&chs=180x180&choe=UTF-8&chld=L|2' alt='qr code'><a href='http://www.qrcode-generator.de' border='0' style='cursor:default'  rel='nofollow'></a>"

with open('Settings.json') as data_file:    
		data = json.load(data_file)
		QR_CODE_MESSGE = json.dumps(data)

print "Content-type: text/html"
print
print "<title>eLock Station V1.0</title>"
print "<center><table border=0 cellspacing=0 cellpadding=0>"
print "<tr><td><h2>eLock Station V1.0</h2></td></tr>"
print "<tr><td>By Tyler Phelps</td></tr>"
print "<tr><td>%s%s%s</td></tr>" % (QR_CODE_TAG_1, QR_CODE_MESSGE, QR_CODE_TAG_2)
#TODO: give this button functionality
print """<tr><td><form action="" method="get">
<input type="submit" value="UNLOCK" />
</form></td></tr>"""
print "</table> </center>"
    