# The SBS SDK workflow only needs a working cross toolchain and target sysroot.
# The default nativesdk libepoxy recipe enables EGL/X11 dispatch generation,
# which currently fails in this BSP because eglplatform.h is not staged into the
# nativesdk sysroot. Disable those optional features for the SDK variant so
# populate_sdk can complete without unrelated host-side GL dispatch support.

PACKAGECONFIG:class-nativesdk = ""
