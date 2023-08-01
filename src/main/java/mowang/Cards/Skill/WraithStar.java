package mowang.Cards.Skill;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;

import mowang.Action.ActionAction;
import mowang.Action.EmptyAction;
import mowang.Action.GiveAllEnemyServitorAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

public class WraithStar extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(WraithStar.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public WraithStar() {
        super(ID, DemonWithSigning.class.getSimpleName(),
                cardStrings, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.block  = this.baseBlock = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		gainBlock();
    	int count=0;
    	if(ModHelper.CanReturn(24)) count +=1;
    	if(ModHelper.CanReturn(8)) count +=1;
    	if(count>0) {
    		ModHelper.CanReturn(1,new EmptyAction());
	    	if(count>=2) {
	    		addToBot(new BetterDiscardPileToHandAction(1,0));
	    	}else {
	    		addToBot(new BetterDiscardPileToHandAction(1));
	    	}
	    }
    	
    }
    
    public void triggerOnGlowCheck() {
		if (ModHelper.CanReturn(24)) {
			this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
		} else if(ModHelper.CanReturn(8)){
			this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
		} else
			this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
	}
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeBlock(3);
    }
}