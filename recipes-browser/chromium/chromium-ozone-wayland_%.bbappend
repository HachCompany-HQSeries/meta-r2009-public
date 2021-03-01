# Add Kiosk mode enabled by default with special commands args.
PACKAGECONFIG += "kiosk-mode"
#TODO: create a user for running chromium and remove the --no-sandbox argument.
CHROMIUM_EXTRA_ARGS_append = " --in-process-gpu --incognito --no-first-run --no-sandbox"
