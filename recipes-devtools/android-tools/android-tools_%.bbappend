# Keep android-tools available, but do not auto-start Amlogic's stock
# ADB/RNDIS configfs gadget. One-KVM owns the UDC for HID/MSD gadget mode.
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

pkg_postinst:${PN}:append() {
	if [ -n "$D" ]; then
		rm -f "$D${sysconfdir}/systemd/system/multi-user.target.wants/adbd.service"
		rm -f "$D${sysconfdir}"/rc*.d/S*adbd
	fi
}
