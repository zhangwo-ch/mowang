package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.powers.ServitorPower;

import java.util.Iterator;

public class DelayAAction extends AbstractGameAction {

	AbstractGameAction a;
    public DelayAAction(AbstractGameAction a) {
        this.a = a;
    }

    public void update() {
    	addToBot(a);

        this.isDone = true;
        //
    }
}
