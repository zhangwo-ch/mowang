package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;


public class RainFallAction extends AbstractGameAction{
	private AbstractPlayer p = AbstractDungeon.player;
	public RainFallAction() {
		setValues((AbstractCreature)this.p, (AbstractCreature)AbstractDungeon.player, amount);
		this.actionType = ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_MED;
	}

	@Override
	public void update() {
		if (this.duration == Settings.ACTION_DUR_MED) {
			CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

			tmp = this.p.drawPile;

			this.p.drawPile = this.p.discardPile;

			this.p.discardPile = tmp;

			this.p.drawPile.shuffle();

			this.isDone = true;
			tickDuration();
		} 
	}

}

