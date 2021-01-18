# HACH specific stuff.
# Provide our current default path where bitbake will serach for custom patches.
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# HACH custom DIFF / PATCH files.
#SRC_URI += "file://my_imx8mm_var_dart_changes.patch"
#SRC_URI += "file://my_imx8mm_var_dart_changes.diff"
