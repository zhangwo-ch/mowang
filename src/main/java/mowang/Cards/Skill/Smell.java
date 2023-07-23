package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

public class Smell extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(Smell.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public Smell() {
        super(ID, Smell.class.getSimpleName(),
                cardStrings, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        setupBlock(3);
        setupMagicNumber(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        this.addToBot(new ScryAction(this.magicNumber));
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeBlock(1);
        upgradeMagicNumber(1);
    }
}