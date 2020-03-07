# Windows10 更新覆盖Ubuntu grub2 解决办法

1. 开机之后直接进入windows10系统
2. 管理员启动命令提示符(cmd)
3. mountval g: /s
4. cd g:
5. cd efi
6. dir命令可以看到ubuntu子目录
7. bcdedit /set {bootmgr} path \EFI\ubuntu\grubx64.efi
