package mowang.ModCore;

//import ModExample.Cards.AbstractExampleCard;
//import ModExample.Cards.Skill.*;
//import ModExample.Cards.special.*;
//import ModExample.Characters.MyCharacter;
//import ModExample.Relics.BaseRelic;
//import ModExample.Relics.MyRelic;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import mowang.Cards.AbstractExampleCard;
import mowang.Characters.MyCharacter;
import mowang.Helpers.ModHelper;

import java.nio.charset.StandardCharsets;

import static mowang.Characters.MyCharacter.Enums.EXAMPLE_CARD;
import static mowang.Characters.MyCharacter.Enums.MY_CHARACTER;

@SpireInitializer // 加载mod的注解
public class ExampleMod implements
        EditCardsSubscriber,
        EditStringsSubscriber,
        EditCharactersSubscriber,
        EditRelicsSubscriber,
        EditKeywordsSubscriber,
        OnPlayerTurnStartSubscriber
{

    private static final String MY_CHARACTER_BUTTON = "ModExampleResources/img/char/Character_Button.png";
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = "ModExampleResources/img/char/Character_Portrait.png";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "ModExampleResources/img/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "ModExampleResources/img/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "ModExampleResources/img/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "ModExampleResources/img/char/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "ModExampleResources/img/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "ModExampleResources/img/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "ModExampleResources/img/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "ModExampleResources/img/char/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENEYGY_ORB = "ModExampleResources/img/char/cost_orb.png";

    public static final Color MY_COLOR = new Color(1.0F, 1.0F, 1.0F, 0.8F);
//    public static final Color MY_COLOR = new Color(79.0F / 255.0F, 185.0F / 255.0F, 9.0F / 255.0F, 1.0F);
//AbstractCard.CardColor color,
// Color bgColor,
// Color backColor,
// Color frameColor,
// Color frameOutlineColor,
// Color descBoxColor,
// Color trailVfxColor,
// Color glowColor
    public ExampleMod() {
        BaseMod.subscribe(this); // 告诉basemod你要订阅事件
        BaseMod.addColor(EXAMPLE_CARD, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512,ENEYGY_ORB,BG_ATTACK_1024,BG_SKILL_1024,BG_POWER_1024,BIG_ORB,SMALL_ORB);
    }

    public static void initialize() {
        new ExampleMod();
    }

    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
        BaseMod.addCharacter(new MyCharacter(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, MY_CHARACTER);
    }
    // 当basemod开始注册mod卡牌时，便会调用这个函数
    @Override
    public void receiveEditCards() {
        // 向basemod注册卡牌

        new AutoAdd("mowang") // ${project.artifactId}
                .packageFilter(AbstractExampleCard.class) // filters to any class in the same package as AbstractDefaultCard, nested packages included
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditRelics() {
//        BaseMod.addRelicToCustomPool(new MyRelic(), EXAMPLE_CARD);
//        BaseMod.addRelic(new MyRelic(), RelicType.SHARED); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
//        BaseMod.addRelic(new BaseRelic(), RelicType.SHARED); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
//        UnlockTracker.markRelicAsSeen(MyRelic.ID);
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "zhs";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "zhs"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
        }

        String json = Gdx.files.internal("ModExampleResources/localization/" + lang + "/keywords.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("mowang", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
    public void receiveEditStrings() {
//        if (Settings.language == Settings.GameLanguage.ZHS) {
//            lang = "zhs"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
//        } else {
//            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
//        }
        String lang = "zhs";
        BaseMod.loadCustomStringsFile(CardStrings.class, "ModExampleResources/localization/" + lang + "/cards.json"); // 加载相应语言的卡牌本地化内容。
        // 如果是中文，加载的就是"ExampleResources/localization/ZHS/cards.json"
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "ModExampleResources/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "ModExampleResources/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "ModExampleResources/localization/" + lang + "/powers.json");
    }

    @Override
    public void receiveOnPlayerTurnStart() {
        ModHelper.hasReturn = false;
    }
}
