package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


import java.util.Iterator;


public class DrawFromDiscard extends AbstractGameAction{
	private int numberOfCards;
	private AbstractPlayer p;

	public DrawFromDiscard(int numberOfCards) {
		this.actionType = ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_MED;
		this.p = AbstractDungeon.player;
		this.numberOfCards = numberOfCards;
	}

	public void update() {
		if (this.numberOfCards == 0) {
			this.isDone = true;
			return;
		}
		if (this.duration == Settings.ACTION_DUR_MED) {
			CardGroup ranc = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
			Iterator<AbstractCard> var5 = this.p.discardPile.group.iterator();

			while (var5.hasNext()) {
				ranc.addToRandomSpot(var5.next());
			} 

			if (ranc.size() == 0) {
				this.isDone = true;
				return;
			}
			if (ranc.size() <= this.numberOfCards) {
				for (int i = 0; i < ranc.size(); i++) {
					AbstractCard card = ranc.getNCardFromTop(i);
					if (this.p.hand.size() == 10) {
						this.p.createHandIsFullDialog();
					} else {
						this.p.discardPile.removeCard(card);
						this.p.hand.addToTop(card);
						card.unhover();
						card.applyPowers();
					} 
				}
				this.p.hand.refreshHandLayout();
				this.p.hand.applyPowers();
				for (AbstractCard c : this.p.discardPile.group) {
					c.unhover();
					c.target_x = CardGroup.DISCARD_PILE_X;
					c.target_y = 0.0F;
				} 
				this.isDone = true;
				return;
			}

			int check = this.numberOfCards;
			Iterator<AbstractCard> var6 = ranc.group.iterator();
			while(var6.hasNext() && check>0) {
				AbstractCard c = var6.next();
				check-=1;
				c.unhover();
				if (this.p.hand.size() == 10) {
					this.p.createHandIsFullDialog();
				} else {
					this.p.discardPile.removeCard(c);
					this.p.hand.addToTop(c);
				}
			}
			for (AbstractCard c : this.p.discardPile.group) {
				c.unhover();
				c.target_x = CardGroup.DISCARD_PILE_X;
				c.target_y = 0.0F;
			} 
			this.p.hand.refreshHandLayout();
			this.p.hand.applyPowers();
			this.isDone = true;
			return;
		}
	}
}

