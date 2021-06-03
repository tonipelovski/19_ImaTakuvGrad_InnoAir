# Отбор "Има такъв град"
## Име на проекта тук !

## Usage
From this point forth we are going to refer to ```~``` as the current repository folder.
Run the following python script that takes an image and sends it to an endpoint where the <your_server_ip> is your server ip and <take_picture_interval> is the time between taking a picture.  
```
python3 main.py --serverip <your_server_ip> --interval <take_picture_interval>
```
You can run this script on startup by adding it to the init.d
```
sudo cp ~/rpi/main.py /etc/init.d/
```
Then give it execution permissions
```
sudo chmod +x main.py
```
Finally run this command
```
sudo update-rc.d sample.py defaults
```
Reboot the device and the script will run on startup
```
sudo reboot
```

Централизирана система за анализ и оценка на пътникопотока в системата на масовият градски транспорт.


