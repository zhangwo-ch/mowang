package mowang.Action;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import mowang.Cards.Skill.Lantern;
import mowang.ModCore.ExampleMod;

public class KongmingLanternAction extends AbstractGameAction {
	private static final float DURATION = 0.1F;

	public KongmingLanternAction() {
		this.actionType = ActionType.CARD_MANIPULATION;
		this.duration = 0.1F;
	}

	public void update() {
		if (this.duration == 0.1F) {
			int res = 0,upgrade = 0;
			for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {

				//ExampleMod.logger.info("=======================c.name: " + c.name + "============================");
				if (c.cardID.equals("mowang:KongmingLantern")) {
					res += 1;
					if(c.upgraded) upgrade+=1;
				}
			}
			for(int i=0;i<res-upgrade;i++){
				addToBot(new GainEnergyAction(1));
				Lantern c = new Lantern();
				addToBot(new MakeTempCardInDiscardAction(c, true));
			}
			for(int i=0;i<upgrade;i++){
				addToBot(new GainEnergyAction(1));
				Lantern c = new Lantern();
				c.upgrade();
				addToBot(new MakeTempCardInDiscardAction(c, true));
			}
		} 
		tickDuration();
		this.isDone = true;
	}
}
