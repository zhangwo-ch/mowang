package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import mowang.Cards.AbstractHealCard;
import mowang.Cards.Skill.Devipower;
import mowang.Cards.Skill.Lantern;

import java.util.Iterator;

import static mowang.Characters.MyCharacter.Enums.Recovery;

public class ResetRecoveryAction extends AbstractGameAction {

    AbstractCard baseCard;
    public ResetRecoveryAction(AbstractCard baseCard) {
        this.baseCard = baseCard;
    }

    public void update() {
        Iterator<AbstractCard> var = AbstractDungeon.player.hand.group.iterator();
        while (var.hasNext()){
            AbstractCard ac = var.next();
            if (ac.hasTag(Recovery)){
                AbstractHealCard ahc = (AbstractHealCard) ac;
                if (ahc instanceof Devipower){
                    continue;
                }
                if (ahc.hasRecovery){
                    ahc.hasRecovery = false;
                    ahc.flash();
                }
            }
        }
        this.isDone = true;
    }
}
