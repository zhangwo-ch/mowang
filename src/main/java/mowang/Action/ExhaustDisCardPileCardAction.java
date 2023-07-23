package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.powers.ServitorPower;

import java.util.ArrayList;
import java.util.Iterator;

public class ExhaustDisCardPileCardAction extends AbstractGameAction {

    private int numberOfCards;
    private AbstractPlayer player;

    public ExhaustDisCardPileCardAction(int numberOfCards) {
        this.numberOfCards = numberOfCards;
        player = AbstractDungeon.player;
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
            Iterator var1;
            AbstractCard c;
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while (var1.hasNext()) {
                    c = (AbstractCard) var1.next();
                    this.addToBot(new ExhaustSpecificCardAction(c, player.discardPile));

                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }
}
