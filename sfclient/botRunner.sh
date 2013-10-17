#!/bin/bash

BOTS=("jerzynathor=nitro0" "jerzynka=nitro0" "kolczasta=nitro0" "hexabit=xaga1973" "klinsmann=klop0000" "Berbelucha=masakracja" "tacix=Audrey111!" "snoopdog=snoopdog" "gigakiler=gigakiler" "mechagodzilla=mechagodzilla" "Tacixa=Audrey111!")

while [ true ]; do
for BOT in ${BOTS[*]}; do
	namePass=(${BOT//=/ })
	echo "Running bot ${namePass[0]}"
	./bot ${namePass[0]} ${namePass[1]}
done
echo "Sleeping for 6 hours"
sleep 21600
done
