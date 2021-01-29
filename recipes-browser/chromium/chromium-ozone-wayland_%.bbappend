#PACKAGECONFIG[kiosk-mode] = ""

# Add Kiosk mode enabled by default with special commands args.
#CHROMIUM_EXTRA_ARGS_append = " ${@bb.utils.contains('PACKAGECONFIG', 'kiosk-mode', '--kiosk --no-first-run --incognito --in-process-gpu --start-fullscreen', '', d)}"

#GN_ARGS += "\
#        use_kiosk-mode=true \
#"
