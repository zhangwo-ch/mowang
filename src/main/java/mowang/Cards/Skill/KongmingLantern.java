package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import mowang.ModCore.ExampleMod;

public class KongmingLantern extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(KongmingLantern.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public KongmingLantern() {
        super(ID, KongmingLantern.class.getSimpleName(),
                cardStrings, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = 1;
        
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
    	return false;
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}
    
    
    @Override
	public void triggerOnExhaust() {
		applyPowers();
		addToBot((AbstractGameAction)new GainEnergyAction(this.magicNumber));
		
		for(int i=0;i<this.magicNumber;i++) {
			Lantern c = new Lantern();
			if(this.upgraded) c.update();
			addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(c, true));
		}
    }
    
    /*
    @Override
	public void atTurnStart() {
    	boolean res = false;
		for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {

	    	ExampleMod.logger.info("=======================c.uuid: " + c.name + "=========this.uuid: " + this.name + "=========================");
			if (c.uuid == this.uuid) {
				res = true;
			}
		}
    	if(res) {
    		addToBot((AbstractGameAction)new GainEnergyAction(1));
    		Lantern c = new Lantern();
			addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(c, true));
    	}
    }
    */
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeMagicNumber(1);
		this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
		initializeDescription();
    }
}