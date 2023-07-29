package mowang.Action;

import mowang.ModCore.ExampleMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PlayCardAction extends AbstractGameAction {
	private AbstractCard card;
	public PlayCardAction(AbstractCard c) {
		this.duration = Settings.ACTION_DUR_FAST;
		this.card = c;
	}

	public void update() {
		this.target = (AbstractCreature)AbstractDungeon.getMonsters().getRandomMonster(true);
		
		AbstractDungeon.player.limbo.group.add(card);
		card.current_x = Settings.WIDTH / 2.0F;
		card.current_y = Settings.HEIGHT / 2.0F;
		card.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
		card.target_y = Settings.HEIGHT / 2.0F;
		card.freeToPlayOnce = true;
		card.purgeOnUse = true;
		card.targetAngle = 0.0F;
		card.drawScale = 0.12F;
		card.lighten(false);
		//ExampleMod.logger.info("打出一张牌 : card : " + card.cardID + " ; target : " + this.target.id);
		card.applyPowers();
		AbstractDungeon.actionManager.currentAction = null;
		AbstractDungeon.actionManager.addToTop(this);
		AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(card, (AbstractMonster)this.target));
		if (!Settings.FAST_MODE) {
			AbstractDungeon.actionManager.addToTop((AbstractGameAction)new WaitAction(Settings.ACTION_DUR_MED));
		} else {
			AbstractDungeon.actionManager.addToTop((AbstractGameAction)new WaitAction(Settings.ACTION_DUR_FASTER));
		} 
		this.isDone = true;
	}
}
