package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.GameActionManager;


import java.util.Iterator;


public class HikariAction extends AbstractGameAction{
	private AbstractPlayer p;
	private String cardID;
	private int dexterity;

	public HikariAction(String cardID, int dexterity) {
		this.actionType = ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_MED;
		this.p = AbstractDungeon.player;
		this.cardID = cardID;
		this.dexterity = dexterity;
	}

	public void update() {
		if (this.duration == Settings.ACTION_DUR_MED) {
			CardGroup ranc = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
			Iterator<AbstractCard> var5 = this.p.drawPile.group.iterator();

			while (var5.hasNext()) {
				AbstractCard c = var5.next();

				if(c.cardID.equals(this.cardID))
					ranc.addToRandomSpot(c);
			} 

			if (ranc.size() == 0) {
				this.isDone = true;
				return;
			}

			int check = ranc.size();

			addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, (4+this.dexterity)*check));

			Iterator<AbstractCard> var6 = ranc.group.iterator();
			while(var6.hasNext() && check>0) {
				AbstractCard c = var6.next();
				check-=1;

				this.p.drawPile.moveToDiscardPile(c);
				GameActionManager.incrementDiscard(false);
				c.triggerOnManualDiscard();
				this.p.drawPile.removeCard(c);

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

