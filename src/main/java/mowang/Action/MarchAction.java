package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.powers.servitorPower;

import java.util.Iterator;

public class MarchAction extends AbstractGameAction {

    AbstractCreature target;
    public MarchAction(AbstractCreature target) {
        this.target = target;
    }

    public void update() {
        int amount = 0;
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            if (!mo.isDead && !mo.isDying) {
                if (mo.hasPower(servitorPower.POWER_ID) && mo != target){
                    amount+=mo.getPower(servitorPower.POWER_ID).amount;
                    this.addToBot(new RemoveSpecificPowerAction(mo,mo, servitorPower.POWER_ID));
                }
            }
        }
        amount += AbstractDungeon.player.getPower(servitorPower.POWER_ID).amount;
        this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player,AbstractDungeon.player, servitorPower.POWER_ID));
        if (amount!=0){
            this.addToBot(new ApplyPowerAction(target,AbstractDungeon.player,new servitorPower(target,amount)));
        }

        this.isDone = true;
        //
    }
}
