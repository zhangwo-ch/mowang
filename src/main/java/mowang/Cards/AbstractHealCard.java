package mowang.Cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import mowang.Helpers.ModHelper;

import static basemod.BaseMod.logger;
import static mowang.Characters.MyCharacter.Enums.*;

public abstract class AbstractHealCard extends AbstractExampleCard {

    AbstractGameAction action;
    public Boolean hasRecovery;
    public AbstractHealCard(String ID, boolean useTmpArt, CardStrings strings, int COST, CardType TYPE,
                            CardRarity RARITY, CardTarget TARGET,AbstractGameAction action) {
        super(ID, useTmpArt, strings, COST, TYPE,
                RARITY, TARGET);
        this.action = action;
        hasRecovery = false;
        tags.add(Recovery);
    }
    public AbstractHealCard(String ID, String s, CardStrings strings, int COST, CardType TYPE,
                            CardRarity RARITY, CardTarget TARGET,AbstractGameAction action) {
        super(ID, s, strings, COST, TYPE,
                RARITY, TARGET);
        this.action = action;
        hasRecovery = false;
        tags.add(Recovery);
    }
    public AbstractHealCard(String ID, String s, CardStrings strings, int COST, CardType TYPE, CardColor color,
                            CardRarity RARITY, CardTarget TARGET) {
        super(ID, s, strings, COST, TYPE, color,
                RARITY, TARGET);
    }
    // 如果按这个方法实现，在cards文件夹下分别放test_attack.png、test_power.png、test_skill.png即可
    private static String GetTmpImgPath() {
        return "ModExampleResources/img/cards/Strike_attack.png";
    }
    private static String GetImgPath(CardType t, String name) {
        String type;
        switch (t) {
            case ATTACK:
                type = "attack";
                break;
            case POWER:
                type = "power";
                break;
            case STATUS:
                type = "status";
                break;
            case CURSE:
                type = "curse";
                break;
            case SKILL:
                type = "skill";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + t);
        }
        return String.format("ModExampleResources/img/cards/%s_%s.png", name,type);
    }
    @Override//这里是重点代码，这个绿色和蓝色不一定要加，拿来测试挺方便的
    public void triggerOnGlowCheck() {
        if(isTheLeftmost()) {//自己是不是最左的
            this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
            SelfHealing();//自愈代码
        }else{
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void atTurnStart() {
        hasRecovery = false;
    }

    public boolean isTheLeftmost() {//判断是不是最左侧的卡
        AbstractCard c = ModHelper.GetMostLeftCard();
        if(c == null) {
            return false;
        }
        return this.equals(c);
    }
    public void SelfHealing () {//判断是否可以执行自愈并且执行
        if (!hasRecovery) {
            AbstractCard c = ModHelper.GetMostLeftState();
            if (c != null) {
                hasRecovery = true;
                addToTop(new ExhaustSpecificCardAction(c,
                        AbstractDungeon.player.hand, true));
                logger.info("自愈成功！！");
                addToBot(action);//自愈的效果
            }
        }
    }
}