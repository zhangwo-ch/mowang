package mowang.Action;

import com.badlogic.gdx.utils.Logger;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import static basemod.BaseMod.logger;

import mowang.Cards.Attack.Komorebi;
import mowang.powers.ServitorPower;

import java.util.Iterator;

public class KomorebiAction extends AbstractGameAction {

	private AbstractPlayer p;
	
    public KomorebiAction() {
    	this.p = AbstractDungeon.player;
    }

    public void update() {
    	for(AbstractCard c : p.discardPile.group) {
			//logger.info("====================="+ c.cardID + "=====================");
    		if(c.cardID.equals(Komorebi.ID)) {
    			addToBot((AbstractGameAction)new DiscardToHandAction(c));
    		}
    	}
        this.isDone = true;
        //
    }
}
