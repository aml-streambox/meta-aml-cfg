FILESEXTRAPATHS:prepend := "${THISDIR}/gstreamer1.0-plugins-bad:"

SRC_URI:append = " file://0001-rtmp2-support-enhanced-codec-signalling.patch"

PACKAGECONFIG:append = " webrtc srtp"
