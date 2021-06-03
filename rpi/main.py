import sys, getopt
import time
import os
import requests

def main(argv):
    server_ip = ''
    send_image_interval = ''
    try:
        opts, args = getopt.getopt(argv, "hi:n:", ["serverip=", "interval="])
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

    while True:
        print('taking picture...')
        take_picture()
        print(f'sending image to endpoint {server_ip}/image/upload')
        send_image_to_endpoint(server_ip)
        time.sleep(send_image_interval)

def take_picture():
    os.system('fswebcam -r 1920x1080 --no-banner image.jpg')

def send_image_to_endpoint(server_ip):
    try:
        url = f'http://{server_ip}/image/upload'
        files = {'media': open('image.png', 'rb')}
        response = requests.post(url, files=files)
        print(response)
    except requests.exceptions.RequestException as e:  # This is the correct syntax
        print(e)


if __name__ == "__main__":
    main(sys.argv[1:])
