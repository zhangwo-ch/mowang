package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.Skill.Lantern;
import mowang.powers.ServitorPower;

import java.util.Iterator;

public class AddLanternAction extends AbstractGameAction {

    AbstractCard baseCard;
    private Boolean useMagic;
    public AddLanternAction(AbstractCard baseCard) {
        this.baseCard = baseCard;
        useMagic = false;
    }
    public AddLanternAction(AbstractCard baseCard,Boolean useMagic) {
        this.baseCard = baseCard;
        this.useMagic = useMagic;
    }
    public void update() {
        AbstractCard c = new Lantern();
        if (baseCard.upgraded){
            c.upgrade();
        }
        if (useMagic){
            addToBot(new MakeTempCardInHandAction(c,c.magicNumber));
        }else {
            addToBot(new MakeTempCardInHandAction(c,1));
        }
        this.isDone = true;
    }
}
