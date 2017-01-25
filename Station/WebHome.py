#!/usr/bin/env python
import json

QR_CODE_TAG_1 = "<img src='https://chart.googleapis.com/chart?cht=qr&chl="
QR_CODE_MESSGE = ""
QR_CODE_TAG_2 = "&chs=180x180&choe=UTF-8&chld=L|2' alt='qr code' style='height:400px; width:400px;'><a href='http://www.qrcode-generator.de' border='0' style='cursor:default'  rel='nofollow'></a>"

with open('Settings.json') as data_file:    
		data = json.load(data_file)
		QR_CODE_MESSGE = json.dumps(data)

print "Content-type: text/html"
print
print """<div class="widget">
    <center><h2 class="title" margin-bottom: 0px>eLock Station V1.0</h2>
    <div class="qr">%s%s%s</div>
    <a href="http://phelps17.github.io">Created by Tyler Phelps</a></center>
</div>""" % (QR_CODE_TAG_1, QR_CODE_MESSGE, QR_CODE_TAG_2)