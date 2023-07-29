package mowang.Cards.Skill;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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
import com.megacrit.cardcrawl.powers.DoubleTapPower;

import mowang.Action.EmptyAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

public class DemonUnderLunar extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(DemonUnderLunar.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public DemonUnderLunar() {
        super(ID, DemonUnderLunar.class.getSimpleName(),
                cardStrings, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	for(int i=0;i<this.magicNumber;i++) {
			Lantern c = new Lantern();
			addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(c, true));
		}
    	
    	ModHelper.CanReturn(13,new BetterDiscardPileToHandAction(1));
    	
    	
    	if (ModHelper.CanReturn(23)){
    		if (!p.powers.isEmpty()) {
    			for (AbstractPower pow : p.powers) {
    				if (pow.type == AbstractPower.PowerType.DEBUFF) {
    					addToBot((AbstractGameAction)new RemoveSpecificPowerAction(p, p, pow.ID));
    				}
    			}
    		} 
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