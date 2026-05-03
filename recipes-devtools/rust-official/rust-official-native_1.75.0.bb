SUMMARY = "Official Rust/Cargo binary toolchain for native build tasks"
DESCRIPTION = "Installs a pinned upstream Rust toolchain into the native sysroot for recipes that need a newer Cargo than the Yocto distro toolchain provides."
LICENSE = "CLOSED"

RUST_OFFICIAL_VERSION = "${PV}"
RUST_OFFICIAL_HOST = "x86_64-unknown-linux-gnu"
RUST_OFFICIAL_TARGET = "aarch64-unknown-linux-gnu"
RUST_OFFICIAL_PREFIX = "${prefix}/lib/rust-official"

SRC_URI = " \
    https://static.rust-lang.org/dist/rustc-${RUST_OFFICIAL_VERSION}-${RUST_OFFICIAL_HOST}.tar.xz;name=rustc;subdir=rust-official-components \
    https://static.rust-lang.org/dist/cargo-${RUST_OFFICIAL_VERSION}-${RUST_OFFICIAL_HOST}.tar.xz;name=cargo;subdir=rust-official-components \
    https://static.rust-lang.org/dist/rust-std-${RUST_OFFICIAL_VERSION}-${RUST_OFFICIAL_HOST}.tar.xz;name=rust-std-host;subdir=rust-official-components \
    https://static.rust-lang.org/dist/rust-std-${RUST_OFFICIAL_VERSION}-${RUST_OFFICIAL_TARGET}.tar.xz;name=rust-std-target;subdir=rust-official-components \
"

SRC_URI[rustc.sha256sum] = "2824ba4045acdddfa436da4f0bb72807b64a089aa2e7c9a66ca1a3a571114ce7"
SRC_URI[cargo.sha256sum] = "6ac164e7da969a1d524f747f22792e9aa08bc7446f058314445a4f3c1d31a6bd"
SRC_URI[rust-std-host.sha256sum] = "136b132199f7bbda2aa0bbff6d1e6ae7d5fca2994a2f2a432a5e99de224b6314"
SRC_URI[rust-std-target.sha256sum] = "2ea0dc380ac1fced245bafadafd0da50167a4a416b6011e3d73ba3e657a71d15"

S = "${WORKDIR}/rust-official-components"

inherit native

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d "${D}${RUST_OFFICIAL_PREFIX}"

    for installer in "${S}"/*/install.sh; do
        "${installer}" --prefix="${D}${RUST_OFFICIAL_PREFIX}" --disable-ldconfig
    done
}

SYSROOT_DIRS_NATIVE += "${RUST_OFFICIAL_PREFIX}"

# The native sysroot contains both host tools and target Rust std libraries.
# Host strip cannot process the target AArch64 shared objects.
INHIBIT_SYSROOT_STRIP = "1"

FILES:${PN} += "${RUST_OFFICIAL_PREFIX}"

INSANE_SKIP:${PN} += "already-stripped libdir staticdev"
