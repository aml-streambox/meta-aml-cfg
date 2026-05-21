SUMMARY = "StreamBox Broadcast System standalone WebUI"
DESCRIPTION = "Static browser client for controlling sbs-server over the public WebSocket API."
HOMEPAGE = "https://github.com/aml-streambox/sbs-client-webui"
LICENSE = "CLOSED"

SRC_URI = " \
    git://git@github.com/aml-streambox/sbs-client-webui.git;protocol=ssh;branch=v0.2_dev \
    file://sbs-webui-dist.tar.gz \
    file://sbs-webui.service \
    file://sbs-webui-http.py \
"
SRCREV = "a63f26a33553bfaf5144c274d91728655c1aa13c"

PV = "0.2+git${SRCPV}"
S = "${WORKDIR}/git"

inherit systemd

# The WebUI is shipped as a prebuilt static bundle to keep image builds
# offline/reproducible instead of resolving npm packages during BitBake tasks.
do_configure[noexec] = "1"
do_compile[noexec] = "1"

SYSTEMD_SERVICE:${PN} = "sbs-webui.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

python do_unpack:prepend() {
    import shutil
    shutil.rmtree(d.expand("${WORKDIR}/dist"), ignore_errors=True)
}

do_install() {
    rm -rf ${D}${localstatedir}/www/sbs-webui
    install -d ${D}${localstatedir}/www/sbs-webui
    cp -r ${WORKDIR}/dist/. ${D}${localstatedir}/www/sbs-webui/

    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/sbs-webui-http.py ${D}${bindir}/sbs-webui-http

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/sbs-webui.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} = " \
    ${bindir}/sbs-webui-http \
    ${localstatedir}/www/sbs-webui \
    ${systemd_system_unitdir}/sbs-webui.service \
"

RDEPENDS:${PN} = " \
    python3-core \
    python3-netserver \
"
