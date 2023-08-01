package mowang.Action.Return;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Helpers.ModHelper;
import mowang.powers.ServitorPower;

import java.util.Iterator;

public class Return2PAction extends AbstractGameAction {

    private final int magicNumber;
    private final AbstractGameAction action;

    public Return2PAction(int magicNumber, AbstractGameAction action) {
        this.magicNumber = magicNumber;
        this.action = action;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        ModHelper.hasReturn = true;
        ModHelper.ReturnCount++;
        AbstractDungeon.actionManager.addToBottom(action);
        if (p.hasPower("StarringChopPower")){
            AbstractDungeon.actionManager.addToBottom(new
                    ReducePowerAction(p,p,"StarringChopPower",1));
        }
        this.isDone = true;
    }
}
