package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.powers.servitorPower;

import java.util.Iterator;

public class CallBackAction extends AbstractGameAction {

    public CallBackAction() {
    }

    public void update() {
        int amount = 0;
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            if (!mo.isDead && !mo.isDying) {
                if (mo.hasPower(servitorPower.POWER_ID)){
                    amount+=mo.getPower(servitorPower.POWER_ID).amount;
                    this.addToBot(new RemoveSpecificPowerAction(mo,mo, servitorPower.POWER_ID));
                }
            }
        }
        if (amount!=0){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new servitorPower(AbstractDungeon.player,amount)));
        }
        this.isDone = true;
        //
    }
}
