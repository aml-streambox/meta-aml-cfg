SUMMARY = "A Featureful File Browser for Cockpit"
DESCRIPTION = "Cockpit Navigator is a file management plugin for Cockpit, providing a modern web-based file browser interface."
HOMEPAGE = "https://github.com/45Drives/cockpit-navigator"

LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = "https://github.com/45Drives/cockpit-navigator/archive/refs/tags/v${PV}.tar.gz"
SRC_URI[sha256sum] = "29d26e00a10a127b689ef148770a3c1684ce7427f863e970935231e5d35a11bd"

S = "${WORKDIR}/cockpit-navigator-${PV}"

RDEPENDS:${PN} = " \
    cockpit \
    cockpit-bridge \
    python3 \
    rsync \
    zip \
    file \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/cockpit/navigator
    cp -r ${S}/navigator/* ${D}${datadir}/cockpit/navigator/
}

FILES:${PN} = "${datadir}/cockpit/navigator"
