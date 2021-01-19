# The following recipe builds the r1701 applications and installs them into the target rootfs according to the CMake
# definitions within the firmware project.
#
# Yocto generated CMake toolchain file:
# /opt/hachdev/buildsystem/r1701/build/tmp/work/cortexa7hf-neon-dey-linux-gnueabi/r1701-applications/0.0-r0/toolchain.make

DESCRIPTION = "A recipe for building the Hach r1701 target applications."
LICENSE = "CLOSED"

# Build time dependencies with other recipes.
DEPENDS += "zeromq cppzmq boost sqlite3 protobuf protobuf-native qtbase qtbase-native gtest gtest-native cmake cmake-native gmock gmock-native"

# Use Yocto to perform CMake configuration.
inherit cmake pkgconfig

# Location of source code and artifacts used by this recipe.
SRC_URI = "gitsm://git@stash.waterqualitytools.com:7999/r1701/r1701_fw.git;branch=${FW_BRANCH};protocol=ssh \
"
SRCREV = "${FW_COMMIT}"

# Configure the SysV initialization service for the sys_mgr within the r1701_applications.
#inherit update-rc.d
#INITSCRIPT_NAME = "r1701-apps.sh"
#INITSCRIPT_PARAMS = "defaults 99"

# Source directory where the code will be placed.
S = "${WORKDIR}/git"

# Additional configuration of CMake.
# NOTE:: For Unit test build, we hve to make UNIT_TEST=ON and Turn off DEBUG mode other wise unit will not run.
EXTRA_OECMAKE += " -DUNIT_TEST=${@oe.utils.ifelse(d.getVar('FW_UNIT_TEST') == 'OFF', 'OFF', 'ON')} \
                   -DDEBUG=${@oe.utils.ifelse(d.getVar('FW_UNIT_TEST') == 'OFF', 'ON', 'OFF')} \
                   -DR1701_RELEASE=${@oe.utils.ifelse(d.getVar('FW_BRANCH') == 'develop', 'OFF', 'ON')} \
                   -DENABLE_CLANG_FORMATTER=OFF \
                   -DCMAKE_CXX_STANDARD=17 \
                   -DCMAKE_CXX_STANDARD_REQUIRED=ON \
                   -DCMAKE_CXX_EXTENSIONS=OFF \
                   -DCMAKE_VERBOSE_MAKEFILE=ON \
                   -DOE_QMAKE_PATH_EXTERNAL_HOST_BINS=${STAGING_BINDIR_NATIVE}\
                   -DCMAKE_INSTALL_PREFIX=${base_prefix}/opt/hach \
                   -DCMAKE_INSTALL_SYSCONFDIR=${base_prefix}/opt/hach/ \
                   -DCMAKE_INSTALL_BINDIR=bin \
"

# Special compiler flags this appliction uses.
CFLAGS_R1701 = "-Wno-poison-system-directories -Wall -Wpointer-arith -Wno-psabi \
                -ffunction-sections -fdata-sections -feliminate-unused-debug-types"

# These definitions are required for easylogging so that when it is turned on it will include all of the needed features.
# These are added explicitly to allow the addition of the TIMED_ functions which make timing the performance of
# functions and scopes very easy.
TARGET_CFLAGS += "${CFLAGS_R1701} -DELPP_DISABLE_PERFORMANCE_TRACKING -DELPP_THREAD_SAFE -DINI_PATH=/opt/hach/configs/sys_r1701.cfg"
TARGET_CXXFLAGS += "${CFLAGS_R1701} -DELPP_DISABLE_PERFORMANCE_TRACKING -DELPP_THREAD_SAFE -DINI_PATH=/opt/hach/configs/sys_r1701.cfg"

# Create /opt directory for r1701 apps installation.
FILES_${PN} += " \
    ${base_prefix}/opt \
    ${base_prefix}/opt/hach/bin/mca_cc6ul.bin \
"