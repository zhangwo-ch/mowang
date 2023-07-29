package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.powers.ServitorPower;

import java.util.Iterator;

public class DevibarrierAction extends AbstractGameAction {

    public DevibarrierAction() {
    }

    public void update() {
    	int SumBlk=0;
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            if (!mo.isDead && !mo.isDying) {
                if (mo.hasPower(ServitorPower.POWER_ID)){
                    SumBlk+=mo.getPower(ServitorPower.POWER_ID).amount;
                }
            }
        }
        
        if(AbstractDungeon.player.hasPower(ServitorPower.POWER_ID)) {
        	SumBlk+=AbstractDungeon.player.getPower(ServitorPower.POWER_ID).amount;
        }
        
        addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, SumBlk));
        this.isDone = true;
        //
    }
}
