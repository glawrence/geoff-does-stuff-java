# Scripts
This directory contains useful scripts for executing the utilities. 

## Wake on LAN
These scripts are designed to have the configuration near the top, which are IP_ADDRESS, MAC_ADDRESS and REPO_BASE. The first two are fairly self-explanatory, and the last one should point to the directory root of the repository, in other words the directory that contains the LICENSE file.

It is recommended that copies of these scripts are taken, and the copies modified.

The scripts take a simple `u` or `d` command line argument for "up" or "down", currently there is no option to set the ping count. The scripts check for the required jar files and if one is missing a Gradle build is performed.

### Windows
The script, `wol.cmd` or rather copy there of, can be added to the path, and run from anywhere.

### Linux/macOS
There is a Bash script called `wol.sh`, however my preference is to copy the function in the file to my shell's profile, that way it is always available and does not need to be on my path.
