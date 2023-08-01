package mowang.Action;

import mowang.Helpers.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import mowang.powers.ServitorPower;

import static basemod.BaseMod.logger;

public class AfterScryCanReturnAction extends AbstractGameAction {
	private int mgc;
	public int[] x;
	public AfterScryCanReturnAction(int mgc, int[] x) {
		this.mgc = mgc;
		this.x = x;
		this.duration = Settings.ACTION_DUR_FAST;
	}

	public void update() {
		if (ModHelper.CanReturn(8,
				new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
				new ServitorPower(AbstractDungeon.player, x[0]))));

		if (ModHelper.CanReturn(12)) {
			addToBot((AbstractGameAction)new GiveAllEnemyServitorAction(this.mgc));
		}
		
		logger.info("========================" + x[0] +"========================");
		this.isDone = true;
	}
}
