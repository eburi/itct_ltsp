TARGETS = console-setup mountkernfs.sh resolvconf alsa-utils ufw plymouth-log screen-cleanup x11-common hostname.sh pppd-dns apparmor udev keyboard-setup mountdevsubfs.sh procps brltty networking urandom hwclock.sh checkroot.sh nbd-client mountnfs.sh mountnfs-bootclean.sh bootmisc.sh kmod mountall.sh checkfs.sh checkroot-bootclean.sh mountall-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
procps: mountkernfs.sh udev
brltty: mountkernfs.sh udev
networking: mountkernfs.sh urandom resolvconf procps
urandom: hwclock.sh
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
nbd-client: networking
mountnfs.sh: nbd-client networking
mountnfs-bootclean.sh: mountnfs.sh
bootmisc.sh: mountnfs-bootclean.sh checkroot-bootclean.sh udev mountall-bootclean.sh
kmod: checkroot.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh
checkfs.sh: checkroot.sh
checkroot-bootclean.sh: checkroot.sh
mountall-bootclean.sh: mountall.sh
