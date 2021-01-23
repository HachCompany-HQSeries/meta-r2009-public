DESCRIPTION = "SWU image for HACH hqseries-lab platform."
SECTION = ""

# Note: sw-description is mandatory
SRC_URI = " \
	file://sw-description \
	file://update.sh \
"

inherit swupdate
require recipes-core/images/hqseries-lab-image.bb

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

CORE_IMAGE_EXTRA_INSTALL += " \
	swupdate \
	swupdate-www \
	kernel-image \
	kernel-devicetree \
"

IMAGE_FSTYPES = "tar.gz"

# IMAGE_DEPENDS: list of Yocto images that contains a root filesystem
# it will be ensured they are built before creating swupdate image
IMAGE_DEPENDS = "hqseries-lab-image"

# SWUPDATE_IMAGES: list of images that will be part of the compound image
# the list can have any binaries - images must be in the DEPLOY directory
SWUPDATE_IMAGES = " \
	hqseries-lab-image \
"

# Images can have multiple formats - define which image must be
# taken to be put in the compound image
SWUPDATE_IMAGES_FSTYPES[hqseries-lab-image] = ".tar.gz"
