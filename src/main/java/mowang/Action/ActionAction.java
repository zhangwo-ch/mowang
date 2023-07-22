package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.powers.ServitorPower;

import java.util.Iterator;

public class ActionAction extends AbstractGameAction {

    public ActionAction() {
    }

    public void update() {
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            if (!mo.isDead && !mo.isDying) {
                if (mo.hasPower(ServitorPower.POWER_ID)){
                    this.addToBot(new DamageAction(mo,new DamageInfo(AbstractDungeon.player, mo.getPower(ServitorPower.POWER_ID).amount,DamageInfo.DamageType.THORNS)));
                }
            }
        }
        this.isDone = true;
        //
    }
}
