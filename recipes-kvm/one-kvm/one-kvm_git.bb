SUMMARY = "One-KVM StreamBox - open IP-KVM solution for Amlogic A311D2"
DESCRIPTION = "One-KVM is an open and lightweight IP-KVM solution written in Rust. \
This StreamBox variant integrates with Amlogic vfmcap + libmultienc for hardware-accelerated \
4K60 HDMI capture and H264/H265 encoding via the Wave521 VPU."
HOMEPAGE = "https://github.com/mofeng-git/One-KVM"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=016b6c2875cfaf9d87362055aae3f974"

# Use the local git repository
SRC_URI = " \
    git:///home/anshi/yocto/one-kvm-streambox/One-KVM-StreamBox;protocol=file;branch=v0.6.2_dev \
    file://one-kvm.service \
    file://one-kvm-vendor.tar.gz \
"

CARGO_NETWORK_OFFLINE = "1"

# Use explicit SRCREV to avoid AUTOREV network fetch
SRCREV = "e06502158ad679dc02a11c16389cdb9f4d1363ed"

S = "${WORKDIR}/git"
CARGO_SRC_DIR = ""

inherit cargo systemd

# Enable the aml feature for Amlogic hardware support
CARGO_BUILD_FLAGS:append = " --features aml"

# Native build dependencies
DEPENDS = " \
    cargo-native \
    rust-native \
    rust-llvm-native \
    pkgconfig-native \
    protobuf-native \
"

# Target build dependencies (libraries linked at runtime)
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
    libusb1 \
    kernel-module-libcomposite \
    kernel-module-usb-f-hid \
    kernel-module-usb-f-mass-storage \
"

# systemd service
SYSTEMD_SERVICE:${PN} = "one-kvm.service"
SYSTEMD_AUTO_ENABLE = "enable"

# The binary name produced by cargo
CARGO_BIN_NAME = "one-kvm"

do_compile:prepend() {
    export LIBCLANG_PATH="${STAGING_LIBDIR_NATIVE}/llvm-rust/lib"
    export BINDGEN_EXTRA_CLANG_ARGS="--sysroot=${STAGING_DIR_TARGET} -I${STAGING_LIBDIR_NATIVE}/llvm-rust/lib/clang/17/include"

    # Overlay vendor directory from tarball onto source tree
    if [ -d "${WORKDIR}/vendor" ]; then
        cp -rn "${WORKDIR}/vendor" "${S}/"
    fi

    # Create .cargo/config.toml to point to vendored sources
    mkdir -p "${S}/.cargo"
    cat > "${S}/.cargo/config.toml" << EOF
[source.crates-io]
replace-with = "vendored-sources"

[source.vendored-sources]
directory = "${S}/vendor"
EOF

    # Overwrite the CARGO_HOME config to use our vendor instead of bitbake
    cat > "${CARGO_HOME}/config" << EOF
# EXTRA_OECARGO_PATHS
paths = []

[source.vendored-sources]
directory = "${S}/vendor"

[source.crates-io]
replace-with = "vendored-sources"
local-registry = "/nonexistent"

[http]
multiplexing = false
cainfo = "${RECIPE_SYSROOT_NATIVE}/etc/ssl/certs/ca-certificates.crt"

# HOST_SYS
[target.aarch64-poky-linux-gnu]
linker = "${WORKDIR}/wrapper/target-rust-ccld"

# BUILD_SYS
[target.x86_64-unknown-linux-gnu]
linker = "${WORKDIR}/wrapper/build-rust-ccld"

[build]
target-dir = "${WORKDIR}/build/target"

[term]
progress.when = 'always'
progress.width = 80
EOF
}

do_install:append() {
    # Install the binary
    install -d ${D}${bindir}
    install -m 0755 ${B}/target/${CARGO_TARGET_SUBDIR}/one-kvm ${D}${bindir}/

    # Create working directories
    install -d ${D}${localstatedir}/lib/one-kvm
    install -d ${D}${sysconfdir}/one-kvm

    # Install default config if present
    if [ -f ${S}/config.toml ]; then
        install -m 0644 ${S}/config.toml ${D}${sysconfdir}/one-kvm/
    fi

    # Install systemd service
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/one-kvm.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} = " \
    ${bindir}/one-kvm \
    ${localstatedir}/lib/one-kvm \
    ${sysconfdir}/one-kvm \
    ${systemd_system_unitdir}/one-kvm.service \
"

CONFFILES:${PN} = "${sysconfdir}/one-kvm/config.toml"

# Skip QA checks that don't apply to Rust binaries
INSANE_SKIP:${PN} += "already-stripped"

# Ensure the aml feature is only built for Amlogic machines
COMPATIBLE_MACHINE = "^(mesont7|mesont7c|mesong12b)"

# Allow cargo.bbclass to use vendored crates from the tarball
CARGO_DISABLE_BITBAKE_VENDORING = "0"
