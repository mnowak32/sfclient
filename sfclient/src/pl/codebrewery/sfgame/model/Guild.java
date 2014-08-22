package pl.codebrewery.sfgame.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Guild {

	public boolean attacking, defending;
	public boolean attackJoined, defenseJoined;
	
	private final List<Member> members = new LinkedList<>();
	public int portalDay, portalLife, portalLifePercent, portalLevel;
	
	public class Member {
		String name;
		int id, level, online, lastPortalVisit;
		long goldSpent, shroomsSpent;
		int rank;
		boolean attackReady, defenseReady;
		
		@Override
		public String toString() {
			return String
				.format(
					"\nMember [name=%s, id=%s, level=%s, online=%s, lastPortalVisit=%s, goldSpent=%s, shroomsSpent=%s, rank=%s, attackReady=%s, defenseReady=%s]",
					name, id, level, online, lastPortalVisit, goldSpent, shroomsSpent, rank, attackReady, defenseReady);
		}
	}

	public void restoreFromSave(String save) {
		String[] data = save.split("/");
		//atak/obrona
		int attackedGuildId = Integer.parseInt(data[Const.GUILD_ATTACK_TARGET]);
		int defendingGuildId = Integer.parseInt(data[Const.GUILD_DEFENCE_TARGET]);
		
		attacking = attackedGuildId > 0;
		defending = defendingGuildId > 0;
		
		members.clear();
		//members
		for (int i = 0; i < 50; i++) { //max 50 ludzi
			Member m = new Member();
			m.id = Integer.parseInt(data[Const.GUILD_MEMBERID + i]);
			if (m.id == 0) { //brak człona. może break tu bęzdie słuszniejszy?
				continue;
			}
			m.level = Integer.parseInt(data[Const.GUILD_MEMBERLEVEL + i]);
			int attDefReady = m.level / 1000;
			m.level = m.level % 1000;
			m.attackReady = (attDefReady & 1) > 0;
			m.defenseReady = (attDefReady & 2) > 0;
			m.online = Integer.parseInt(data[Const.GUILD_MEMBERONLINE + i]);
			m.lastPortalVisit = Integer.parseInt(data[Const.GUILD_MEMBERHONOR + i]);
			m.goldSpent = Long.parseLong(data[Const.GUILD_MEMBERGOLDSPENT + i]);
			m.shroomsSpent = Long.parseLong(data[Const.GUILD_MEMBERMUSHSPENT + i]);
			m.rank = Integer.parseInt(data[Const.GUILD_MEMBERRANK + i]);
			
			members.add(m);
		}
		
		portalDay = (int)(Long.parseLong(data[2]) >> 16);
//        guildData[2] = (guildData[2] - (guildPortalDay * 65536));
        int guildPortalLifeLo = (int)(Long.parseLong(data[4]) >> 16);
//        guildData[4] = (guildData[4] - (guildPortalLifeLo * 65536));
        int guildPortalLifeHi = (int)(Long.parseLong(data[5]) >> 16);
//        guildData[5] = (guildData[5] - (guildPortalLifeHi * 65536));
        portalLife = ((guildPortalLifeHi * 65536) + guildPortalLifeLo);
        portalLifePercent = (int)(Long.parseLong(data[6]) >> 16);
//        guildData[6] = (guildData[6] - (this.guildPortalLifePercent * 65536));
        portalLevel = (int)(Long.parseLong(data[7]) >> 16);
//        guildData[7] = (guildData[7] - (this.guildPortalLevel * 65536));		
	}
	
	public void checkOwnReadiness(int ownId) {
		for (Member m : members) {
			if (m.id == ownId) {
				attackJoined = m.attackReady;
				defenseJoined = m.defenseReady;
				break;
			}
		}
	}

	public List<Member> getMembers() {
		return members;
	}

	@Override
	public String toString() {
		return String
			.format(
				"Guild [attacking=%s, defending=%s, attackJoined=%s, defenseJoined=%s, portalDay=%s, portalLife=%s, portalLifePercent=%s, portalLevel=%s]",
				attacking, defending, attackJoined, defenseJoined, portalDay, portalLife, portalLifePercent, portalLevel);
	}

	public boolean isPortalOpen(int myId) {
		return getMember(myId).map(m -> {
			return !Game.isToday(m.lastPortalVisit);
		}).orElse(false);
	}
	
	public Optional<Member> getMember(int id) {
		return members.stream().filter(m -> {
			return m.id == id;
		}).findFirst();
	}
}
