SUMMARY = "StreamBox Broadcast System standalone WebUI"
DESCRIPTION = "Static browser client for controlling sbs-server over the public WebSocket API."
HOMEPAGE = "https://github.com/aml-streambox/sbs-client-webui"
LICENSE = "CLOSED"

SRC_URI = " \
    git://git@github.com/aml-streambox/sbs-client-webui.git;protocol=ssh;branch=v0.3_dev \
    file://sbs-webui-dist.tar.gz \
    file://sbs-webui.service \
    file://sbs-webui-http.py \
"
SRCREV = "fbdb9efc7b8511d2dc5ab91069b404b3963b0549"

PV = "0.3+git${SRCPV}"
PR = "r2"
S = "${WORKDIR}/git"

inherit systemd

# The WebUI is shipped as a prebuilt static bundle to keep image builds
# offline/reproducible instead of resolving npm packages during BitBake tasks.
do_configure[noexec] = "1"
do_compile[noexec] = "1"

SYSTEMD_SERVICE:${PN} = "sbs-webui.service"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

python do_unpack:prepend() {
    import shutil
    shutil.rmtree(d.expand("${WORKDIR}/dist"), ignore_errors=True)
}

do_install() {
    rm -rf ${D}${localstatedir}/www/sbs-webui
    install -d ${D}${localstatedir}/www/sbs-webui
    if [ -d ${WORKDIR}/dist ]; then
        cp -r ${WORKDIR}/dist/. ${D}${localstatedir}/www/sbs-webui/
    elif [ -f ${WORKDIR}/index.html ]; then
        install -m 0644 ${WORKDIR}/index.html ${D}${localstatedir}/www/sbs-webui/
        if [ -d ${WORKDIR}/assets ]; then
            cp -r ${WORKDIR}/assets ${D}${localstatedir}/www/sbs-webui/
        fi
    else
        bbfatal "sbs-webui dist bundle not found in ${WORKDIR}"
    fi

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
