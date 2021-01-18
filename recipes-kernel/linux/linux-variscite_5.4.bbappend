# Provide our current default path where bitbake will serach for custom defconfig and patches.
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# HACH custom defconfig file to use.
SRC_URI += "file://defconfig"

# Point to our custon DEFCONFIG file after do_config is called by bitbake.
KERNEL_DEFCONFIG_imx8mm-var-dart = "${WORKDIR}/defconfig"

# Add your patches here and make sure that all patches are in files directory.
#SRC_URI += "file://my_device_tree_changes.patch"
