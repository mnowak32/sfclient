package pl.codebrewery.sfgame.model;

public class Const {
    public static final int ACT_ACCOUNT_CREATE = 1;
    public static final int ACT_LOGIN = 2;
    public static final int ACT_FORGOT_PASSWORD = 3;
    public static final int ACT_CHAR_CREATE = 501;
    public static final int ACT_ARBEIT = 502;
    public static final int ACT_REQUEST_GUILD = 503;
    public static final int ACT_INVENTORY_CHANGE = 504;
    public static final int ACT_ARBEIT_CANCEL = 505;
    public static final int ACT_REQUEST_NEWWAREZ = 506;
    public static final int ACT_POST_READ = 507;
    public static final int ACT_POST_DELETE = 508;
    public static final int ACT_POST_SEND = 509;
    public static final int ACT_QUEST_BEGIN = 510;
    public static final int ACT_QUEST_CANCEL = 511;
    public static final int ACT_START_FIGHT = 0x0200;
    public static final int ACT_REQUEST_CHAR = 513;
    public static final int ACT_SET_PLAYER_DESC = 0x0202;
    public static final int ACT_RE_LOGIN = 515;
    public static final int ACT_SEND_CHAT = 516;
    public static final int ACT_GET_CHAT_HISTORY = 517;
    public static final int ACT_BUY_BEER = 518;
    public static final int ACT_MAINQUEST = 519;
    public static final int ACT_REQUEST_TRANS_COUNT = 530;
    public static final int ACT_RESEND_EMAIL = 531;
    public static final int ACT_VALIDATE = 532;
    public static final int ACT_REQUEST_GUILD_NAMES = 533;
    public static final int ACT_REVOLT = 534;
    public static final int ACT_LOGOUT = 535;
    public static final int ACT_POST_SEND_GUILD = 536;
    public static final int ACT_WHISPER = 537;
    public static final int ACT_SCREEN_CHAR = 4;
    public static final int ACT_SCREEN_POST = 5;
    public static final int ACT_SCREEN_GILDEN = 6;
    public static final int ACT_SCREEN_EHRENHALLE = 7;
    public static final int ACT_SCREEN_WELTKARTE = 8;
    public static final int ACT_SCREEN_OPTIONEN = 9;
    public static final int ACT_SCREEN_TAVERNE = 10;
    public static final int ACT_SCREEN_ARENA = 11;
    public static final int ACT_SCREEN_ARBEITEN = 12;
    public static final int ACT_SCREEN_SCHMIEDE = 13;
    public static final int ACT_SCREEN_ZAUBERLADEN = 14;
    public static final int ACT_SCREEN_STALL = 15;
    public static final int ACT_SCREEN_PILZDEALER = 16;
    public static final int ACT_SCREEN_GILDE_GRUENDEN = 17;
    public static final int ACT_BUY_MOUNT = 20;
    public static final int ACT_BUY_ATTRIB = 21;
    public static final int ACT_PLACE_BET = 22;
    public static final int ACT_SCREEN_FREMDGILDE = 23;
    public static final int ACT_SCREEN_GILDENHALLE = 24;
    public static final int ACT_DEALER_AKTION = 25;
    public static final int ACT_DEALER_SPONSOR = 26;
    public static final int ACT_KILL_POTION = 27;
    public static final int ACT_GUILD_FOUND = 101;
    public static final int ACT_GUILD_DELETE = 102;
    public static final int ACT_GUILD_INVITE = 103;
    public static final int ACT_GUILD_EXPEL = 104;
    public static final int ACT_GUILD_SET_MASTER = 105;
    public static final int ACT_GUILD_SET_OFFICER = 106;
    public static final int ACT_GUILD_IMPROVE = 107;
    public static final int ACT_GUILD_SET_DESC = 108;
    public static final int ACT_GUILD_RENAME = 109;
    public static final int ACT_GUILD_JOIN = 110;
    public static final int ACT_GUILD_DONATE = 111;
    public static final int ACT_GUILD_JOIN_ATTACK = 112;
    public static final int ACT_GUILD_JOIN_DEFENSE = 113;
    public static final int ACT_GUILD_COMMENCE_ATTACK = 114;
    public static final int ACT_INVITE_PLAYER = 115;
    public static final int ACT_ALBUM = 116;
    public static final int ACT_BUY_LUXURY = 195;
    public static final int ACT_LOAD_CATAPULT = 196;
    public static final int ACT_SCREEN_TOILET = 303;
    public static final int ACT_TOILET_FLUSH = 302;
    public static final int ACT_QUEST_SKIP = 189;
    public static final int ACT_SCREEN_TOWER = 312;
    public static final int ACT_TOWER_TRY = 313;
    public static final int ACT_COPYCAT_BOOST = 314;
    public static final int ACT_ROB_PLAYER = 313;
    public static final int ACT_MOVE_COPYCAT_ITEM = 318;
    public static final int ACT_CHANGE_FACE = 801;
    public static final int ACT_DELETE_ACCOUNT = 802;
    public static final int ACT_CHANGE_NAME = 803;
    public static final int ACT_CHANGE_MAIL = 804;
    public static final int ACT_CHANGE_PASS = 805;
    public static final int ACT_PORTAL_FIGHT = 358;
    public static final int ACT_PORTAL_FIGHT_SINGLE = 360;

    
    public static final int RESP_ACCOUNT_SUCCESS = 1;
    public static final int RESP_LOGIN_SUCCESS = 2;
    public static final int RESP_FAME_LIST = 3;
    public static final int RESP_SCREEN_BUILDCHAR = 4;
    public static final int RESP_GUILD_DATA = 101;
    public static final int RESP_SAVEGAME_STAY = 102;
    public static final int RESP_ARBEIT_ERLEDIGT = 103;
    public static final int RESP_ARBEIT_START = 104;
    public static final int RESP_ARBEIT_STOP = 105;
    public static final int RESP_QUEST_DONE = 106;
    public static final int RESP_QUEST_START = 107;
    public static final int RESP_QUEST_STOP = 108;
    public static final int RESP_PLAYER_DESC_SUCCESS = 109;
    public static final int RESP_PLAYER_SCREEN = 111;
    public static final int RESP_PLAYER_NOT_FOUND = 112;
    public static final int RESP_DEMO_SCREEN = 113;
    public static final int RESP_ATTACK_NOT_EXIST = 114;
    public static final int RESP_CHANGE_FACE_OK = 115;
    public static final int RESP_CHANGE_PASS_OK = 116;
    public static final int RESP_CHANGE_NAME_OK = 117;
    public static final int RESP_CHANGE_MAIL_OK = 118;
    public static final int RESP_DELETE_ACCOUNT_OK = 119;
    public static final int RESP_NO_LOGIN = 120;
    public static final int RESP_REQUEST_GUILD = 121;
    public static final int RESP_MAINQUEST = 122;
    public static final int RESP_GUILD_FOUND_SUCCESS = 150;
    public static final int RESP_GUILD_DELETE_SUCCESS = 151;
    public static final int RESP_GUILD_RENAME_SUCCESS = 152;
    public static final int RESP_GUILD_CHANGE_DESC_SUCCESS = 153;
    public static final int RESP_GUILD_IMPROVE_SUCCESS = 154;
    public static final int RESP_GUILD_OFFICER_SUCCESS = 155;
    public static final int RESP_GUILD_EXPEL_SUCCESS = 156;
    public static final int RESP_GUILD_INVITE_SUCCESS = 157;
    public static final int RESP_GUILD_JOIN_SUCCESS = 158;
    public static final int RESP_GUILD_MASTER_SUCCESS = 159;
    public static final int RESP_GUILD_DONATE_SUCCESS = 160;
    public static final int RESP_CHAT_HISTORY = 161;
    public static final int RESP_CHAT_LINE = 162;
    public static final int RESP_TRANS_COUNT = 163;
    public static final int RESP_EMAIL_RESENT = 164;
    public static final int RESP_PASSWORD_SENT = 165;
    public static final int RESP_VALIDATE_OK = 166;
    public static final int RESP_UPDATE_CHECK = 167;
    public static final int RESP_BET_WON = 168;
    public static final int RESP_BET_LOST = 169;
    public static final int RESP_SCREEN_GILDENHALLE = 170;
    public static final int RESP_OTHER_GUILD = 172;
    public static final int RESP_SAVEGAME_STAY_ERROR = 173;
    public static final int RESP_DEALER_AKTION = 174;
    public static final int RESP_DEALER_SPONSOR = 176;
    public static final int RESP_GUILD_FIGHT = 178;
    public static final int RESP_GUILD_JOIN_ATTACK_OK = 179;
    public static final int RESP_GUILD_JOIN_DEFENSE_OK = 180;
    public static final int RESP_GUILD_COMMENCE_ATTACK_OK = 181;
    public static final int RESP_GUILD_NAMES = 183;
    public static final int RESP_LOGIN_SUCCESS_BOUGHT = 184;
    public static final int RESP_REQUEST_GUILD_QUIET = 186;
    public static final int RESP_LOGOUT_SUCCESS = 187;
    public static final int RESP_QUEST_DONE_PIXEL = 188;
    public static final int RESP_WHISPER_SUCCESS = 190;
    public static final int RESP_INVITE_SUCCESS = 191;
    public static final int RESP_ALBUM = 192;
    public static final int RESP_QUEST_SKIP_ALLOWED = 193;
    public static final int RESP_QUEST_SKIP_ALLOWED_START = 194;
    public static final int RESP_TOILET_LOCKED = 304;
    public static final int RESP_TOILET_DROPPED = 305;
    public static final int RESP_TOILET_FULL = 306;
    public static final int RESP_TOILET_FLUSHED = 308;
    public static final int RESP_TOILET_UNLOCKED = 309;
    public static final int RESP_TOILET_TANKFULL = 311;
    public static final int RESP_TOWER_SAVE = 315;
    public static final int RESP_SAVEGAME_SHARD = 316;
    public static final int RESP_SAVEGAME_MIRROR = 317;
    public static final int RESP_MOVE_TOWER_ITEM = 319;
    public static final int RESP_TOWER_FIGHT = 321;
    public static final int RESP_READ_MESSAGE = 201;
    public static final int RESP_MESSAGE_SENT = 202;
    public static final int REPS_TOILET_DROPTWICE = 357;
    public static final int RESP_PORTAL_FIGHT = 359;
    public static final int RESP_PORTAL_FIGHT_SINGLE = 361;

    
    public static final int ERR_DEALER_AKTION = 175;
    public static final int ERR_DEALER_SPONSOR = 177;
    public static final int ERR_INBOX_FULL = 203;
    public static final int ERR_RECIPIENT_NOT_FOUND = 204;
    public static final int ERR_RECIPIENT_SELF = 205;
    public static final int ERR_NAME_EXISTS = -1;
    public static final int ERR_NAME_TOO_SHORT = -2;
    public static final int ERR_PASSWORD_TOO_SHORT = -3;
    public static final int ERR_EMAIL_REJECTED = -4;
    public static final int ERR_NAME_REJECTED = -5;
    public static final int ERR_LOGIN_FAILED = -6;
    public static final int ERR_TOO_EXPENSIVE = -7;
    public static final int ERR_WRONG_PASSWORD = -8;
    public static final int ERR_FACE_DATA_INCORRECT = -9;
    public static final int ERR_EMAIL_WRONG = -10;
    public static final int ERR_GENDER_OR_RACE = -11;
    public static final int ERR_MAIL_EXISTS = -12;
    public static final int ERR_ALREADY_IN_GUILD = -13;
    public static final int ERR_NO_INDEX_FREE = -14;
    public static final int ERR_FIGHT_SELF = -15;
    public static final int ERR_GUILD_NOT_FOUND = -16;
    public static final int ERR_GUILD_NOT_ALLOWED = -17;
    public static final int ERR_GUILD_LACK_MUSH = -18;
    public static final int ERR_GUILD_LACK_GOLD = -19;
    public static final int ERR_GUILD_BUILDING_NOT_FOUND = -20;
    public static final int ERR_GUILD_BUILDING_MAX = -21;
    public static final int ERR_GUILD_NOT_MEMBER = -22;
    public static final int ERR_GUILD_MASTER_CANT_BE_OFFICER = -23;
    public static final int ERR_GUILD_IS_FULL = -24;
    public static final int ERR_GUILD_ALREADY_YOU_OTHER = -25;
    public static final int ERR_GUILD_NOT_REAL_MEMBER = -26;
    public static final int ERR_GUILD_ALREADY_YOU_THIS = -27;
    public static final int ERR_GUILD_PLAYER_NOT_FOUND = -28;
    public static final int ERR_SUBJECT_TOO_SHORT = -29;
    public static final int ERR_GUILD_TOO_EXPENSIVE = -30;
    public static final int ERR_GUILD_CHAT_NOT_MEMBER = -31;
    public static final int ERR_GUILD_CHAT_HISTORY = -32;
    public static final int ERR_GUILD_CHAT_TEXT_ERROR = -33;
    public static final int ERR_BEER = -34;
    public static final int ERR_BOOST = -42;
    public static final int ERR_GUILD_NAME_REJECTED = -43;
    public static final int ERR_GUILD_NAME_LENGTH = -44;
    public static final int ERR_GUILD_NAME_CHARACTERS = -45;
    public static final int ERR_GUILD_EMAIL_VALIDATE = -46;
    public static final int ERR_GUILD_MUSH_FREE = -47;
    public static final int ERR_ATTACK_AGAIN = -48;
    public static final int ERR_REQUEST_PW = -49;
    public static final int ERR_VALIDATE = -50;
    public static final int ERR_NO_MUSH_BAR = -35;
    public static final int ERR_NO_ENDURANCE = -36;
    public static final int ERR_WORSE_MOUNT = -37;
    public static final int ERR_GUILD_ALREADY_MEMBER = -38;
    public static final int ERR_NOT_INVITED = -39;
    public static final int ERR_NO_MUSH_PVP = -40;
    public static final int ERR_NO_MUSH_MQ = -41;
    public static final int ERR_GUILD_DONATE_NEG = -51;
    public static final int ERR_GUILD_DONATE_FRA = -52;
    public static final int ERR_LOCKED_PAYMENT = -53;
    public static final int ERR_LOCKED_ADMIN = -54;
    public static final int ERR_TOO_SOON = -55;
    public static final int ERR_ACCOUNTS_PER_IP = -56;
    public static final int ERR_PLACE_BET = -57;
    public static final int ERR_INVENTORY_FULL = -58;
    public static final int ERR_GUILD_FIGHT_TOO_EXPENSIVE = -60;
    public static final int ERR_GUILD_ALREADY_UNDER_ATTACK = -61;
    public static final int ERR_GUILD_ATTACK_DELAY = -62;
    public static final int ERR_GUILD_ALREADY_ATTACKING = -63;
    public static final int ERR_GUILD_ATTACK_STATUS = -64;
    public static final int ERR_SESSION_ID_EXPIRED = -65;
    public static final int ERR_STOP_TUNNELING = -66;
    public static final int ERR_REVOLT_FAILED = -67;
    public static final int ERR_JOINED_TOO_RECENTLY = -68;
    public static final int ERR_SERVER_DOWN = -69;
    public static final int ERR_MSG_LEVEL_TOO_LOW = -84;
    public static final int ERR_MSG_NOT_VALIDATED = -85;
    public static final int ERR_INVENTORY_FULL_ADV = -86;
    public static final int ERR_INVITE_NOT_VALIDATED = -90;
    public static final int ERR_INVITE_TOO_MANY = -91;
    public static final int ERR_INVITE_EMAIL_REJECTED = -92;
    public static final int ERR_NO_ALBUM = -93;
    public static final int ERR_LUXURY_ALREADY = -94;
    public static final int ERR_GUILD_RANK_WRONG = -95;
    public static final int ERR_NO_CHAT_INFO = -96;
    public static final int ERR_NO_CHAT_OVERFLOW = -97;
    public static final int ERR_TOWER_CLOSED = -98;
    public static final int ERR_TOWER_ITEMMOVE = -100;
    public static final int ERR_TOWER_NO_COPYCATS = -101;
    public static final int ERR_GUILD_DESCR_TOO_LONG = -102;
    public static final int ERR_NO_SLOT_FOR_FLUSHING = -307;
    public static final int ERR_TOILET_EMPTY = -310;

    
    public static final int GUILD_RAID_LEVEL = 8;
    public static final int GUILD_IS_RAID = 9;
    public static final int GUILD_MEMBERID = 14;
    public static final int GUILD_MEMBERLEVEL = 64;
    public static final int GUILD_MEMBERONLINE = 114;
    public static final int GUILD_MEMBERHONOR = 164;
    public static final int GUILD_MEMBERGOLDSPENT = 214;
    public static final int GUILD_MEMBERMUSHSPENT = 264;
    public static final int GUILD_MEMBERRANK = 314;
    public static final int GUILD_ATTACK_TARGET = 364;
    public static final int GUILD_ATTACK_TIME = 365;
    public static final int GUILD_DEFENCE_TARGET = 366;
    public static final int GUILD_DEFENCE_TIME = 367;
    public static final int GUILD_EVENT_TRIGGER_COUNT = 368;

