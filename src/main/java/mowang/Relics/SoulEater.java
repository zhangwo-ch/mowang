package mowang.Relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import mowang.Helpers.ModHelper;

public class SoulEater extends CustomRelic {
    // 遗物ID
    public static final String ID = ModHelper.MakePath("MyRelic");
    // 图片路径
    private static final String IMG_PATH = "ModExampleResources/img/relics/"+SoulEater.class.getSimpleName()+".png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public SoulEater() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
        counter = 0;
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onMonsterDeath(AbstractMonster m) {
        if (m.currentHealth == 0 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            addToBot(new HealAction(AbstractDungeon.player,AbstractDungeon.player,3));
            this.addToBot(new RelicAboveCreatureAction(m, this));
            counter++;
        }
    }

    public AbstractRelic makeCopy() {
        return new SoulEater();
    }
}