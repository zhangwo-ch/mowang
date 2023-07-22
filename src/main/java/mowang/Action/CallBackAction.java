package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.powers.ServitorPower;

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
                if (mo.hasPower(ServitorPower.POWER_ID)){
                    amount+=mo.getPower(ServitorPower.POWER_ID).amount;
                    this.addToBot(new RemoveSpecificPowerAction(mo,mo, ServitorPower.POWER_ID));
                }
            }
        }
        if (amount!=0){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new ServitorPower(AbstractDungeon.player,amount)));
        }
        this.isDone = true;
        //
    }
}
