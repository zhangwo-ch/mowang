package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.powers.ServitorPower;

import java.util.Iterator;

public class GiveAllEnemyServitorAction extends AbstractGameAction {

    int magic;
    public GiveAllEnemyServitorAction(int magic) {
        this.magic = magic;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            if (!mo.isDead && !mo.isDying) {
                this.addToBot(new ApplyPowerAction(mo,p,new ServitorPower(mo,magic)));
            }
        }
        this.isDone = true;
        //
    }
}
