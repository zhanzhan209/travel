# -*- coding: utf-8 -*-
import urllib2
import time
import socket

url = "https://github.com"

def run_test():
    print("[*] Starting Outbound Connectivity Test")
    try:
        ip = socket.gethostbyname(socket.gethostname())
        print("[*] Source Internal IP: %s" % ip)
    except:
        pass

    for i in range(1, 4):
        try:
            response = urllib2.urlopen(url, timeout=10)
            print("[Iteration %d] Success! Code: %d" % (i, response.getcode()))
        except Exception as e:
            print("[Iteration %d] Event: %s" % (i, str(e)))
        time.sleep(1)

if __name__ == "__main__":
    run_test()
