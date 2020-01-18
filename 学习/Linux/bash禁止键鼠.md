$xinput list可以展示设备列表
```
⎡ Virtual core pointer                    	id=2	[master pointer  (3)]
⎜   ↳ Virtual core XTEST pointer              	id=4	[slave  pointer  (2)]
⎜   ↳ MSFT0001:00 06CB:CD3E Touchpad          	id=12	[slave  pointer  (2)]
⎜   ↳ SINO WEALTH USB KEYBOARD Mouse          	id=17	[slave  pointer  (2)]
⎣ Virtual core keyboard                   	id=3	[master keyboard (2)]
    ↳ Virtual core XTEST keyboard             	id=5	[slave  keyboard (3)]
    ↳ Power Button                            	id=6	[slave  keyboard (3)]
    ↳ Video Bus                               	id=7	[slave  keyboard (3)]
    ↳ Power Button                            	id=8	[slave  keyboard (3)]
    ↳ Integrated Camera: Integrated C         	id=9	[slave  keyboard (3)]
    ↳ Integrated Camera: Integrated I         	id=10	[slave  keyboard (3)]
    ↳ Ideapad extra buttons                   	id=11	[slave  keyboard (3)]
    ↳ AT Translated Set 2 keyboard            	id=13	[slave  keyboard (3)]
    ↳ SINO WEALTH USB KEYBOARD                	id=14	[slave  keyboard (3)]
    ↳ SINO WEALTH USB KEYBOARD Consumer Control	id=15	[slave  keyboard (3)]
    ↳ SINO WEALTH USB KEYBOARD System Control 	id=16	[slave  keyboard (3)]
```
通过上面可以得知AT Translated Set 2 keyboard是我的笔记本键盘的名字.  
而SINO WEALTH USB KEYBOARD是我外接键盘的名字.  
通过`$xinput list-props 'AT Translated Set 2 keyboard'`可以查看自带键盘的属性.  
```
Device 'AT Translated Set 2 keyboard':
	Device Enabled (148):	1
	Coordinate Transformation Matrix (150):	1.000000, 0.000000, 0.000000, 0.000000, 1.000000, 0.000000, 0.000000, 0.000000, 1.000000
	libinput Send Events Modes Available (270):	1, 0
	libinput Send Events Mode Enabled (271):	0, 0
	libinput Send Events Mode Enabled Default (272):	0, 0
	Device Node (273):	"/dev/input/event3"
	Device Product ID (274):	1, 1
```
其中device enabled : 1 就是是否使用该键盘的那个属性  
脚本如下:
```
#!/bin/bash

# 如果检测到有外接USB键盘鼠标则禁用笔记本键盘触摸板

SLEEP_TIME=5

# 自带的键盘触摸板名字 这里要用xinput list命令查看你的电脑自带键盘触摸板的名字
KEYBOARD_IN='AT Translated Set 2 keyboard'
# MOUSE_IN='ETPS/2 Elantech Touchpad'

# 外接的键盘触摸板名字，同上插拔外接键鼠，并用xinput list命令确定你的外接键鼠的名字
USB_KEYBOARD_OUT='SINO WEALTH USB KEYBOARD'
# USB_MOUSE_OUT='USB OPTICAL MOUSE'

while true
do
    # 处理键盘的逻辑
    HAVE_USB_KEYBOARD=`xinput list | grep "$USB_KEYBOARD_OUT"`
    if [ "" != "$HAVE_USB_KEYBOARD" ]; then
        if [ `xinput list-props "$KEYBOARD_IN" | grep "Device Enabled" | awk  -F ':' '{print $2}'` == 1 ]; then
            xinput set-prop "$KEYBOARD_IN" 'Device Enabled' 0
            # echo Disable keyboard
        fi
    else
        if [ `xinput list-props "$KEYBOARD_IN" | grep "Device Enabled" | awk  -F ':' '{print $2}'` == 0 ]; then
            xinput set-prop "$KEYBOARD_IN" 'Device Enabled' 1
            # echo Enable keyboard 
        fi
    fi

    # 处理键鼠标的逻辑
    # HAVE_USB_MOUSE=`xinput list | grep "$USB_MOUSE_OUT"`
    # if [ "" != "$HAVE_USB_MOUSE" ]; then
    #     if [ `xinput list-props "$MOUSE_IN" | grep "Device Enabled" | awk  -F ':' '{print $2}'` == 1 ]; then
    #         xinput set-prop "$MOUSE_IN" 'Device Enabled' 0
    #         # echo Disable Touchpad
    #     fi
    # else
    #     if [ `xinput list-props "$MOUSE_IN" | grep "Device Enabled" | awk  -F ':' '{print $2}'` == 0 ]; then
    #         xinput set-prop "$MOUSE_IN" 'Device Enabled' 1
    #         # echo Enable Touchpad
    #     fi
    # fi

    sleep $SLEEP_TIME
done
```
测试成功后,将编写的脚本放在~/.profile即可实现开机自启.

