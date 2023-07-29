package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleTapPower;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;

public class Intuition extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(Intuition.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public Intuition() {
        super(ID, Intuition.class.getSimpleName(),
                cardStrings, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.block = this.baseBlock = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	addToBot(new GainBlockAction(p, p, this.block));
    	addToBot(new ApplyPowerAction(p, p, new FreeAttackPower(p, 1), 1));
    	ModHelper.CanReturn(20,new ApplyPowerAction(p,p,new DoubleTapPower(p,1), 1));
    }
    
    public void triggerOnGlowCheck() {
		if (ModHelper.CanReturn(20)) {
			this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
		} else
			this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
	}
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeBlock(4);
    }
}