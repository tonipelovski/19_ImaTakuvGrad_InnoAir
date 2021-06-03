import sys, getopt
import time
import os
import requests
import base64

debug = False

def main(argv):
    server_ip = ''
    send_image_interval = ''
    try:
        opts, args = getopt.getopt(argv, "hdi:n:", ["serverip=", "interval=", "debug="])
    except getopt.GetoptError:
        print('main.py -i <ip>')
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print('main.py -i <ip>')
            sys.exit()
        elif opt in ("-i", "--serverip"):
            server_ip = arg
            print(f'Server ip: {server_ip}')
        elif opt in ("-n", "--interval"):
            send_image_interval = int(arg)
            print(f'Send image interval: {send_image_interval}')
        elif opt in ("-d", "--debug"):
            global debug
            debug = True

    while True:
        if debug:
            print('taking picture...')
        take_picture()
        if debug:
            print(f'sending image to endpoint {server_ip}/images')
        send_image_to_endpoint(server_ip)
        time.sleep(send_image_interval)

def take_picture():
    os.system('fswebcam -r 640x480 --no-banner image.png')

def send_image_to_endpoint(server_ip):
    try:
        url = f'http://{server_ip}:8080/images'
        with open("image.png", "rb") as img_file:
            encoded_image = base64.b64encode(img_file.read())
        payload = {'data': encoded_image}
        if debug:
            print(url)
        response = requests.post(url, data=payload, params={"vehicleId": 1})
        if debug:
            print(response)
    except requests.exceptions.RequestException as e:  # This is the correct syntax
        print(e)


if __name__ == "__main__":
    main(sys.argv[1:])
