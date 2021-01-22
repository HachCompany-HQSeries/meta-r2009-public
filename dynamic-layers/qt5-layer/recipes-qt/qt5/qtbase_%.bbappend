# build linuxfb backend if required
PACKAGECONFIG_append = " fontconfig tslib ${@bb.utils.filter('BACKEND', 'linuxfb', d)}"

# Remove examples.
PACKAGECONFIG[examples] = ""
