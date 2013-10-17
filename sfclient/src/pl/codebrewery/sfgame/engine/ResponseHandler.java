package pl.codebrewery.sfgame.engine;

import java.util.Arrays;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;

import pl.codebrewery.sfgame.interfaces.Commander;
import pl.codebrewery.sfgame.model.ChatLine;
import pl.codebrewery.sfgame.model.Const;
import pl.codebrewery.sfgame.model.Game;

public class ResponseHandler {

	public boolean handleResponse(final Response r, final Commander com) {
		if (r == null) {
			com.nullResponse();
//			com.print("Null response encountered!\n");
			return true;
		}
		
		final int act = r.getCode();
		final String[] par = r.getParts();
		final Game gd = Game.I;

		switch (act) {
//        case Const.ERR_TOWER_CLOSED:
//            break;
//        case Const.RESP_TOWER_SAVE:
//            parseSavegame(par[0]);
//            this.ShowTowerScreen(par);
//            break;
//        case Const.RESP_TOILET_LOCKED:
//            this.Remove(this.IMG_TAVERNE_BARKEEPER_HINT);
//            this.Remove(this.BNC_TAVERNE_CAS);
//            this.Add(this.BNC_BEEROFFER);
//            this.EnablePopup(this.CNT_QO_REWARDGOLD);
//            this.EnablePopup(this.CNT_QO_REWARDSILVER);
//            this.EnablePopup(this.LBL_QO_REWARDGOLD);
//            this.EnablePopup(this.LBL_QO_REWARDSILVER);
//            this.EnablePopup(this.LBL_QO_REWARDEXP);
//            var _local3 = this.actor[this.LBL_QO_QUESTNAME];
//            with (_local3) {
//                text = txt[(TXT_TOILET_HINT + 5)];
//                x = ((POS_QO_BLACK_SQUARE_X + REL_QO_QUESTNAME_X) - int((textWidth / 2)));
//            };
//            _local3 = this.actor[this.LBL_QO_QUESTTEXT];
//            with (_local3) {
//                text = txt[(TXT_TOILET_HINT + 6)].split("#").join(String.fromCharCode(13));
//            };
//            this.Arabize(this.LBL_QO_QUESTTEXT);
//            this.actor[this.LBL_QO_TIME].text = "";
//            this.actor[this.LBL_QO_REWARDEXP].text = "";
//            this.Remove(this.BTN_BO_BUY);
//            this.Add(this.IMG_BO_PORTRAIT_TH);
//            break;
//        case Const.RESP_TOILET_UNLOCKED:
//            this.Play(this.SND_MAINQUESTS_UNLOCK);
//        case Const.RESP_TOILET_DROPPED:
//        case Const.RESP_TOILET_FULL:
//        case Const.RESP_TOILET_FLUSHED:
//        case Const.ACT_SCREEN_TOILET:
//        case Const.RESP_TOILET_TANKFULL:
//        case Const.REPS_TOILET_DROPTWICE:
//            if (act == this.RESP_TOILET_DROPPED){
//                this.Play(this.SND_TOILET_DROP);
//            };
//            ParseSavegame(par[0]);
//            if (par.length > 1){
//                if (act == this.RESP_TOILET_FLUSHED){
//                    this.ShowToilet(par[1], par[2], par[3], par[4], par[5]);
//                } else {
//                    this.ShowToilet(par[1], par[2], par[3], par[4]);
//                };
//            };
//            if (act == this.RESP_TOILET_FULL){
//                this.ErrorMessage(this.txt[this.TXT_TOILET_FULL]);
//            } else {
//                if (act == this.RESP_TOILET_TANKFULL){
//                    this.ErrorMessage(this.txt[this.TXT_TOILET_TANKFULL]);
//                } else {
//                    if (act == this.REPS_TOILET_DROPTWICE){
//                        this.ErrorMessage(this.txt[this.TXT_TOILET_DROPTWICE]);
//                    } else {
//                        this.ErrorMessage("");
//                    };
//                };
//            };
//            break;
//        case Const.ERR_NO_SLOT_FOR_FLUSHING:
//            this.ErrorMessage(this.txt[this.TXT_ERR_NO_SLOT_FOR_FLUSHING]);
//            break;
//        case Const.ERR_TOILET_EMPTY:
//            this.ErrorMessage(this.txt[this.TXT_ERR_TOILET_EMPTY]);
//            break;
//        case Const.ERR_GUILD_DESCR_TOO_LONG:
//            this.ErrorMessage(this.txt[this.TXT_ERR_GUILD_DESCR_TOO_LONG]);
//            break;
//        case Const.ERR_NO_CHAT_OVERFLOW:
//            break;
//        case Const.ERR_GUILD_RANK_WRONG:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_GUILD_RANK_WRONG]);
//            break;
        case Const.RESP_ALBUM:
        	byte[] bytes = DatatypeConverter.parseBase64Binary(StringUtils.join(par, '/'));
        	boolean[] album = new boolean[8 * bytes.length];
        	for (int i = 0, p = 0; i < bytes.length; i++) {
        		byte b = bytes[i];
        		album[p++] = ((b & 0x80) > 0);
        		album[p++] = ((b & 0x40) > 0);
        		album[p++] = ((b & 0x20) > 0);
        		album[p++] = ((b & 0x10) > 0);
        		album[p++] = ((b & 0x08) > 0);
        		album[p++] = ((b & 0x04) > 0);
        		album[p++] = ((b & 0x02) > 0);
        		album[p++] = ((b & 0x01) > 0);
        	}
        	gd.setAlbum(album);
        	int albumCounter = 0;
        	for (boolean a : album) {
        		if (a) albumCounter++;
        	}
        	com.print(String.format("Album completion: #_R%d/%d#Z (#Y%.2f%%%%#Z).\n", albumCounter, Game.CONTENT_MAX, (100.0 * albumCounter / Game.CONTENT_MAX)));
        	break;
//            tmpByteArray = Base64.decodeToByteArray(par.join("/"));
//            bitArray = new Array();
//            i = 0;
//            while (i < tmpByteArray.length) {
//                bitArray.push(((tmpByteArray[i] & 128) / 128));
//                bitArray.push(((tmpByteArray[i] & 64) / 64));
//                bitArray.push(((tmpByteArray[i] & 32) / 32));
//                bitArray.push(((tmpByteArray[i] & 16) / 16));
//                bitArray.push(((tmpByteArray[i] & 8) / 8));
//                bitArray.push(((tmpByteArray[i] & 4) / 4));
//                bitArray.push(((tmpByteArray[i] & 2) / 2));
//                bitArray.push((tmpByteArray[i] & 1));
//                i = (i + 1);
//            };
//            this.AlbumContent = bitArray;
//            this.ShowScreenAlbum();
//            break;
//        case Const.RESP_INVITE_SUCCESS:
//            this.Show(this.BNC_INVITE_SUCCESS);
//            this.Hide(this.BNC_INVITE_INPUTDIALOGUE);
//            break;
//        case Const.ERR_INVITE_NOT_VALIDATED:
//        case Const.ERR_INVITE_TOO_MANY:
//        case Const.ERR_INVITE_EMAIL_REJECTED:
//            this.ErrorMessage(this.txt[((this.TXT_ERROR_INVITE_NOT_VALIDATED - act) + this.ERR_INVITE_NOT_VALIDATED)]);
//            break;
//        case Const.RESP_LOGOUT_SUCCESS:
//            break;
//        case Const.ERR_SERVER_DOWN:
//            this.ShowDisconnectScreen();
//            if ((this.param_reconnect * this.intervalMultiplierReconnect) < ((1000 * 60) * 2)){
//                this.intervalMultiplierReconnect = (this.intervalMultiplierReconnect + 0.1);
//            };
//            break;
//        case Const.ERR_JOINED_TOO_RECENTLY:
//            ParseSavegame(par[0]);
//            this.ErrorMessage(this.txt[this.TXT_GUILD_JOINED_TOO_RECENTLY].split("%1").join(this.TimeStr((Number(this.Savegame[this.SG_GUILD_JOIN_DATE]) + ((60 * 60) * 24)), true)));
//            break;
//        case Const.RESP_ATTACK_NOT_EXIST:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_PLAYER_NOT_FOUND]);
//            break;
//        case Const.ERR_GUILD_FIGHT_TOO_EXPENSIVE:
//        case Const.ERR_GUILD_ALREADY_UNDER_ATTACK:
//        case Const.ERR_GUILD_ATTACK_DELAY:
//        case Const.ERR_GUILD_ALREADY_ATTACKING:
//        case Const.ERR_GUILD_ATTACK_STATUS:
//            this.ErrorMessage(this.txt[((this.TXT_ERROR_GUILD_FIGHT_TOO_EXPENSIVE - act) + this.ERR_GUILD_FIGHT_TOO_EXPENSIVE)]);
//            break;
//        case Const.RESP_GUILD_NAMES:
//            if (par[0] == ""){
//                if (this.lastGuildData[this.GUILD_IS_RAID] != 0){
//                    if (this.txt[this.TXT_RAID_TEXT]){
//                        this.actor[this.LBL_GILDE_ATTACK].text = this.txt[(this.TXT_RAID_TEXT + ((this.IsToday(this.lastGuildData[this.GUILD_ATTACK_TIME])) ? 13 : 12))].split("%1").join(this.txt[(this.TXT_DUNGEON_NAMES + int(this.lastGuildData[this.GUILD_RAID_LEVEL]))]).split("%2").join(this.TimeStr(this.lastGuildData[this.GUILD_ATTACK_TIME], true));
//                    } else {
//                        this.actor[this.LBL_GILDE_ATTACK].text = "";
//                    };
//                } else {
//                    this.actor[this.LBL_GILDE_ATTACK].text = "";
//                };
//            } else {
//                this.actor[this.LBL_GILDE_ATTACK].text = this.txt[(this.TXT_GUILD_BATTLE_MSG + ((this.IsToday(this.lastGuildData[this.GUILD_ATTACK_TIME])) ? 2 : 0))].split("%1").join(par[0]).split("%2").join(this.TimeStr(this.lastGuildData[this.GUILD_ATTACK_TIME], true));
//            };
//            this.actor[this.LBL_GILDE_DEFENCE].text = ((par[1])=="") ? "" : this.txt[((this.TXT_GUILD_BATTLE_MSG + 1) + ((this.IsToday(this.lastGuildData[this.GUILD_DEFENCE_TIME])) ? 2 : 0))].split("%1").join(par[1]).split("%2").join(this.TimeStr(this.lastGuildData[this.GUILD_DEFENCE_TIME], true));
//            if (par[2]){
//                if (this.lastGuildData[this.GUILD_IS_RAID] != 0){
//                    if (this.txt[this.TXT_RAID_TEXT]){
//                        this.EnablePopup(this.CNT_GILDE_ATTACK, this.txt[(this.TXT_RAID_TEXT + 14)].split("%1").join(par[2]));
//                    };
//                } else {
//                    if ((((par[0] == "")) || ((par[2] == "")))){
//                        this.EnablePopup(this.CNT_GILDE_ATTACK);
//                    } else {
//                        if (this.txt[this.TXT_GUILD_ATTACK_PLAYER]){
//                            this.EnablePopup(this.CNT_GILDE_ATTACK, this.txt[this.TXT_GUILD_ATTACK_PLAYER].split("%1").join(par[2]));
//                        } else {
//                            this.EnablePopup(this.CNT_GILDE_ATTACK, par[2]);
//                        };
//                    };
//                };
//            } else {
//                this.EnablePopup(this.CNT_GILDE_ATTACK);
//            };
//            this.guildAttackTime = this.lastGuildData[this.GUILD_ATTACK_TIME];
//            this.guildDefenceTime = this.lastGuildData[this.GUILD_DEFENCE_TIME];
//            this.guildAttacked = par[0];
//            this.guildAttacking = par[1];
//            break;
        case Const.ERR_SESSION_ID_EXPIRED:
        	gd.setSessionId(null);
        	com.print("#RSession expired, re-logging...\n");
        	com.relogin();
        	break;
//            this.trc("Achtung, sessionID ist abgelaufen.");
//            this.sessionID = "";
//            this.fightFlushMode = false;
//            this.ShowLoginScreen();
//            break;
//        case Const.ERR_MSG_LEVEL_TOO_LOW:
//            if (this.txt[this.TXT_ERROR_MSG_LEVEL_TOO_LOW]){
//                this.ErrorMessage(this.txt[this.TXT_ERROR_MSG_LEVEL_TOO_LOW]);
//            } else {
//                this.ErrorMessage("Error: You need to reach at least level 10 to send messages.");
//            };
//            break;
//        case Const.ERR_MSG_NOT_VALIDATED:
//            if (this.txt[this.TXT_ERROR_MSG_NOT_VALIDATED]){
//                this.ErrorMessage(this.txt[this.TXT_ERROR_MSG_NOT_VALIDATED]);
//            } else {
//                this.ErrorMessage("Error: Your email address has to be validated in order to send messages.");
//            };
//            break;
//        case Const.ERR_INVENTORY_FULL:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_INVENTORY_FULL]);
//            break;
//        case Const.ERR_INVENTORY_FULL_ADV:
//            if (this.txt[this.TXT_ERROR_INVENTORY_FULL_ADV]){
//                this.ErrorMessage(this.txt[this.TXT_ERROR_INVENTORY_FULL_ADV]);
//            } else {
//                this.ErrorMessage(this.txt[this.TXT_ERROR_INVENTORY_FULL]);
//            };
//            this.FadeOut(this.CNT_QUEST_SLOT, 20, 0.04, 0.3);
//            this.forceAdventure = true;
//            break;
//        case Const.ERR_PLACE_BET:
//            break;
//        case Const.RESP_BET_WON:
//            ParseSavegame(par[0]);
//            this.ShowBetResult(true);
//            break;
//        case Const.RESP_BET_LOST:
//            ParseSavegame(par[0]);
//            this.ShowBetResult(false);
//            break;
//        case Const.ERR_ACCOUNTS_PER_IP:
//            this.ErrorMessage(this.txt[this.TXT_ERRROR_ACCOUNTS_PER_IP]);
//            break;
//        case Const.ERR_TOO_SOON:
//            break;
//        case Const.ERR_LOCKED_PAYMENT:
//            this.ErrorMessage(this.txt[this.TXT_ERRROR_LOCKED_PAYMENT].split("%supportemail%").join(this.param_support_email).split("%gamestaffemail%").join(this.param_gamestaff_email));
//            break;
//        case Const.ERR_LOCKED_ADMIN:
//            if (par[2]){
//                if (this.txt[((this.TXT_LOCK_REASON + int(par[1])) - 1)]){
//                    this.ErrorMessage(this.txt[((this.TXT_LOCK_REASON + int(par[1])) - 1)].split("%1").join(String((1 + int((par[2] / ((60 * 60) * 24)))))));
//                } else {
//                    this.ErrorMessage(this.txt[this.TXT_ERRROR_LOCKED_ADMIN]);
//                };
//            } else {
//                this.ErrorMessage(this.txt[this.TXT_ERRROR_LOCKED_ADMIN]);
//            };
//            break;
//        case Const.RESP_REQUEST_GUILD_QUIET:
//            this.DestroyGuildBtnTimer = true;
//            if (this.OnStage(this.LBL_GILDE_CHAT_CAPTION)){
//                this.SendAction(this.ACT_SCREEN_GILDEN);
//            };
//            break;
//        case Const.RESP_REQUEST_GUILD:
//            this.DestroyGuildBtnTimer = true;
//            if (this.OnStage(this.LBL_GILDE_CHAT_CAPTION)){
//                this.SendAction(this.ACT_SCREEN_GILDEN);
//            } else {
//                this.PulseGilde = true;
//            };
//            break;
//        case Const.ERR_GUILD_DONATE_FRA:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_GUILD_DONATE_FRA]);
//            break;
//        case Const.ERR_GUILD_DONATE_NEG:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_GUILD_DONATE_NEG]);
//            break;
//        case Const.ERR_MAIL_EXISTS:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_MAIL_EXISTS]);
//            break;
//        case Const.RESP_VALIDATE_OK:
//            if (par[0]){
//                this.param_cid = par[0];
//                this.trc("cid set by server:", this.param_cid);
//            };
//            this.ShowEmailNagScreen(1);
//            break;
//        case Const.ERR_VALIDATE:
//            this.ShowEmailNagScreen(2);
//            break;
//        case Const.RESP_PASSWORD_SENT:
//            this.ShowLoginScreen();
//            break;
//        case Const.ERR_REQUEST_PW:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_REQUEST_PW]);
//            break;
//        case Const.RESP_TRANS_COUNT:
//            PaymentLink = ((((((("http://www.payment.playa-games.com/legal/wiretransfer_" + this.lang_code) + ".php?amount=") + String(int((this.tmpAmount / 100)))) + (((this.lang_code == "de")) ? "," : ".")) + String((this.tmpAmount % 100))) + "&use=") + par[0]);
//            navigateToURL(new URLRequest(PaymentLink), "_blank");
//            break;
//        case Const.RESP_DEALER_AKTION:
//            NetbankLink = ((((((("http://www.playa-games.com/legal/netbank/index.php?email=" + par[0]) + "&playerid=") + this.Savegame[this.SG_PLAYER_ID]) + "a") + this.Savegame[this.SG_PAYMENT_ID]) + "a") + String(this.ServerID));
//            navigateToURL(new URLRequest(NetbankLink), "_blank");
//            break;
//        case Const.RESP_DEALER_SPONSOR:
//            SponsorLink = ((((((("http://www.payment.playa-games.com/sponsorpay.php?" + ((par.length)>0) ? (("email=" + par[0]) + "&") : "") + "playerid=") + this.Savegame[this.SG_PLAYER_ID]) + "a") + this.Savegame[this.SG_PAYMENT_ID]) + "a") + String(this.ServerID));
//            navigateToURL(new URLRequest(SponsorLink), "_blank");
//            break;
//        case Const.RESP_EMAIL_RESENT:
//            this.actor[this.LBL_EMAIL_RESEND].htmlText = this.txt[this.TXT_EMAIL_RESENT];
//            this.Arabize(this.LBL_EMAIL_RESEND);
//            this.actor[this.LBL_OPTION_FIELD1].htmlText = this.txt[this.TXT_EMAIL_RESENT];
//            this.Arabize(this.LBL_OPTION_FIELD1);
//            this.Remove(this.BTN_OPTION_DOCHANGE);
//            break;
        case Const.RESP_CHAT_HISTORY:
    			gd.setNewChat(false);
    			String[] lines = par[0].split("/");
    			String[] ids = par[3].split("/");
    			long lastId = Long.parseLong(par[1]);
    			
    			if (lastId != gd.getChat().getLastIndex()) {
    				Map<Long, ChatLine> map = gd.getChat().getLines();
    				for (int i = 0; i < lines.length; i++) {
    					long id = Long.parseLong(ids[i]);
    					ChatLine cl = new ChatLine(lines[i]);
    					if (!map.containsKey(id)) {
    						map.put(id,  cl);
    					}
    				}
    			}
    			
    			//fall throgh!
    			
        case Const.ERR_NO_CHAT_INFO:
    			for (ChatLine cl : gd.getChat().getLines().values()) {
    				com.print(cl.getLine() + "\n");
    			}
    			break;
        	
//            if (par[0] != this.lastChatHist){
//                this.lastChatHist = par[0];
//                this.intervalMultiplierChat = 1;
//            } else {
//                if (this.intervalMultiplierChat < 5){
//                    this.intervalMultiplierChat = (this.intervalMultiplierChat + 0.1);
//                } else {
//                    if (this.intervalMultiplierChat < 30){
//                        this.intervalMultiplierChat = (this.intervalMultiplierChat + 1);
//                    };
//                };
//            };
//            if (par[0] == ""){
//                tmpArray = [];
//            } else {
//                tmpArray = par[0].split("/");
//            };
//            this.firstChatFill = false;
//            if (this.lastChatIndex == 0){
//                this.firstChatFill = true;
//                i = 0;
//                while (i < 40) {
//                    _local3 = this.actor[(this.LBL_GILDE_CHAT + i)];
//                    with (_local3) {
//                        defaultTextFormat = FontFormat_Chat;
//                        text = "";
//                    };
//                    i = (i + 1);
//                };
//            };
//            if (tmpArray.length > 0){
//                i = (tmpArray.length - 1);
//                while (i >= 0) {
//                    if (!par[3]){
//                        if (((!((this.lastChatIndex == 0))) && ((this.DecodeChat(tmpArray[i], false, true) == "1")))){
//                            this.Remove(this.BNC_GILDE_CHAT);
//                        };
//                        this.ChatLine(this.DecodeChat(tmpArray[i]), false, this.getHlIndex(tmpArray[i]));
//                        if (((((!((tmpArray[i].indexOf("§") == -1))) || (!(this.so.data.noPulseOnSysMsg)))) && (this.PulseGildeOnHistory))){
//                            this.PulseGilde = true;
//                        };
//                        if (this.lastChatIndex != 0){
//                            ii = 0;
//                            while (ii < this.offlineGuildMembers.length) {
//                                if (tmpArray[i].toLowerCase().indexOf((this.offlineGuildMembers[ii].toLowerCase() + ":§")) != -1){
//                                    if (this.OnStage(this.INP_GILDE_CHAT)){
//                                        this.SendAction(this.ACT_SCREEN_GILDEN);
//                                    };
//                                    break;
//                                };
//                                ii = (ii + 1);
//                            };
//                        };
//                    } else {
//                        if (par[3].split("/")[i] > this.lastChatIndex){
//                            if (((!(this.firstChatFill)) && (this.chatSound))){
//                                this.Play(this.SND_ERROR);
//                            };
//                            this.lastChatIndex = par[3].split("/")[i];
//                            if (((!((this.lastChatIndex == 0))) && ((this.DecodeChat(tmpArray[i], false, true) == "1")))){
//                                this.Remove(this.BNC_GILDE_CHAT);
//                            };
//                            this.ChatLine(this.DecodeChat(tmpArray[i]), false, this.getHlIndex(tmpArray[i]));
//                            if (((((!((tmpArray[i].indexOf("§") == -1))) || (!(this.so.data.noPulseOnSysMsg)))) && (this.PulseGildeOnHistory))){
//                                this.PulseGilde = true;
//                            };
//                            ii = 0;
//                            while (ii < this.offlineGuildMembers.length) {
//                                if (tmpArray[i].toLowerCase().indexOf((this.offlineGuildMembers[ii].toLowerCase() + ":§")) != -1){
//                                    if (this.OnStage(this.INP_GILDE_CHAT)){
//                                        this.SendAction(this.ACT_SCREEN_GILDEN);
//                                    };
//                                    break;
//                                };
//                                ii = (ii + 1);
//                            };
//                        };
//                    };
//                    i = (i - 1);
//                };
//                this.PulseGildeOnHistory = false;
//            };
//            if (((par[1]) && (!(par[3])))){
//                this.lastChatIndex = Number(par[1]);
//            };
//            if (par[2]){
//                tmpArray = par[2].split("/");
//            } else {
//                tmpArray = [];
//            };
//            if (tmpArray.length > 0){
//                if (this.chatSound){
//                    this.Play(this.SND_ERROR);
//                };
//                i = (tmpArray.length - 1);
//                while (i >= 0) {
//                    this.PulseGilde = true;
//                    externalWhisperer = tmpArray[i].substr(6);
//                    externalWhisperer = externalWhisperer.substr(0, externalWhisperer.indexOf(":§"));
//                    this.ChatLine(this.DecodeChat(tmpArray[i]), false, this.getHlIndex(tmpArray[i]), true);
//                    this.addSuggestNames(externalWhisperer);
//                    if (this.lastChatIndex != 0){
//                        ii = 0;
//                        while (ii < this.offlineGuildMembers.length) {
//                            if (tmpArray[i].toLowerCase().indexOf((this.offlineGuildMembers[ii].toLowerCase() + ":§")) != -1){
//                                if (this.OnStage(this.INP_GILDE_CHAT)){
//                                    this.SendAction(this.ACT_SCREEN_GILDEN);
//                                };
//                                break;
//                            };
//                            ii = (ii + 1);
//                        };
//                    };
//                    i = (i - 1);
//                };
//            };
//            this.guildBlinkReady = true;
//            if (((((!((this.newCrestSuggested == ""))) && (!(this.firstChatFill)))) && (this.OnStage(this.INP_GILDE_CHAT)))){
//                this.clickChatLine(this.newCrestSuggested);
//            };
//            this.newCrestSuggested = "";
//            break;
//        case Const.RESP_WHISPER_SUCCESS:
//            this.addSuggestNames(this.lastWhisperTarget);
//            this.ChatLine(this.DecodeChat(par[0]), false, this.getHlIndex(par[0]), true);
//            break;
//        case Const.ERR_GUILD_NAME_REJECTED:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_GUILD_NAME_REJECTED]);
//            break;
//        case Const.ERR_GUILD_NAME_LENGTH:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_GUILD_NAME_LENGTH]);
//            break;
//        case Const.ERR_GUILD_NAME_CHARACTERS:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_GUILD_NAME_CHARACTERS]);
//            break;
//        case Const.ERR_GUILD_EMAIL_VALIDATE:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_GUILD_EMAIL_VALIDATE]);
//            break;
//        case Const.ERR_GUILD_MUSH_FREE:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_GUILD_MUSH_FREE]);
//            break;
//        case Const.RESP_CHAT_LINE:
//            if (this.DecodeChat(par[0], false, true) == "1"){
//                this.Remove(this.BNC_GILDE_CHAT);
//            };
//            this.ChatLine(this.DecodeChat(par[0]), false, this.getHlIndex(par[0]));
//            if (!this.OnStage(this.INP_GILDE_CHAT)){
//                this.PulseGilde = true;
//            };
//            break;
//        case Const.RESP_GUILD_DONATE_SUCCESS:
//            ParseSavegame(par[0]);
//            this.SendAction(this.ACT_SCREEN_GILDEN);
//            break;
//        case Const.RESP_NO_LOGIN:
//            this.RequestLogout(undefined, true);
//            this.ShowLoginScreen();
//            break;
//        case Const.RESP_DELETE_ACCOUNT_OK:
//            this.RequestLogout();
//            break;
//        case Const.RESP_CHANGE_PASS_OK:
//            this.so.data.password = this.optionNewData;
//            this.so.flush();
//            this.actor[this.INP_LOGIN_PASSWORD].getChildAt(1).text = this.optionNewData;
//            this.ShowOptionScreen();
//            this.ErrorMessage(this.txt[this.TXT_PASSWORD_CHANGED]);
//            break;
//        case Const.RESP_CHANGE_NAME_OK:
//            this.so.data.userName = this.optionNewData;
//            this.so.flush();
//            this.actor[this.INP_NAME].getChildAt(1).text = this.optionNewData;
//            ParseSavegame(par[0]);
//            this.ShowOptionScreen();
//            this.ErrorMessage(this.txt[this.TXT_NAME_CHANGED]);
//            break;
//        case Const.RESP_CHANGE_MAIL_OK:
//            this.ShowOptionScreen();
//            this.ErrorMessage(this.txt[this.TXT_EMAIL_CHANGED]);
//            break;
//        case Const.RESP_CHANGE_FACE_OK:
//            ParseSavegame(par[0]);
//        case Const.ACT_SCREEN_OPTIONEN:
//            this.ShowOptionScreen();
//            break;
//        case Const.RESP_DEMO_SCREEN:
//            this.ShowDemoScreen();
//            break;
//        case Const.RESP_PLAYER_SCREEN:
//            this.ShowPlayerScreen(("0/" + par[0]).split("/"), this.selName, par[2], this.resolveBreaks(par[1]));
//            break;
//        case Const.RESP_PLAYER_DESC_SUCCESS:
//            this.PlayerDesc = this.actor[this.INP_CHARDESC].getChildAt(0).text;
//            break;
//        case Const.RESP_GUILD_CHANGE_DESC_SUCCESS:
//            break;
        case Const.RESP_GUILD_DATA:
            if (parseInt(par[1]) == gd.getGuildId()) {
                gd.setGuildName(par[0]);
            };
            break;
//        case Const.RESP_MAINQUEST:
//            this.Hide(this.BNC_IF_STATS);
//            ParseSavegame(par[10]);
//            this.PulseChar = false;
//        case Const.RESP_QUEST_DONE:
//        case Const.RESP_QUEST_DONE_PIXEL:
//            this.fightLock = true;
//            this.PostFightMode = false;
//            this.ShowFightScreen(par[0].split("/"), par[1].split("/"), (par[6] == "1"), par[2].split("/"), (par[5] == "2"), ((par[3] + "/") + par[4]).split("/"), int(par[7]), int(par[8]), (par[5] == "3"), false, int(par[9]));
//            break;
//        case Const.RESP_GUILD_FIGHT:
//            this.towerFightMode = false;
//            this.alternateCharOppImg = true;
//            this.fightLock = true;
//            this.winners = new Array();
//            this.lastRoundFighterName = "";
//            this.fights = parStr.split("§");
//            this.guildFightCount = int(((this.fights.length - 1) / 2));
//            this.skipGuildFights = 0;
//            this.nextFightTimer.start();
//            break;
//        case Const.RESP_TOWER_FIGHT:
//            this.towerFightMode = true;
//            this.alternateCharOppImg = true;
//            this.fightLock = true;
//            this.winners = new Array();
//            this.lastRoundFighterName = "";
//            this.fights = parStr.split("§");
//            ParseSavegame(this.fights.pop(), true, true);
//            this.guildFightCount = int(((this.fights.length - 1) / 2));
//            this.skipGuildFights = 0;
//            this.nextFightTimer.start();
//            break;
//        case Const.RESP_QUEST_SKIP_ALLOWED_START:
//            this.skipAllowed = true;
//        case Const.RESP_QUEST_START:
//            ParseSavegame(par[0]);
//            this.ShowQuestScreen();
//            break;
//        case Const.RESP_QUEST_SKIP_ALLOWED:
//            this.skipAllowed = true;
        case Const.ACT_SCREEN_TAVERNE:
        case Const.RESP_QUEST_STOP:
			gd.restoreFromSave(par[0]);
			com.print(Arrays.asList(gd.getQuests()).toString());
			break;
//            ParseSavegame(par[0]);
//            if (par[1]){
//                this.specialAction = par[1];
//            } else {
//                this.specialAction = 0;
//            };
//            this.ShowTaverneScreen();
//            break;
//        case Const.ACT_SCREEN_GILDE_GRUENDEN:
//            this.ShowScreenGildeGruenden();
//            break;
//        case Const.RESP_GUILD_FOUND_SUCCESS:
//            _local3 = this.actor[this.LBL_IF_GOLD];
//            with (_local3) {
//                text = String((int(text) - 10));
//                x = ((actor[IMG_IF_GOLD].x - textWidth) - 10);
//            };
//        case Const.RESP_GUILD_RENAME_SUCCESS:
//        case Const.RESP_GUILD_IMPROVE_SUCCESS:
//        case Const.RESP_GUILD_OFFICER_SUCCESS:
//        case Const.RESP_GUILD_EXPEL_SUCCESS:
//        case Const.RESP_GUILD_INVITE_SUCCESS:
//        case Const.RESP_GUILD_MASTER_SUCCESS:
//        case Const.RESP_GUILD_JOIN_SUCCESS:
//            this.SendAction(this.ACT_SCREEN_GILDEN);
//            break;
//        case Const.RESP_GUILD_DELETE_SUCCESS:
//            this.Gilde = "";
//            this.myOwnRank = -1;
//            this.myOwnAttackTarget = -1;
//            this.myOwnGuildMoney = -1;
//            this.ShowCityScreen();
//            break;
//        case Const.RESP_GUILD_COMMENCE_ATTACK_OK:
//        case Const.RESP_GUILD_JOIN_ATTACK_OK:
//        case Const.RESP_GUILD_JOIN_DEFENSE_OK:
//            ParseSavegame(par[0]);
//            this.SendAction(this.ACT_SCREEN_GILDEN);
//            break;
        case Const.ACT_SCREEN_GILDEN:
//        	System.out.println(par[0]);
        	gd.getGuild().restoreFromSave(par[0]);
        	gd.getGuild().checkOwnReadiness(gd.getPlayerId());
//        	System.out.println(gd.getGuild());
        	break;
//            this.Savegame[this.SG_GUILD_INDEX] = par[0].split("/")[0];
//            this.Gilde = par[3];
//            isMine = true;
//            this.intervalMultiplierChat = 1;
//        case Const.RESP_OTHER_GUILD:
//            this.DestroyGuildBtnTimer = true;
//            if (((((((!(this.OnStage(this.CNT_GILDE_CREST))) || ((act == this.RESP_OTHER_GUILD)))) || (!((this.lastGuildCrestId == par[0].split("/")[0]))))) || (((isMine) && ((this.oldCrestStr == this.getCrestStr())))))){
//                if (par[1].indexOf("§") != -1){
//                    this.setCrestStr(par[1].split("§")[0]);
//                    par[1] = par[1].substr((par[1].indexOf("§") + 1));
//                } else {
//                    this.lastGuildData = par[0].split("/");
//                    this.setDefaultCrest();
//                };
//                this.oldCrestStr = this.getCrestStr();
//            } else {
//                if (par[1].indexOf("§") != -1){
//                    par[1] = par[1].substr((par[1].indexOf("§") + 1));
//                };
//            };
//            if (this.lastGuildCrestId != par[0].split("/")[0]){
//                this.oldCrestStr = this.getCrestStr();
//            };
//            this.ShowScreenGilden(par[0].split("/"), ((isMine) ? par[1] : (((par[1].indexOf("///") > -1)) ? par[1].split("///")[1] : par[1])), par[2].split("/"), par[3], isMine, int(par[5]), int(par[4]), ((par[6]) ? par[6] : 0));
//            break;
//        case Const.ACT_SCREEN_STALL:
//            this.Stundenlohn = Number(par[0]);
//            this.ShowStallScreen();
//            break;
//        case Const.ERR_ATTACK_AGAIN:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_ATTACK_AGAIN]);
//            break;
//        case Const.ACT_SCREEN_ARENA:
//            this.ShowArenaScreen(par[0], par[2], par[1]);
//            break;
//        case Const.ERR_INBOX_FULL:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_INBOX_FULL]);
//            break;
//        case Const.ERR_RECIPIENT_NOT_FOUND:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_RECIPIENT_NOT_FOUND]);
//            break;
//        case Const.ERR_RECIPIENT_SELF:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_RECIPIENT_SELF]);
//            break;
//        case Const.RESP_MESSAGE_SENT:
//            this.addSuggestNames(this.lastMessageTarget);
//            this.Remove(this.BNC_POST_WRITE);
//            this.Remove(this.BNC_POST_READ);
//            this.Add(this.BNC_POST_LIST);
//            break;
//        case Const.RESP_READ_MESSAGE:
//            this.RemoveAll();
//            this.Add(this.BNC_SCREEN_POST);
//            if (this.Tageszeit() != 0){
//                this.Remove(this.BNC_POST_NIGHT);
//            };
//            if (this.Tageszeit() != 1){
//                this.Remove(this.BNC_POST_DAWN);
//            };
//            this.Remove(this.BNC_POST_LIST);
//            this.Add(this.BNC_POST_READ);
//            if (((this.PostSel + this.PostScroll) - 1) == 1){
//                this.Remove(this.BTN_POST_READ_PREV);
//            };
//            if (((this.PostSel + this.PostScroll) - 1) == this.Savegame[this.SG_MSG_COUNT]){
//                this.Remove(this.BTN_POST_READ_NEXT);
//            };
//            if (int(par[4]) > 0){
//                this.InviteGildenID = int(par[4]);
//                this.Add(this.BTN_POST_ACCEPT);
//            };
//            _local3 = this.actor[this.INP_POST_ADDRESS].getChildAt(1);
//            with (_local3) {
//                type = TextFieldType.DYNAMIC;
//                text = ((((((txt[TXT_POST_FROM] + " ") + par[0]) + " ") + txt[TXT_POST_TIME]) + " ") + TimeStr(par[2]));
//            };
//            _local3 = this.actor[this.INP_POST_SUBJECT].getChildAt(1);
//            with (_local3) {
//                type = TextFieldType.DYNAMIC;
//                ReplyAddress = par[0];
//                switch (par[1]){
//                    case "1  ":
//                    case "2  ":
//                    case "3  ":
//                    case "4  ":
//                    case "5  ":
//                    case "6  ":
//                    case "7  ":
//                    case "8  ":
//                    case "9  ":
//                        par[1] = "Moo!";
//                        par[3] = "Holy Cow!";
//                        break;
//                    case "1":
//                        par[1] = txt[TXT_SUBJECT_GUILD_DELETED];
//                        par[3] = txt[TXT_BODY_GUILD_DELETED].replace("%1", par[0]).replace("%2", par[3]);
//                        break;
//                    case "2":
//                        par[1] = txt[TXT_SUBJECT_GUILD_DELETED_BY_ADMIN];
//                        par[3] = txt[TXT_BODY_GUILD_DELETED_BY_ADMIN].replace("%1", par[0]).replace("%2", par[3]);
//                        break;
//                    case "3":
//                        par[1] = txt[TXT_SUBJECT_GUILD_EXPELLED];
//                        par[3] = txt[TXT_BODY_GUILD_EXPELLED].replace("%1", par[0]).replace("%2", par[3]);
//                        break;
//                    case "4":
//                        par[1] = txt[TXT_SUBJECT_GUILD_EXPELLED_BY_ADMIN];
//                        par[3] = txt[TXT_BODY_GUILD_EXPELLED_BY_ADMIN].replace("%1", par[0]).replace("%2", par[3]);
//                        break;
//                    case "5":
//                        par[1] = txt[TXT_SUBJECT_GUILD_INVITE];
//                        par[3] = txt[TXT_BODY_GUILD_INVITE].replace("%1", par[0]).replace("%2", par[3]);
//                        break;
//                    case "6":
//                    case "7":
//                        par[1] = txt[TXT_SUBJECT_PVP].replace("%1", par[0]);
//                        tmpBattleInfo = par[3];
//                        tmpFighterArray = tmpBattleInfo.split("#")[0].split("/");
//                        ichAnfg = tmpFighterArray[0];
//                        erAnfg = tmpFighterArray[6];
//                        tmpFightArray = tmpBattleInfo.split("#")[1].split("/");
//                        ichEnde = tmpFightArray[(tmpFightArray.length - 7)];
//                        erEnde = tmpFightArray[(tmpFightArray.length - 4)];
//                        rundenZahl = int((tmpFightArray.length / 6));
//                        tmpHonor = Math.abs(tmpBattleInfo.split("#")[7]);
//                        tmpGold = Math.abs(int((tmpBattleInfo.split("#")[8] / 100)));
//                        tmpSilver = Math.abs(int((tmpBattleInfo.split("#")[8] % 100)));
//                        par[3] = txt[TXT_BODY_PVP].replace("%1", par[0]).replace("%2", par[3]).replace("%3", String(ichAnfg)).replace("%4", String(erAnfg)).replace("%5", String(ichEnde)).replace("%6", String(erEnde)).replace("%7", String(rundenZahl)).replace("%8", txt[(((ichEnde > erEnde)) ? TXT_DU_GEWONNEN : TXT_DU_VERLOREN)]).replace("%9", (((rundenZahl == 1)) ? "" : txt[TXT_ROUNDS_PLURAL])).split("%10").join(String(tmpHonor)).split("%11").join(String(tmpGold)).split("%12").join(String(tmpSilver)).split("%13").join(txt[(((ichEnde > erEnde)) ? TXT_DU_WAS_GEWONNEN : TXT_DU_WAS_VERLOREN)]).split("#").join(String.fromCharCode(13));
//                        Add(BTN_POST_VIEWFIGHT);
//                        break;
//                    case "8":
//                        if (txt[TXT_INV_ACC_TITLE] != ""){
//                            par[1] = txt[TXT_INV_ACC_TITLE];
//                            par[3] = txt[TXT_INV_ACC_TEXT].split("%1").join(par[0]);
//                        } else {
//                            par[1] = "FRIEND_LINK_ACCEPTED";
//                            par[3] = (((("You are seeing this message in english because it has not been translated for your location yet. " + par[0]) + " has accepted your invitation to the game. Please wait for ") + par[0]) + " to verify email address in order to get your bonus.");
//                        };
//                        break;
//                    case "9":
//                        if (txt[TXT_INV_VAL_TITLE] != ""){
//                            par[1] = txt[TXT_INV_VAL_TITLE];
//                            par[3] = txt[TXT_INV_VAL_TEXT].split("%1").join(par[0]);
//                        } else {
//                            par[1] = "FRIEND_EMAIL_VERIFIED";
//                            par[3] = (par[0] + " has verified his/her email address.");
//                        };
//                        break;
//                    default:
//                        Add(BTN_POST_REPLY);
//                };
//                ReplySubject = par[1];
//                text = par[1].split("%u20AC").join("€");
//            };
//            postReadText = par[3];
//            if (this.txt[this.TXT_ALERT_WORDS]){
//                alertWords = this.txt[this.TXT_ALERT_WORDS].split(" ");
//                i = 0;
//                while (i < alertWords.length) {
//                    if (postReadText.toLowerCase().indexOf(alertWords[i].toLowerCase()) != -1){
//                        postReadText = this.txt[this.TXT_ALERT_TEXT].split("%1").join(postReadText);
//                        break;
//                    };
//                    i = (i + 1);
//                };
//            };
//            _local3 = this.actor[this.INP_POST_TEXT].getChildAt(1);
//            with (_local3) {
//                type = TextFieldType.DYNAMIC;
//                text = SwapWords(postReadText).split("#").join(String.fromCharCode(13)).split("%u20AC").join("€");
//            };
//            this.forwardText = postReadText;
//            break;
//        case Const.ACT_SCREEN_POST:
//            this.ShowPostScreen(par);
//            break;
//        case Const.ACT_SCREEN_PILZDEALER:
//            if (par[0]){
//                this.DealerAktion = int(par[0]);
//            } else {
//                this.DealerAktion = 0;
//            };
//            this.PulseDealer = false;
//            this.ShowDealerScreen();
//            break;
//        case Const.ACT_SCREEN_WELTKARTE:
//            ParseSavegame(par[0]);
//            this.ShowMainQuestsScreen(par[1].split("/"));
//            break;
//        case Const.ACT_SCREEN_EHRENHALLE:
//            this.lastGuildShown = "";
//        case Const.RESP_SCREEN_GILDENHALLE:
//            this.GuildHallMode = (act == this.RESP_SCREEN_GILDENHALLE);
//            if (this.GuildHallMode){
//                this.Hide(this.LBL_HALL_GOTO_SPIELER_HL, this.LBL_HALL_GOTO_GILDEN);
//                this.Show(this.LBL_HALL_GOTO_SPIELER, this.LBL_HALL_GOTO_GILDEN_HL);
//            } else {
//                this.lastHallMembers = new Array();
//                this.lastHallMembers.push("");
//                this.Show(this.LBL_HALL_GOTO_SPIELER_HL, this.LBL_HALL_GOTO_GILDEN);
//                this.Hide(this.LBL_HALL_GOTO_SPIELER, this.LBL_HALL_GOTO_GILDEN_HL);
//            };
//            if (par[1]){
//                this.ruhmesHalleSuchString = par[1];
//                this.ruhmesHalleSuchName = true;
//            };
//            if (!this.OnStage(this.IMG_SCR_HALLE_BG)){
//                this.ShowHallScreen();
//            };
//            _local3 = this.actor[this.CNT_HALL_LIST];
//            with (_local3) {
//                while (numChildren > 0) {
//                    removeChildAt(0);
//                };
//            };
//            if (this.textDir == "right"){
//                HallListAddField((this.REL_HALL_LIST_COLUMN_6_X + 40), this.REL_HALL_LIST_LINES_Y, this.txt[this.TXT_HALL_LIST_COLUMN_1], this.FontFormat_HallListHeading);
//                HallListAddField((this.REL_HALL_LIST_COLUMN_6_X - 10), this.REL_HALL_LIST_LINES_Y, this.txt[((this.GuildHallMode) ? this.TXT_HALL_LIST_COLUMN_3 : this.TXT_HALL_LIST_COLUMN_2)], this.FontFormat_HallListHeading);
//                HallListAddField((this.REL_HALL_LIST_COLUMN_2_X - 10), this.REL_HALL_LIST_LINES_Y, this.txt[this.TXT_HALL_LIST_COLUMN_5], this.FontFormat_HallListHeading);
//                HallListAddField((this.REL_HALL_LIST_COLUMN_4_X + 20), this.REL_HALL_LIST_LINES_Y, this.txt[((this.GuildHallMode) ? this.TXT_GUILDHALL_LEADER : this.TXT_HALL_LIST_COLUMN_3)], this.FontFormat_HallListHeading);
//                HallListAddField((this.REL_HALL_LIST_COLUMN_3_X + 25), this.REL_HALL_LIST_LINES_Y, this.txt[((this.GuildHallMode) ? this.TXT_GUILDHALL_MEMBERS : this.TXT_HALL_LIST_COLUMN_4)], this.FontFormat_HallListHeading);
//            } else {
//                HallListAddField(this.REL_HALL_LIST_COLUMN_1_X, this.REL_HALL_LIST_LINES_Y, this.txt[this.TXT_HALL_LIST_COLUMN_1], this.FontFormat_HallListHeading);
//                HallListAddField(this.REL_HALL_LIST_COLUMN_2_X, this.REL_HALL_LIST_LINES_Y, this.txt[((this.GuildHallMode) ? this.TXT_HALL_LIST_COLUMN_3 : this.TXT_HALL_LIST_COLUMN_2)], this.FontFormat_HallListHeading);
//                HallListAddField(this.REL_HALL_LIST_COLUMN_6_X, this.REL_HALL_LIST_LINES_Y, this.txt[this.TXT_HALL_LIST_COLUMN_5], this.FontFormat_HallListHeading);
//                HallListAddField(this.REL_HALL_LIST_COLUMN_4_X, this.REL_HALL_LIST_LINES_Y, this.txt[((this.GuildHallMode) ? this.TXT_GUILDHALL_LEADER : this.TXT_HALL_LIST_COLUMN_3)], this.FontFormat_HallListHeading);
//                HallListAddField(this.REL_HALL_LIST_COLUMN_5_X, this.REL_HALL_LIST_LINES_Y, this.txt[((this.GuildHallMode) ? this.TXT_GUILDHALL_MEMBERS : this.TXT_HALL_LIST_COLUMN_4)], this.FontFormat_HallListHeading);
//            };
//            HallListName = new Array();
//            HallListGilde = new Array();
//            tmpArray = par[0].split("/");
//            line = 1;
//            i = 0;
//            while (i < (tmpArray.length - 1)) {
//                if (this.ruhmesHalleSuchString.toLowerCase() == tmpArray[(i + ((this.ruhmesHalleSuchName) ? ((this.GuildHallMode) ? 2 : 1) : 0))].toLowerCase()){
//                    tmpFmt = this.FontFormat_HallListHighLight;
//                } else {
//                    if (((this.GuildHallMode) && ((int(tmpArray[(i + 3)]) < 0)))){
//                        tmpFmt = this.FontFormat_GuildHallNoAttack;
//                    } else {
//                        if (((!(this.GuildHallMode)) && (!((this.lastAttacked.indexOf(tmpArray[(i + 1)].toLowerCase()) == -1))))){
//                            tmpFmt = this.FontFormat_GuildHallNoAttack;
//                        } else {
//                            tmpFmt = this.FontFormat_HallListText;
//                        };
//                    };
//                };
//                this.lastHallMembers.push(tmpArray[(i + 1)]);
//                this.arrowHallMode = true;
//                if (this.textDir == "right"){
//                    HallListAddField((this.REL_HALL_LIST_COLUMN_6_X + 40), (this.REL_HALL_LIST_LINES_Y + (line * this.REL_HALL_LIST_LINE_Y)), Math.abs(tmpArray[i]), tmpFmt, 0, this.GuildHallMode);
//                    HallListAddField((this.REL_HALL_LIST_COLUMN_6_X - 10), ((this.REL_HALL_LIST_LINES_Y + (line * this.REL_HALL_LIST_LINE_Y)) + 5), ((this.GuildHallMode) ? "" : (((tmpArray[i] < 0)) ? "[J]" : (((tmpArray[(i + 3)] < 0)) ? "[M]" : "[K]"))), tmpFmt);
//                    i = (i + 1);
//                    HallListName[line] = tmpArray[i];
//                    i = (i + 1);
//                    HallListAddField(((this.GuildHallMode) ? (this.REL_HALL_LIST_COLUMN_4_X + 20) : (this.REL_HALL_LIST_COLUMN_6_X - 30)), (this.REL_HALL_LIST_LINES_Y + (line * this.REL_HALL_LIST_LINE_Y)), tmpArray[i], tmpFmt, ((this.GuildHallMode) ? ((this.REL_HALL_LIST_COLUMN_5_X - this.REL_HALL_LIST_COLUMN_4_X) - 10) : ((this.REL_HALL_LIST_COLUMN_4_X - this.REL_HALL_LIST_COLUMN_3_X) - 10)));
//                    HallListGilde[line] = tmpArray[i];
//                    i = (i + 1);
//                    HallListAddField(((this.GuildHallMode) ? (this.REL_HALL_LIST_COLUMN_6_X - 10) : (this.REL_HALL_LIST_COLUMN_4_X + 20)), (this.REL_HALL_LIST_LINES_Y + (line * this.REL_HALL_LIST_LINE_Y)), (((tmpArray[i] == "")) ? this.txt[this.TXT_NOGUILD] : tmpArray[(i - 1)]), tmpFmt, ((this.GuildHallMode) ? ((this.REL_HALL_LIST_COLUMN_4_X - this.REL_HALL_LIST_COLUMN_2_X) - 10) : ((this.REL_HALL_LIST_COLUMN_5_X - this.REL_HALL_LIST_COLUMN_4_X) - 10)), true);
//                    i = (i + 1);
//                    HallListAddField((this.REL_HALL_LIST_COLUMN_3_X + 25), (this.REL_HALL_LIST_LINES_Y + (line * this.REL_HALL_LIST_LINE_Y)), Math.abs(tmpArray[i]), tmpFmt, 0, this.GuildHallMode);
//                    HallListAddField((this.REL_HALL_LIST_COLUMN_2_X - 10), (this.REL_HALL_LIST_LINES_Y + (line * this.REL_HALL_LIST_LINE_Y)), (((tmpArray[i] == 1)) ? 0 : tmpArray[i]), tmpFmt, 0, this.GuildHallMode);
//                } else {
//                    HallListAddField(this.REL_HALL_LIST_COLUMN_1_X, (this.REL_HALL_LIST_LINES_Y + (line * this.REL_HALL_LIST_LINE_Y)), Math.abs(tmpArray[i]), tmpFmt, 0, this.GuildHallMode);
//                    HallListAddField(this.REL_HALL_LIST_COLUMN_2_X, ((this.REL_HALL_LIST_LINES_Y + (line * this.REL_HALL_LIST_LINE_Y)) + 5), ((this.GuildHallMode) ? "" : (((tmpArray[i] < 0)) ? "[J]" : (((tmpArray[(i + 3)] < 0)) ? "[M]" : "[K]"))), tmpFmt);
//                    i = (i + 1);
//                    HallListName[line] = tmpArray[i];
//                    i = (i + 1);
//                    HallListAddField(((this.GuildHallMode) ? this.REL_HALL_LIST_COLUMN_4_X : this.REL_HALL_LIST_COLUMN_3_X), (this.REL_HALL_LIST_LINES_Y + (line * this.REL_HALL_LIST_LINE_Y)), tmpArray[i], tmpFmt, ((this.GuildHallMode) ? ((this.REL_HALL_LIST_COLUMN_5_X - this.REL_HALL_LIST_COLUMN_4_X) - 10) : ((this.REL_HALL_LIST_COLUMN_4_X - this.REL_HALL_LIST_COLUMN_3_X) - 10)));
//                    HallListGilde[line] = tmpArray[i];
//                    i = (i + 1);
//                    HallListAddField(((this.GuildHallMode) ? this.REL_HALL_LIST_COLUMN_2_X : this.REL_HALL_LIST_COLUMN_4_X), (this.REL_HALL_LIST_LINES_Y + (line * this.REL_HALL_LIST_LINE_Y)), (((tmpArray[i] == "")) ? this.txt[this.TXT_NOGUILD] : tmpArray[(i - 1)]), tmpFmt, ((this.GuildHallMode) ? ((this.REL_HALL_LIST_COLUMN_4_X - this.REL_HALL_LIST_COLUMN_2_X) - 10) : ((this.REL_HALL_LIST_COLUMN_5_X - this.REL_HALL_LIST_COLUMN_4_X) - 10)), true);
//                    i = (i + 1);
//                    HallListAddField(this.REL_HALL_LIST_COLUMN_5_X, (this.REL_HALL_LIST_LINES_Y + (line * this.REL_HALL_LIST_LINE_Y)), Math.abs(tmpArray[i]), tmpFmt, 0, this.GuildHallMode);
//                    HallListAddField(this.REL_HALL_LIST_COLUMN_6_X, (this.REL_HALL_LIST_LINES_Y + (line * this.REL_HALL_LIST_LINE_Y)), (((tmpArray[i] == 1)) ? 0 : tmpArray[i]), tmpFmt, 0, this.GuildHallMode);
//                };
//                line = (line + 1);
//                i = (i + 1);
//            };
//            break;
//        case Const.RESP_ARBEIT_START:
//        case Const.RESP_ARBEIT_STOP:
//            ParseSavegame(par[0]);
//            this.ShowWorkScreen();
//            break;
//        case Const.RESP_ARBEIT_ERLEDIGT:
//            ParseSavegame(par[0]);
//            this.VerdientesGeld = par[1];
//            this.ShowWorkSuccessScreen();
//            break;
        case Const.ACT_SCREEN_ARBEITEN:
			int gold = parseInt(par[0]);
			com.print(String.format("You are at WORK (will earn G #_Y%d#Z S #_K%d#Z per hour), come back later! You can 'abort work'.", gold / 100, gold % 100));
            break;
//        case Const.RESP_SAVEGAME_STAY_ERROR:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_SELL_ITEM]);
//        case Const.RESP_SAVEGAME_STAY:
//        case Const.RESP_SAVEGAME_SHARD:
//        case Const.RESP_SAVEGAME_MIRROR:
//        case Const.RESP_MOVE_TOWER_ITEM:
//            ParseSavegame(par[0]);
//            if (this.OnStage(this.IMG_SCR_CHAR_BG)){
//                if (act == this.RESP_MOVE_TOWER_ITEM){
//                    this.ShowTowerScreen(par);
//                } else {
//                    if (act == this.RESP_SAVEGAME_SHARD){
//                        this.Play(this.SND_SHARD);
//                        this.mirrorFadeAmount = 0.2;
//                        this.mirrorAniTimer.start();
//                    };
//                    if (act == this.RESP_SAVEGAME_MIRROR){
//                        this.Play(this.SND_MIRROR);
//                    };
//                    this.DisplayInventory(undefined, this.OnStage(this.IMG_SCR_CHAR_BG_RIGHT));
//                    i = 0;
//                    while (i < 13) {
//                        if (this.MirrorPieces[i]){
//                            this.Add((this.IMG_MIRROR_PIECE + i));
//                        } else {
//                            this.Remove((this.IMG_MIRROR_PIECE + i));
//                        };
//                        i = (i + 1);
//                    };
//                };
//            };
//            break;
//        case Const.ACT_SCREEN_CHAR:
//            ParseSavegame(par[0]);
//            this.PlayerDesc = this.resolveBreaks(par[1]);
//            if (this.Savegame[this.SG_FACE_1] == 0){
//                this.ShowBuildCharacterScreen();
//            } else {
//                this.ShowCharacterScreen(undefined, true);
//            };
//            break;
//        case Const.ACT_SCREEN_ZAUBERLADEN:
//            ParseSavegame(par[0]);
//            if (par[1]){
//                this.specialAction = par[1];
//            } else {
//                this.specialAction = 0;
//            };
//            if (this.OnStage(this.IMG_SCR_FIDGET_BG)){
//                this.DisplayInventory();
//            } else {
//                this.Load(this.IMG_SCR_FIDGET_BG);
//                this.ShowCharacterScreen();
//                this.WhenLoaded(DoActZauberladen);
//            };
//            break;
//        case Const.ACT_SCREEN_SCHMIEDE:
//            ParseSavegame(par[0]);
//            if (par[1]){
//                this.specialAction = par[1];
//            } else {
//                this.specialAction = 0;
//            };
//            if (this.OnStage(this.IMG_SCR_SHAKES_BG)){
//                this.DisplayInventory();
//            } else {
//                this.Load(this.IMG_SCR_SHAKES_BG);
//                this.ShowCharacterScreen();
//                this.WhenLoaded(DoActSchmiede);
//            };
//            break;
//        case Const.RESP_UPDATE_CHECK:
//            ExternalInterface.call("refresh");
//            break;
//        case Const.RESP_LOGIN_SUCCESS_BOUGHT:
        case Const.RESP_LOGIN_SUCCESS:
            gd.setBeerFest(parseBool(par[5]));

            if (StringUtils.isNotBlank(par[2])) {
                gd.setSessionId(par[2]);
            };
//            
//            this.LevelUp = false;
//            this.LastLevel = 0;
//            this.oldAch = new Array();
//            this.oldAlbum = -1;
//            this.AlbumEffect = false;
//            this.PreviousLogin = true;
//            this.GildenID = 0;
            parseSavegame(gd, par[0]);
            
//            if (par[1]){
//                this.DealerAktion = int(par[1]);
//            } else {
//                this.DealerAktion = 0;
//            };
//            if (this.DealerAktion > 0){
//                this.PulseDealer = true;
//            } else {
//                this.PulseDealer = false;
//            };
//            this.so.data.skipAutoLogin = false;
//            if (!this.so.data.HasAccount){
//                this.so.data.PaymentMethod = 4;
//            };
//            this.so.data.HasAccount = true;
//            this.so.data.userName = this.actor[this.INP_NAME].getChildAt(1).text;
//            this.so.data.password = this.actor[this.INP_LOGIN_PASSWORD].getChildAt(1).text;
//            this.so.flush();
//            this.Add(this.CNT_IF_LOGOUT);
//            if (this.Savegame[this.SG_FACE_1] == 0){
//                this.trc("Fehler: Charakter nicht initialisiert.");
//                this.RequestLogout();
//            } else {
//                ParseSavegame(par[0]);
//                if (this.view_player != ""){
//                    this.selName = this.view_player;
//                    this.SendAction(this.ACT_REQUEST_CHAR, this.view_player);
//                } else {
//                    if (this.param_hall != ""){
//                        this.SendAction(this.ACT_SCREEN_EHRENHALLE, this.param_hall, "-2");
//                        this.param_valid = "";
//                    } else {
//                        if ((((int(this.Savegame[this.SG_EMAIL_VALID]) < 1)) && ((int(this.Savegame[this.SG_SERVER_TIME]) > (int(this.Savegame[this.SG_EMAIL_DATE]) + (((2 * 60) * 24) * 60)))))){
//                            if (this.param_valid != ""){
//                                this.SendAction(this.ACT_VALIDATE, this.param_valid);
//                                this.param_valid = "";
//                            } else {
//                                this.ShowEmailNagScreen();
//                            };
//                        } else {
//                            if (int(this.Savegame[this.SG_EMAIL_VALID]) == 1){
//                                if (this.param_valid != ""){
//                                    this.ShowEmailNagScreen(3);
//                                } else {
//                                    this.ShowCityScreen();
//                                };
//                            } else {
//                                if (this.param_valid != ""){
//                                    this.SendAction(this.ACT_VALIDATE, this.param_valid);
//                                    this.param_valid = "";
//                                } else {
//                                    this.ShowCityScreen();
//                                };
//                            };
//                        };
//                    };
//                };
//            };
            break;
//        case Const.ERR_ALREADY_IN_GUILD:
//        case Const.ERR_NO_INDEX_FREE:
//        case Const.ERR_FIGHT_SELF:
//        case Const.ERR_GUILD_NOT_FOUND:
//        case Const.ERR_GUILD_NOT_ALLOWED:
//        case Const.ERR_GUILD_LACK_MUSH:
//        case Const.ERR_GUILD_LACK_GOLD:
//        case Const.ERR_GUILD_BUILDING_NOT_FOUND:
//        case Const.ERR_GUILD_BUILDING_MAX:
//        case Const.ERR_GUILD_NOT_MEMBER:
//        case Const.ERR_GUILD_MASTER_CANT_BE_OFFICER:
//        case Const.ERR_GUILD_IS_FULL:
//        case Const.ERR_GUILD_ALREADY_YOU_OTHER:
//        case Const.ERR_GUILD_NOT_REAL_MEMBER:
//        case Const.ERR_GUILD_ALREADY_YOU_THIS:
//        case Const.ERR_GUILD_PLAYER_NOT_FOUND:
//        case Const.ERR_SUBJECT_TOO_SHORT:
//        case Const.ERR_GUILD_TOO_EXPENSIVE:
//        case Const.ERR_GUILD_CHAT_NOT_MEMBER:
//        case Const.ERR_GUILD_CHAT_HISTORY:
//        case Const.ERR_GUILD_CHAT_TEXT_ERROR:
//        case Const.ERR_BEER:
//        case Const.ERR_NO_MUSH_BAR:
//        case Const.ERR_NO_ENDURANCE:
//        case Const.ERR_WORSE_MOUNT:
//        case Const.ERR_GUILD_ALREADY_MEMBER:
//        case Const.ERR_NOT_INVITED:
//        case Const.ERR_NO_MUSH_PVP:
//        case Const.ERR_NO_MUSH_MQ:
//            this.ErrorMessage(this.txt[((this.TXT_ERROR_ALREADY_IN_GUILD - Math.abs(this.ERR_ALREADY_IN_GUILD)) + Math.abs(act))]);
//            break;
//        case Const.ERR_BOOST:
//            break;
//        case Const.RESP_ACCOUNT_SUCCESS:
//            this.actor[this.INP_LOGIN_PASSWORD].getChildAt(1).text = this.actor[this.INP_PASSWORD].getChildAt(1).text;
//            this.so.data.skipAutoLogin = false;
//            this.so.data.HasAccount = true;
//            this.so.data.hadAccount = true;
//            this.so.data.userName = this.actor[this.INP_NAME].getChildAt(1).text;
//            this.so.data.password = this.actor[this.INP_LOGIN_PASSWORD].getChildAt(1).text;
//            this.so.data.advpar = this.paramObj;
//            this.so.flush();
//            logInAfterPixel = true;
//            break;
//        case Const.ERR_NAME_EXISTS:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_NAME_EXISTS]);
//            break;
//        case Const.ERR_NAME_TOO_SHORT:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_NAME_TOO_SHORT]);
//            break;
//        case Const.ERR_PASSWORD_TOO_SHORT:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_PASSWORD_TOO_SHORT]);
//            break;
//        case Const.ERR_EMAIL_REJECTED:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_EMAIL_REJECTED]);
//            break;
//        case Const.ERR_NAME_REJECTED:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_NAME_REJECTED]);
//            break;
        case Const.ERR_LOGIN_FAILED:
        	com.print("#_RLogin failed, check your credentials and try again.\n");
        	return false;
//            this.so.data.skipAutoLogin = true;
//            this.so.data.password = "";
//            this.so.flush();
//            this.actor[this.INP_EMAIL].getChildAt(1).text = "";
//            this.actor[this.INP_PASSWORD].getChildAt(1).text = "";
//            this.CharVolk = 0;
//            this.ShowLoginScreen(undefined, true, true);
//            this.ErrorMessage(this.txt[this.TXT_ERROR_LOGIN_FAILED]);
//            break;
        case Const.ERR_TOO_EXPENSIVE:
        	com.print("#_RYou don't have enough money.\n");
        	break;
//            if (this.OnStage(this.BTN_MODIFY_CHARACTER)){
//                this.CharVolk = this.revertCharVolk;
//                this.CharMann = this.revertCharMann;
//                this.CharColor = this.revertCharColor;
//                this.CharMouth = this.revertCharMouth;
//                this.CharBeard = this.revertCharBeard;
//                this.CharNose = this.revertCharNose;
//                this.CharEyes = this.revertCharEyes;
//                this.CharBrows = this.revertCharBrows;
//                this.CharEars = this.revertCharEars;
//                this.CharHair = this.revertCharHair;
//                this.CharSpecial = this.revertCharSpecial;
//                this.CharSpecial2 = this.revertCharSpecial2;
//                this.ShowOptionScreen();
//            };
//            this.ErrorMessage(this.txt[this.TXT_ERROR_TOO_EXPENSIVE]);
//            break;
//        case Const.ERR_WRONG_PASSWORD:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_WRONG_PASSWORD]);
//            break;
//        case Const.ERR_FACE_DATA_INCORRECT:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_FACE_DATA_INCORRECT]);
//            break;
//        case Const.ERR_EMAIL_WRONG:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_EMAIL_WRONG]);
//            break;
//        case Const.RESP_PLAYER_NOT_FOUND:
//            this.ErrorMessage(this.txt[this.TXT_ERROR_PLAYER_NOT_FOUND]);
//            break;
        }
		return true;
	}

	private void parseSavegame(Game g, String sg) {
		g.restoreFromSave(sg);
	}
	
	public String toString(int i) {
		return Integer.toString(i);
	}
	
	public int parseInt(String str) {
		if (str == null) {
			return 0;
		}
		
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public boolean parseBool(String str) {
		return parseInt(str) > 0;
	}
}
