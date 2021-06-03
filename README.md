# Отбор "Има такъв град"
## Очко

Събиране на информация чрез Raspberry Pi и Web Camera.

![image](https://user-images.githubusercontent.com/21009499/120721233-10de9480-c4d6-11eb-8d75-bf4f88fefc20.png)

Лицево разпознаване на броя на пътници чрез Google Cloud Vision API, което използва машинно самообучение.

![image](https://user-images.githubusercontent.com/21009499/120721084-d37a0700-c4d5-11eb-8eee-7f7175d6fd13.png)

Централизирана система за анализ и оценка на пътникопотока в системата на масовият градски транспорт.

![image](https://user-images.githubusercontent.com/21009499/120720755-4a62d000-c4d5-11eb-9525-cd0d30ad3acf.png)


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
