package pl.codebrewery.sfgame.model;

import java.util.LinkedList;
import java.util.List;

public class Guild {

	private boolean attacking, defending;
	private boolean attackJoined, defenseJoined;
	
	private List<Member> members = new LinkedList<>();
	
	public class Member {
		String name;
		int id, level, online, honor;
		long goldSpent, shroomsSpent;
		int rank;
		boolean attackReady, defenseReady;
		
		@Override
		public String toString() {
			return String
				.format(
					"\nMember [name=%s, id=%s, level=%s, online=%s, honor=%s, goldSpent=%s, shroomsSpent=%s, rank=%s, attackReady=%s, defenseReady=%s]",
					name, id, level, online, honor, goldSpent, shroomsSpent, rank, attackReady, defenseReady);
		}
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isDefending() {
		return defending;
	}

	public void setDefending(boolean defending) {
		this.defending = defending;
	}

	public boolean isDefenseJoined() {
		return defenseJoined;
	}

	public void setDefenseJoined(boolean defenseJoined) {
		this.defenseJoined = defenseJoined;
	}

	public boolean isAttackJoined() {
		return attackJoined;
	}

	public void setAttackJoined(boolean attackJoined) {
		this.attackJoined = attackJoined;
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
			m.honor = Integer.parseInt(data[Const.GUILD_MEMBERHONOR + i]);
			m.goldSpent = Long.parseLong(data[Const.GUILD_MEMBERGOLDSPENT + i]);
			m.shroomsSpent = Long.parseLong(data[Const.GUILD_MEMBERMUSHSPENT + i]);
			m.rank = Integer.parseInt(data[Const.GUILD_MEMBERRANK + i]);
			
			members.add(m);
		}
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
		return String.format("Guild [attacking=%s, defending=%s, attackJoined=%s, defenseJoined=%s, members=%s]", attacking,
			defending, attackJoined, defenseJoined, members);
	}
}
