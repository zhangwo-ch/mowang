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

    public AbstractGameAction action;
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
                            CardRarity RARITY, CardTarget TARGET) {
        super(ID, s, strings, COST, TYPE,
                RARITY, TARGET);
        hasRecovery = false;
        tags.add(Recovery);
    }
    // 通常用这个，你AE的最后一个参数加action就可以，简单action可以不用创建action文件夹中，用匿名
    public AbstractHealCard(String ID, String s, CardStrings strings, int COST, CardType TYPE,
                            CardRarity RARITY, CardTarget TARGET,AbstractGameAction action) {
        super(ID, s, strings, COST, TYPE,
                RARITY, TARGET);
        this.action = action;
        hasRecovery = false;
        tags.add(Recovery);
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

    // 思考，从性能上来考虑，是否应该先判断是否有状态牌才考虑自己是否是最左
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