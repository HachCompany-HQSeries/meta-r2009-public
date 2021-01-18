### WARNING: This image is NOT suitable for production use and is intended
###          to provide a way for users to reproduce the image used during
###          the validation process of i.MX BSP releases.

#IMAGE_FEATURES += ""

#CORE_IMAGE_EXTRA_INSTALL += ""

# only for Android enabled machines
#IMAGE_INSTALL_append_imxgpu3d = ""

# only for DRM enabled machines
#IMAGE_INSTALL_append_imxdrm = ""

#CORE_IMAGE_EXTRA_INSTALL_append_mx8 = ""

IMAGE_INSTALL += " \
    zeromq \
    cppzmq \
    boost \
    \
    openssh-sftp-server \
    rsync \
    i2c-tools \
    \
    r1701-apps \
"
