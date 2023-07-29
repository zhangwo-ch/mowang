package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import mowang.powers.ServitorPower;

public class WraithStrikeHealAction extends AbstractGameAction {

	private int amount;
    public WraithStrikeHealAction(int amount) {
		this.amount = amount;
	}
    
    public void update() {
    	for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
			if (!mo.isDead) {
				addToBot(new ApplyPowerAction(mo,AbstractDungeon.player,new ServitorPower(mo,this.amount)));
			}
        }
        addToBot(new CallBackAction());
        this.isDone = true;
    }
}
