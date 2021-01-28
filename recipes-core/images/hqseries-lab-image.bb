# Copyright (C) 2021 HACH Company
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "HACH Image for HQ Series Lab meters for open source purpose."
LICENSE = "MIT"

inherit core-image features_check

# Remove distribution feature.
# NOTE:: For some reason if we use just wayland as distro then GUI application will fail to show up on display. We have to use XWAYLAND for that to work.
# DISTRO_FEATURES_remove += " virtualization"

# QEMU
#QEMU_TARGETS = "arm64 x86_64"
QEMU_TARGETS = ""

IMAGE_LINGUAS = "en-us"

IMAGE_FEATURES += " \
    splash \
    ssh-server-openssh \
    debug-tweaks \
    nfs-server \
    tools-debug \
    eclipse-debug \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
       bb.utils.contains('DISTRO_FEATURES',     'x11', 'x11-base x11-sato', \
                                                       '', d), d)} \
"

# HACH Specific.
IMAGE_INSTALL += " \
    zeromq \
    cppzmq \
    boost \
    sqlite3 \
    protobuf \
    cpio \
    \
    openssh-sftp-server \
    rsync \
    \
    htop \
    p7zip \
    tzdata \
    \
    nodejs \
    nodejs-npm \
    \
    fuse\
    zlib \
    \
    libxscrnsaver \
    nss \
    cups \
    gtk+3 \
    \
    at-spi2-atk \
    imx-gpu-viv \
    \
    libxcomposite \
    libxrandr \
    \
    chromium-ozone-wayland \
"

#packagegroup-core-x11-base
CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-core-full-cmdline \
	packagegroup-fsl-gstreamer1.0 \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xterm', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston-init', \
	   bb.utils.contains('DISTRO_FEATURES',     'x11', 'packagegroup-core-x11-sato-games', \
							 '', d), d)} \
	tcf-agent \
"

# only for Android enabled machines
#IMAGE_INSTALL_append_imxgpu3d = " \
#	android-tools \
#"

# only for DRM enabled machines
#IMAGE_INSTALL_append_imxdrm = " \
#	libdrm-tests \
#"

#CORE_IMAGE_EXTRA_INSTALL_append_mx8 = "\
#    packagegroup-fsl-tools-gpu \
#"

# Remove Wayland, wireless and sound related packages.
# NOTE - SOC_PACKAGES contains only IMX graphics demo tools and examples. We do not need them.
IMAGE_FEATURES_remove += " \
    evtest \
    fbtest \
    i2c-tools \
    memwatch \
    tcpdump \
    ethtool \
    ssh-server-dropbear \
    \
    hwcodecs \
"

# Remove all big components from image.
IMAGE_INSTALL_remove += " \
    packagegroup-fsl-tools-testapps \
    packagegroup-tools-bluetooth \
    packagegroup-imx-tools-audio \
    packagegroup-fsl-tools-gpu \
    packagegroup-fsl-gstreamer1.0-full \
    packagegroup-fsl-gstreamer1.0 \
    gstreamer1.0 \
"

# Disable virtual terminal on primary display.
systemd_disable_vt () {
    rm ${IMAGE_ROOTFS}${root_prefix}${sysconfdir}/systemd/system/getty.target.wants/getty@tty*.service
}

IMAGE_PREPROCESS_COMMAND_append = " ${@ 'systemd_disable_vt;' if bb.utils.contains('DISTRO_FEATURES', 'systemd', True, False, d) and bb.utils.contains('USE_VT', '0', True, False, d) else ''} "

# export base name.
#export IMAGE_BASENAME = "hqseries-lab-image"
