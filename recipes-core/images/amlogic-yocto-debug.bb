require amlogic-yocto.bb

IMAGE_FEATURES += "package-management"

IMAGE_INSTALL += "\
    iozone3 \
    fio \
    stressapptest \
    coreutils \
    cpuburn \
    opkg \
    opkg-arch-config \
    sbs-server \
    sbs-webui \
    "
