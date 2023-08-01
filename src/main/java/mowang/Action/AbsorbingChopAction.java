package mowang.Action;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import mowang.powers.ServitorPower;

public class AbsorbingChopAction extends AbstractGameAction {
	private DamageInfo info;
	private int magic;
	private static final float DURATION = 0.1F;

	public AbsorbingChopAction(AbstractCreature target, DamageInfo info, int magic) {
		this.info = info;
		setValues(target, info);
		this.magic = magic;
		this.actionType = ActionType.DAMAGE;
		this.duration = 0.1F;
	}
	public AbsorbingChopAction(AbstractCreature target, DamageInfo info) {
		this.info = info;
		setValues(target, info);
		this.actionType = ActionType.DAMAGE;
		this.duration = 0.1F;
	}
	public void update() {
		if (this.duration == 0.1F && this.target != null) {
			int res;
			AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HEAVY));
			AbstractMonster mon = (AbstractMonster)this.target;
			int tmp = mon.currentHealth;
			this.target.damage(this.info);
			if (mon.isDying) {
				res = tmp;
			} else {
				res = tmp - mon.currentHealth;
			} 
			if (res > 0)
				this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new ServitorPower(AbstractDungeon.player,res)));
			if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
				AbstractDungeon.actionManager.clearPostCombatActions(); 
		} 
		tickDuration();
	}
}
