package mowang.Cards.Skill;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Wound;

import mowang.Action.ActionAction;
import mowang.Action.GiveAllEnemyServitorAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import mowang.powers.ServitorPower;

public class DemonWithSigning extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(DemonWithSigning.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public DemonWithSigning() {
        super(ID, DemonWithSigning.class.getSimpleName(),
                cardStrings, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	addToBot(new GiveAllEnemyServitorAction(this.magicNumber));
    	addToBot(new ApplyPowerAction(p,p,new ServitorPower(p,this.magicNumber)));
    	addToBot(new MakeTempCardInDrawPileAction(new Dazed(), 1, true, true));
    	
    	ModHelper.CanReturn(13, new ActionAction());
    	
    	if(ModHelper.CanReturn(23)) {
    		addToBot(new ActionAction());
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