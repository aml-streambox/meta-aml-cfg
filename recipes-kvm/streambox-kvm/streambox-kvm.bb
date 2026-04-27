SUMMARY = "StreamBox KVM - IP-KVM solution for Amlogic A311D2"
DESCRIPTION = "StreamBox-native IP-KVM application with WebRTC streaming, \
USB HID, virtual media, and web UI. Uses libvfmcap + libmultienc for \
hardware-accelerated HDMI capture and H265 encoding."
HOMEPAGE = "https://github.com/streambox/streambox-kvm"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=016b6c2875cfaf9d87362055aae3f974"

SRC_URI = " \
    file://streambox-kvm.service \
    file://streambox-kvm-vendor.tar.gz \
"

# Use external source directory (no git fetch needed)
inherit externalsrc cargo systemd
EXTERNALSRC = "${TOPDIR}/../apps/streambox-kvm"
EXTERNALSRC_BUILD = "${B}"

# No network access during build
CARGO_NETWORK_OFFLINE = "1"

# Native build dependencies
DEPENDS = " \
    cargo-native \
    rust-native \
    rust-llvm-native \
    pkgconfig-native \
"

# Target build dependencies
DEPENDS:append = " \
    libmultienc \
    libvfmcap \
    ffmpeg \
    vulkan-loader \
    libjpeg-turbo \
    alsa-lib \
    libopus \
"

# Runtime dependencies
RDEPENDS:${PN} = " \
    libmultienc \
    libvfmcap \
    ffmpeg \
    vulkan-loader \
    libjpeg-turbo \
    alsa-lib \
    libopus \
    systemd \
    udev \
    kernel-module-libcomposite \
    kernel-module-usb-f-hid \
    kernel-module-usb-f-mass-storage \
"

# systemd service
SYSTEMD_SERVICE:${PN} = "streambox-kvm.service"
SYSTEMD_AUTO_ENABLE = "enable"

# The binary name produced by cargo
CARGO_BIN_NAME = "streambox-kvm"

do_compile:prepend() {
    export LIBCLANG_PATH="${STAGING_LIBDIR_NATIVE}/llvm-rust/lib"
    export BINDGEN_EXTRA_CLANG_ARGS="--sysroot=${STAGING_DIR_TARGET} -I${STAGING_LIBDIR_NATIVE}/llvm-rust/lib/clang/17/include"
    export RUSTFLAGS="${RUSTFLAGS} -L ${STAGING_LIBDIR} -L ${STAGING_DIR_TARGET}/usr/lib"

    # Extract vendor tarball into workdir (not source tree)
    if [ -f "${WORKDIR}/streambox-kvm-vendor.tar.gz" ]; then
        tar xzf "${WORKDIR}/streambox-kvm-vendor.tar.gz" -C "${WORKDIR}/"
    fi

    # Overwrite cargo_home config to use vendored sources instead of bitbake
    # cargo.bbclass ignores .cargo/config.toml, so we must patch CARGO_HOME/config
    cat > "${CARGO_HOME}/config" << EOF
paths = []

[source.bitbake]
directory = "${CARGO_HOME}/bitbake"

[source.crates-io]
replace-with = "vendored-sources"
local-registry = "/nonexistent"

[http]
multiplexing = false
cainfo = "${RECIPE_SYSROOT_NATIVE}/etc/ssl/certs/ca-certificates.crt"

[target.aarch64-poky-linux-gnu]
linker = "${WORKDIR}/wrapper/target-rust-ccld"

[target.x86_64-unknown-linux-gnu]
linker = "${WORKDIR}/wrapper/build-rust-ccld"

[build]
target-dir = "${WORKDIR}/build/target"

[term]
progress.when = 'always'
progress.width = 80

[source.vendored-sources]
directory = "${WORKDIR}/vendor"
EOF
}

do_install:append() {
    # Install the binary
    install -d ${D}${bindir}
    install -m 0755 ${B}/target/${CARGO_TARGET_SUBDIR}/streambox-kvm ${D}${bindir}/

    # Create working directories
    install -d ${D}${localstatedir}/lib/streambox-kvm
    install -d ${D}${sysconfdir}/streambox-kvm

    # Install default config if present
    if [ -f ${EXTERNALSRC}/config.toml ]; then
        install -m 0644 ${EXTERNALSRC}/config.toml ${D}${sysconfdir}/streambox-kvm/
    fi

    # Install systemd service
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/streambox-kvm.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} = " \
    ${bindir}/streambox-kvm \
    ${localstatedir}/lib/streambox-kvm \
    ${sysconfdir}/streambox-kvm \
    ${systemd_system_unitdir}/streambox-kvm.service \
"

CONFFILES:${PN} = "${sysconfdir}/streambox-kvm/config.toml"

# Skip QA checks that don't apply to Rust binaries
INSANE_SKIP:${PN} += "already-stripped"

# Ensure only built for Amlogic machines
COMPATIBLE_MACHINE = "^(mesont7|mesont7c|mesong12b)"
