# Copyright (C) 2021 HACH Company.
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "HACH Hqseries Image."
LICENSE = "MIT"

require recipes-core/images/hqseries-lab-image.bb

CORE_IMAGE_EXTRA_INSTALL += " \
	swupdate \
	swupdate-www \
	kernel-image \
	kernel-devicetree \
"

IMAGE_FSTYPES = "tar.gz"
