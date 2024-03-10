#!/bin/bash
gnome-terminal -- bash -c "python3 server.py; exec bash" --close-on-parent-exit
gnome-terminal -- bash -c "python3 client.py Arek; exec bash" --close-on-parent-exit
gnome-terminal -- bash -c "python3 client.py Marek; exec bash" --close-on-parent-exit
gnome-terminal -- bash -c "python3 client.py Jarek; exec bash" --close-on-parent-exit
# gnome-terminal -- bash -c "python3 client.py Darek; exec bash" --close-on-parent-exit
# gnome-terminal -- bash -c "python3 client.py Gwarek; exec bash" --close-on-parent-exit
