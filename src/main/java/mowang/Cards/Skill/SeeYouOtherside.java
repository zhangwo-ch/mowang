package mowang.Cards.Skill;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

public class SeeYouOtherside extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(SeeYouOtherside.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public SeeYouOtherside() {
        super(ID, SeeYouOtherside.class.getSimpleName(),
                cardStrings, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        setupBlock(4);
        setupMagicNumber(4);
    }
    public void gainMoreBlock(){
        upgradeBlock(magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeBlock(2);
        upgradeMagicNumber(2);
    }
}