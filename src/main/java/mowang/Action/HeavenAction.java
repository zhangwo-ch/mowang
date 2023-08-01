package mowang.Action;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import mowang.Cards.Skill.Lantern;

public class HeavenAction extends AbstractGameAction {
	int damage =1;
	private boolean freeToPlayOnce = false;

	private DamageInfo.DamageType damageType;

	private AbstractPlayer p;
	private AbstractMonster m;

	private int energyOnUse = -1;
	private boolean upgrade;
	
	public HeavenAction(AbstractPlayer p, AbstractMonster m, boolean upgrade, boolean freeToPlayOnce, int energyOnUse) {
		this.p = p;
		this.m = m;
		this.upgrade = upgrade;
		this.freeToPlayOnce = freeToPlayOnce;
		this.duration = Settings.ACTION_DUR_XFAST;
		this.actionType = ActionType.SPECIAL;
		this.energyOnUse = energyOnUse;
	}

	public void update() {
		int effect = EnergyPanel.totalCount;
		if (this.energyOnUse != -1)
			effect = this.energyOnUse; 
		if (this.p.hasRelic("Chemical X")) {
			effect += 2;
			this.p.getRelic("Chemical X").flash();
		} 
		if (effect > 0) {
			addToBot(new LoseHPAction(p, p, effect));
			addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, effect), effect));
			
			for(int i=0;i<effect;i++) {
	    		addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(new Burn(), true));
	    		Lantern c = new Lantern();
	    		if(this.upgrade)
					c.upgrade();
	    		addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(c, true));
			}
			
			if (!this.freeToPlayOnce)
				this.p.energy.use(EnergyPanel.totalCount); 
		} 
		this.isDone = true;
	}
}
