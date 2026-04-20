SUMMARY = "Minimal Yocto SDK for SBS cross-compilation"
DESCRIPTION = "Installable SDK containing the SBS cross toolchain, target sysroot, and host build tools"
LICENSE = "MIT"

inherit populate_sdk

TOOLCHAIN_HOST_TASK = " \
    nativesdk-sdk-provides-dummy \
    packagegroup-cross-canadian-${MACHINE} \
    nativesdk-python3 \
    nativesdk-meson \
    nativesdk-ninja \
    nativesdk-pkgconfig \
    nativesdk-make \
    nativesdk-git \
    nativesdk-patch \
    nativesdk-glslang \
"

TOOLCHAIN_TARGET_TASK = " \
    packagegroup-core-standalone-sdk-target \
    target-sdk-provides-dummy \
    glib-2.0-dev \
    libion-dev \
    libge2d-dev \
    vulkan-loader-dev \
    gstreamer1.0-dev \
    gstreamer1.0-plugins-base-dev \
    gstreamer1.0-plugins-bad-dev \
    cjson-dev \
    srt-dev \
    libjpeg-dev \
    libpng-dev \
    alsa-lib-dev \
"

TOOLCHAIN_OUTPUTNAME = "sbs-sdk-${MACHINE}"

SDK_TITLE = "SBS SDK"
