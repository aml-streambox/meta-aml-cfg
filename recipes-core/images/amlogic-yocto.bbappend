# Set default root password for Streambox devices
inherit extrausers

# Password: Streamb0x (SHA-512 hashed)
EXTRA_USERS_PARAMS = "usermod -p '\$6\$ZjHJv2dDBSOECMla\$yggyCuOgLMn0CchupaKf5zzwvj7pcAa2Ka2Zl2NhBN7/A38dfBmYt2SQdbD8ZinKc0Z5gT/2mEl.9Xi.Qbzff.' root;"

# Cockpit web admin interface
IMAGE_INSTALL:append = " cockpit"
