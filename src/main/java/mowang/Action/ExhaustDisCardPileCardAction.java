package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;

public class ExhaustDisCardPileCardAction extends AbstractGameAction {

    private int numberOfCards;
    private AbstractPlayer player;

    public ExhaustDisCardPileCardAction(int numberOfCards) {
        this.numberOfCards = numberOfCards;
        player = AbstractDungeon.player;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (!this.player.discardPile.isEmpty() && this.numberOfCards > 0) {
                AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards,true, "选择消耗任意张牌。");
                this.tickDuration();
            } else {
                this.isDone = true;
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {

                ArrayList<AbstractCard> selectedCards = AbstractDungeon.gridSelectScreen.selectedCards;
                for (AbstractCard c : selectedCards) {

                    this.player.discardPile.moveToExhaustPile(c);
//                    this.addToBot(new ExhaustSpecificCardAction(c, player.discardPile));
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }
            this.tickDuration();
        }
    }
}
