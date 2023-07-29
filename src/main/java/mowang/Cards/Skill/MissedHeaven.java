package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Action.HeavenAction;
import mowang.Helpers.ModHelper;

public class MissedHeaven extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(MissedHeaven.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public MissedHeaven() {
        super(ID, MissedHeaven.class.getSimpleName(),
                cardStrings, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	addToBot((AbstractGameAction)new HeavenAction(p, m, this.upgraded, this.freeToPlayOnce, this.energyOnUse));
    }
    
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
		this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
		initializeDescription();
    }
}