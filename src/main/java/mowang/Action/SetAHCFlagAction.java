package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import mowang.Cards.AbstractHealCard;

public class SetAHCFlagAction extends AbstractGameAction {

    private final AbstractHealCard card;

    public SetAHCFlagAction(AbstractHealCard card) {
        this.card = card;
    }

    public void update() {
        card.hasRecovery = true;
        card.RecoveryIng = false;
        this.isDone = true;
    }
}
