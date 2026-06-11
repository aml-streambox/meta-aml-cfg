SUMMARY = "StreamBox-specific GStreamer plugins"
DESCRIPTION = "GStreamer plugin collection for StreamBox-specific FLV and RTMP behavior."
HOMEPAGE = "https://github.com/aml-streambox/gst-plugins-streambox"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=6c8f8366bf2bd49f4d32743a9897dbc7"

SRC_URI = "git://github.com/aml-streambox/gst-plugins-streambox.git;protocol=https;branch=main"
SRCREV = "d9cfe2f7aa939515bd88bb02170053df2d7a99ab"

PV = "0.1.0+git${SRCPV}"
S = "${WORKDIR}/git"

inherit meson pkgconfig

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base"

FILES:${PN} += "${libdir}/gstreamer-1.0/*.so"