    public static final int SG_ITM_SIZE = 12;
    public static final int SG_BACKPACK_SIZE = 5;
    public static final int SG_INVENTORY_SIZE = 10;
    public static final int SG_ITM_TYP = 0;
    public static final int SG_ITM_PIC = 1;
    public static final int SG_ITM_SCHADEN_MIN = 2;
    public static final int SG_ITM_SCHADEN_MAX = 3;
    public static final int SG_ITM_ATTRIBTYP1 = 4;
    public static final int SG_ITM_ATTRIBTYP2 = 5;
    public static final int SG_ITM_ATTRIBTYP3 = 6;
    public static final int SG_ITM_ATTRIBVAL1 = 7;
    public static final int SG_ITM_ATTRIBVAL2 = 8;
    public static final int SG_ITM_ATTRIBVAL3 = 9;
    public static final int SG_ITM_GOLD = 10;
    public static final int SG_ITM_MUSH = 11;
    
    public static final int SG_PAYMENT_ID = 1;
    public static final int SG_PLAYER_ID = 2;
    public static final int SG_LAST_ACTION_DATE = 3;
    public static final int SG_REGISTRATION_DATE = 4;
    public static final int SG_REGISTRATION_IP = 5;
    public static final int SG_MSG_COUNT = 6;
    public static final int SG_VALIDATION_IP = 7;
    public static final int SG_LEVEL = 8;
    public static final int SG_EXP = 9;
    public static final int SG_EXP_FOR_NEXTLEVEL = 10;
    public static final int SG_HONOR = 11;
    public static final int SG_RANK = 12;
    public static final int SG_CLASS_RANK = 13;
    public static final int SG_GOLD = 14;
    public static final int SG_MUSH = 15;
    public static final int SG_MUSH_GAINED = 16;
    public static final int SG_MUSH_SPENT = 17;
    public static final int SG_FACE_1 = 18;
    public static final int SG_FACE_2 = 19;
    public static final int SG_FACE_3 = 20;
    public static final int SG_FACE_4 = 21;
    public static final int SG_FACE_5 = 22;
    public static final int SG_FACE_6 = 23;
    public static final int SG_FACE_7 = 24;
    public static final int SG_FACE_8 = 25;
    public static final int SG_FACE_9 = 26;
    public static final int SG_FACE_10 = 27;
    public static final int SG_RACE = 28;
    public static final int SG_GENDER = 29;
    public static final int SG_CLASS = 30;
    public static final int SG_ATTR_STAERKE = 31;
    public static final int SG_ATTR_BEWEGLICHKEIT = 32;
    public static final int SG_ATTR_AUSDAUER = 33;
    public static final int SG_ATTR_INTELLIGENZ = 34;
    public static final int SG_ATTR_WILLENSKRAFT = 35;
    public static final int SG_ATTR_STAERKE_BONUS = 36;
    public static final int SG_ATTR_BEWEGLICHKEIT_BONUS = 37;
    public static final int SG_ATTR_AUSDAUER_BONUS = 38;
    public static final int SG_ATTR_INTELLIGENZ_BONUS = 39;
    public static final int SG_ATTR_WILLENSKRAFT_BONUS = 40;
    public static final int SG_ATTR_STAERKE_GEKAUFT = 41;
    public static final int SG_ATTR_BEWEGLICHKEIT_GEKAUFT = 42;
    public static final int SG_ATTR_AUSDAUER_GEKAUFT = 43;
    public static final int SG_ATTR_INTELLIGENZ_GEKAUFT = 44;
    public static final int SG_ATTR_WILLENSKRAFT_GEKAUFT = 45;
    public static final int SG_ACTION_STATUS = 46;
    public static final int SG_ACTION_INDEX = 47;
    public static final int SG_ACTION_ENDTIME = 48;
    public static final int SG_INVENTORY_OFFS = 49;
    public static final int SG_BACKPACK_OFFS = 169;
    public static final int SG_QUEST_REROLL_TIME = 229;
    public static final int SG_QUEST_OFFER_LEVEL1 = 230;
    public static final int SG_QUEST_OFFER_TYPE1 = 233;
    public static final int SG_QUEST_OFFER_ENEMY1 = 236;
    public static final int SG_QUEST_OFFER_LOCATION1 = 239;
    public static final int SG_QUEST_OFFER_DURATION1 = 242;
    public static final int SG_QUEST_OFFER_REWARD_ITM1 = 245;
    public static final int SG_QUEST_OFFER_EXP1 = 281;
    public static final int SG_QUEST_OFFER_GOLD1 = 284;
    public static final int SG_MOUNT = 287;
    public static final int SG_SHAKES_REROLL_TIME = 288;
    public static final int SG_SHAKES_ITEM1 = 289;
    public static final int SG_FIDGET_REROLL_TIME = 361;
    public static final int SG_FIDGET_ITEM1 = 362;
    public static final int SG_NEXT_BATTLE_TIME = 434;
    public static final int SG_UNREAD_MESSAGES = 435;
    public static final int SG_GUILD_INDEX = 436;
    public static final int SG_GUILD_RANK = 437;
    public static final int SG_MUSHROOMS_MAY_DONATE = 438;
    public static final int SG_ALBUM = 439;
    public static final int SG_LAST_GUILD_FIGHT_EXP = 440;
    public static final int SG_ACCOUNT_PROTECTION_DATE = 441;
    public static final int SG_NEW_DUNGEONS = 442;
    public static final int SG_GUILD_JOIN_DATE = 444;
    public static final int SG_NEW_FLAGS = 445;
    public static final int SG_MUSH_BOUGHT_SINCE_LAST_LOGIN = 446;
    public static final int SG_WE_MISS_YOU = 447;
    public static final int SG_ARMOR = 448;
    public static final int SG_DAMAGE_MIN = 449;
    public static final int SG_DAMAGE_MAX = 450;
    public static final int SG_LIFE = 451;
    public static final int SG_MOUNT_DURATION = 452;
    public static final int SG_TRANSACTION_COUNT = 453;
    public static final int SG_EVASION = 454;
    public static final int SG_MAGICRSISTANCE = 455;
    public static final int SG_TIMEBAR_REROLL_TIME = 456;
    public static final int SG_TIMEBAR = 457;
    public static final int SG_BEERS = 458;
    public static final int SG_MQ_STATE = 459;
    public static final int SG_MQ_REROLL_TIME = 460;
    public static final int SG_PVP_REROLL_TIME = 461;
    public static final int SG_EXP_BONUS = 462;
    public static final int SG_GOLD_BONUS = 463;
    public static final int SG_EMAIL_VALID = 464;
    public static final int SG_EMAIL_DATE = 465;
    public static final int SG_ACHIEVEMENTS = 466;
    public static final int SG_LOCKDURATION = 476;
    public static final int SG_FOO = 477;
    public static final int SG_BAR = 478;
    public static final int SG_HELLO = 479;
    public static final int SG_FIRST_PAYMENT = 480;
    public static final int SG_DUNGEON_LEVEL = 481;
    public static final int SG_DUNGEON_13 = 491;
    public static final int SG_TOILET = 492;
    public static final int SG_PHP_SESSION = 493;
    public static final int SG_POTION_TYPE = 494;
    public static final int SG_POTION_DURATION = 497;
    public static final int SG_POTION_GAIN = 500;
    public static final int SG_POWER_LIFE_POTION = 503;
    public static final int SG_LAST_LOGIN_IP = 504;
    public static final int SG_MUSHROOM_BOUGHT_AMOUNT = 507;
    public static final int SG_MUSHROOM_BOUGHT_DATE = 508;
    public static final int SG_GUILD_FIGHT_STATUS = 509;
    public static final int SG_EVENT_TRIGGER_COUNT = 510;
    public static final int SG_SERVER_TIME = 511;
    
}
