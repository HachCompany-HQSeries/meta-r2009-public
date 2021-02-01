# Add Kiosk mode enabled by default with special commands args.
PACKAGECONFIG += "kiosk-mode"
#TODO: create a user for running chromium and remove the --no-sandbox argument.
CHROMIUM_EXTRA_ARGS_append = " --in-process-gpu --incognito --no-first-run --no-sandbox --disable-web-security"

# There is a do_install error when the following flag is included in CHROMIUM_EXTRA_ARGS, so we'll add it at runtime
# --user-data-dir=/opt/hach/chromium "
