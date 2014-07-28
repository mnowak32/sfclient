#!/bin/bash

declare -A SERVERS
SERVERS=([s1]="s1.sfgame.pl" [s24]="s24.sfgame.pl")

BOTS=("s1=jerzynathor=nitro0" "s1=jerzynka=nitro0" "s1=kolczasta=nitro0" "s1=hexabit=xaga1973" "s1=klinsmann=klop0000" "s1=Berbelucha=masakracja" "s1=tacix=Audrey111!" "s1=snoopdog=snoopdog" "s1=gigakiler=gigakiler" "s1=mechagodzilla=mechagodzilla" "s1=Tacixa=Audrey111!" "s1=Wargrim=dupablada" "s1=Filemon=S&F_Filemon" "s1=1996MANIEK1996=S&F_1996MANIEK1996" "s24=jerzynathor=nitro0" "s1=FabianC=1qazxsw2" "s1=platfoos=maslotrzaslo" "s1=msisdn=478b2095ebc661d6e15620b5f3d5e75e" "s1=usim=408d63c8f41f06b885f8a4c8040ddf58" "s1=imsi=5918e19c5652d5605e187368106b0ec0")

while [ true ]; do
date
for BOT in ${BOTS[*]}; do
	namePass=(${BOT//=/ })
	srv=${SERVERS[${namePass[0]}]}
	echo "Running bot ${namePass[1]}@$srv"
	java -cp bin:lib/commons-lang3-3.1.jar:lib/jline-1.0.jar pl.codebrewery.sfgame.interfaces.WarBot $srv ${namePass[1]} ${namePass[2]}
done
echo -e "\nSleeping for 2 hours\n"
sleep 7200
done
