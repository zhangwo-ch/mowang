package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import mowang.Cards.Skill.Lantern;

public class AddLanternToDisCardAction extends AbstractGameAction {

    AbstractCard baseCard;
    private int Magic;
    public AddLanternToDisCardAction(AbstractCard baseCard) {
        this.baseCard = baseCard;
        Magic = 1;
    }
    public AddLanternToDisCardAction(AbstractCard baseCard, int Magic) {
        this.baseCard = baseCard;
        this.Magic = Magic;
    }
    public void update() {
        addToBot(new MakeTempCardInDiscardAction(new Lantern(),Magic));

        this.isDone = true;
    }
}
