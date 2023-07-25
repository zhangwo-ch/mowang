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
    public AddLanternAction(AbstractCard baseCard) {
        this.baseCard = baseCard;
    }

    public void update() {
        AbstractCard c = new Lantern();
        if (baseCard.upgraded){
            c.upgrade();
        }
        addToBot(new MakeTempCardInHandAction(c,1));
        this.isDone = true;
    }
}
