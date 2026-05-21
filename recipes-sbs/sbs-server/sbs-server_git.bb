SUMMARY = "StreamBox Broadcast System server"
DESCRIPTION = "SBS native compositor, control API, worker, and encoder service for Amlogic StreamBox devices."
HOMEPAGE = "https://github.com/aml-streambox/sbs-server"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = " \
    git://git@github.com/aml-streambox/sbs-server.git;protocol=ssh;branch=v0.2_dev \
"
SRCREV = "ee241eaca2c745f88eefb1627ef3bd4410e75e59"

PV = "0.2+git${SRCPV}"
S = "${WORKDIR}/git"

inherit meson pkgconfig systemd

DEPENDS = " \
    alsa-lib \
    cjson \
    fontconfig \
    glib-2.0 \
    glslang-native \
    gstreamer1.0 \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-base \
    libdrm \
    libge2d \
    libion \
    libjpeg-turbo \
    libpng \
    libvfmcap \
    libwebsockets \
    pango \
    srt \
    systemd \
    vulkan-loader \
"

EXTRA_OEMESON = " \
    -Dplatform=amlogic \
    -Dshader_compile=true \
    -Dtests=false \
"

SYSTEMD_SERVICE:${PN} = "sbs-server.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/data/sbs-server.service ${D}${systemd_system_unitdir}/

    install -d ${D}${sysconfdir}/tmpfiles.d
    install -m 0644 ${S}/data/sbs-tmpfiles.conf ${D}${sysconfdir}/tmpfiles.d/sbs.conf
}

FILES:${PN} += " \
    ${bindir}/sbs-server \
    ${bindir}/sbs-worker \
    ${bindir}/sbs-cli \
    ${datadir}/sbs \
    ${systemd_system_unitdir}/sbs-server.service \
    ${sysconfdir}/tmpfiles.d/sbs.conf \
"

RDEPENDS:${PN} += " \
    bash \
    gstreamer1.0 \
    gstreamer1.0-libav \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-good \
    gst-plugin-vfmcap \
    libge2d \
    libion \
    libmultienc \
    libvfmcap \
    srt \
    vfm-cap \
    vulkan-loader \
"

COMPATIBLE_MACHINE = "^(mesont7|mesont7c|mesong12b)"
