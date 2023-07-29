package mowang.Cards.Skill;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import mowang.Action.EmptyAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import mowang.powers.ServitorPower;

public class DemonInMist extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(DemonInMist.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public DemonInMist() {
        super(ID, DemonInMist.class.getSimpleName(),
                cardStrings, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	int count=0;
    	for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
    		if(!mo.isDead &&  mo.hasPower(ServitorPower.POWER_ID)) {
    			count+=1;
    		}
    	}
    	if(p.hasPower(ServitorPower.POWER_ID)) {
			count+=1;
		}
    	if(count>0)
    		addToBot((AbstractGameAction)new GainEnergyAction(count));
    	
    	ModHelper.CanReturn(13,new ApplyPowerAction(p,p,new BlurPower(p,this.magicNumber), this.magicNumber));
    	
    	
    	if (ModHelper.CanReturn(23)){
    		addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, this.magicNumber), this.magicNumber));
    	}
    	
    }
    
    public void triggerOnGlowCheck() {
		if (ModHelper.CanReturn(23)) {
			this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
		} else if(ModHelper.CanReturn(13)){
			this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
		} else
			this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
	}
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeMagicNumber(1);
    }
}