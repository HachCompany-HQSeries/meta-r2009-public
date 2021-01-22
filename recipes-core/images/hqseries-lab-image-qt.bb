# Copyright (C) 2021 HACH Company
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "HACH Image for HQ Series Lab meters based on QT5."
LICENSE = "MIT"

inherit features_check populate_sdk_qt5

require recipes-core/images/hqseries-lab-image.bb

# Install fonts
QT5_FONTS = " \
    ttf-dejavu-mathtexgyre \
    ttf-dejavu-sans \
    ttf-dejavu-sans-condensed \
    ttf-dejavu-sans-mono \
    ttf-dejavu-serif \
    ttf-dejavu-serif-condensed \
"

# add more qml packages
QT5_QML_INSTALL = " \
	qtcharts-qmldesigner \
	qtcharts-qmlplugins \
	qtquickcontrols-qmldesigner \
	qtquickcontrols-qmlplugins \
	qtquickcontrols2-qmldesigner \
	qtquickcontrols2-qmlplugins \
	qtvirtualkeyboard-plugins \
	qtvirtualkeyboard-qmlplugins \
	qtdeclarative-tools \
"

# Install QT5 demo applications, fonts and wayland support.
QT5_IMAGE_INSTALL = " \
    ${QT5_FONTS} \
    ${QT5_QML_INSTALL} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland qtwayland-plugins', '', d)} \
"

QT5_IMAGE_INSTALL_append_imxgpu3d = " \
    packagegroup-qt5-3d \
"

# uncomment the following line to add webengine support
# but remind to add also meta-python2 to the bblayere
# QT5_IMAGE_INSTALL_append = " packagegroup-qt5-webengine"

# uncomment the following line to add webkit support
# but remind that is considered obsolete since Qt 5.7
# QT5_IMAGE_INSTALL_append = " packagegroup-qt5-webkit"

# Due to the Qt samples the resulting image will not fit the default NAND size.
# Removing default ubi creation for this image
IMAGE_FSTYPES_remove = "multiubi"

# HACH Specific.
IMAGE_INSTALL += " \
    ${QT5_IMAGE_INSTALL} \
"

# Remove all big components from image.
IMAGE_INSTALL_remove += " \
    packagegroup-qt5-webengine \
    packagegroup-qt5-webkit \
    packagegroup-qt5-demos \
    qtwebbrowser \
    qt5everywheredemo \
"

# export base name.
export IMAGE_BASENAME = "hqseries-lab-image-qt"
