package mowang.Action;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;

public class FetchAllBurnFromDisCardPileAction extends AbstractGameAction {

    public FetchAllBurnFromDisCardPileAction() {
    }

    public void update() {
        Iterator<AbstractCard> var3 = AbstractDungeon.player.discardPile.group.iterator();
        int nowHandcount = AbstractDungeon.player.hand.group.size();
        ArrayList<AbstractCard> planToAddCards = new ArrayList<>();
        while (var3.hasNext() && nowHandcount< BaseMod.MAX_HAND_SIZE){
            AbstractCard card = var3.next();
            if (card instanceof Burn){
                planToAddCards.add(card);
                nowHandcount++;
            }
        }
        for (AbstractCard c:planToAddCards
             ) {
            this.addToBot(new DiscardToHandAction(c));
        }

        this.isDone = true;
        //
    }
}
