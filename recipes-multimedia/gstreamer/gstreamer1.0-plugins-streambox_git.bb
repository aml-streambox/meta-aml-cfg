SUMMARY = "StreamBox-specific GStreamer plugins"
DESCRIPTION = "GStreamer plugin collection for StreamBox-specific FLV and RTMP behavior."
HOMEPAGE = "https://github.com/aml-streambox/gst-plugins-streambox"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=6c8f8366bf2bd49f4d32743a9897dbc7"

SRC_URI = "git://github.com/aml-streambox/gst-plugins-streambox.git;protocol=https;branch=main"
SRCREV = "bb2621cfaa18581d2f7f5747bc2993113778aa3a"

PV = "0.1.0+git${SRCPV}"
S = "${WORKDIR}/git"

inherit meson pkgconfig

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base"

FILES:${PN} += "${libdir}/gstreamer-1.0/*.so"
