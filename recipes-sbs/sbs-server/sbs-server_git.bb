SUMMARY = "StreamBox Broadcast System server"
DESCRIPTION = "SBS native compositor, control API, worker, and encoder service for Amlogic StreamBox devices."
HOMEPAGE = "https://github.com/aml-streambox/sbs-server"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = " \
    git://git@github.com/aml-streambox/sbs-server.git;protocol=ssh;branch=v0.3_dev \
"
SRCREV = "afb29954286867bc5374a245667308db86ef9076"

PV = "0.3+git${SRCPV}"
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
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

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
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-base-alsa \
    gstreamer1.0-plugins-base-app \
    gstreamer1.0-plugins-base-audioconvert \
    gstreamer1.0-plugins-base-audiomixer \
    gstreamer1.0-plugins-base-audioresample \
    gstreamer1.0-plugins-base-opus \
    gstreamer1.0-plugins-base-playback \
    gstreamer1.0-plugins-base-typefindfunctions \
    gstreamer1.0-plugins-base-videoconvert \
    gstreamer1.0-plugins-base-videoscale \
    gstreamer1.0-plugins-base-videotestsrc \
    gstreamer1.0-plugins-base-volume \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-bad-dtls \
    gstreamer1.0-plugins-bad-mpegtsmux \
    gstreamer1.0-plugins-bad-rtmp \
    gstreamer1.0-plugins-bad-rtmp2 \
    gstreamer1.0-plugins-bad-srt \
    gstreamer1.0-plugins-bad-srtp \
    gstreamer1.0-plugins-bad-videoparsersbad \
    gstreamer1.0-plugins-bad-webrtc \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-good-audioparsers \
    gstreamer1.0-plugins-good-audiofx \
    gstreamer1.0-plugins-good-equalizer \
    gstreamer1.0-plugins-good-flv \
    gstreamer1.0-plugins-good-gdkpixbuf \
    gstreamer1.0-plugins-good-imagefreeze \
    gstreamer1.0-plugins-good-isomp4 \
    gstreamer1.0-plugins-good-jpeg \
    gstreamer1.0-plugins-good-level \
    gstreamer1.0-plugins-good-matroska \
    gstreamer1.0-plugins-good-png \
    gstreamer1.0-plugins-good-rtp \
    gstreamer1.0-plugins-good-rtpmanager \
    gstreamer1.0-plugins-good-soup \
    gstreamer1.0-plugins-good-udp \
    gstreamer1.0-plugins-good-video4linux2 \
    gstreamer1.0-plugins-streambox \
    gst-plugin-aml-v4l2dec \
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
