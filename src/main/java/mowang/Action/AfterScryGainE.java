package mowang.Action;

import mowang.ModCore.ExampleMod;
import mowang.Helpers.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AfterScryGainE extends AbstractGameAction {
	public AfterScryGainE() {
		this.duration = Settings.ACTION_DUR_FAST;
	}

	public void update() {

		if (ModHelper.CanReturn(8)) {
			addToBot(new GainEnergyAction(1));
		}
		this.isDone = true;
	}
}
