package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

public class Lantern extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(Lantern.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public Lantern() {
        super(ID, Lantern.class.getSimpleName(),
                cardStrings, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        setupBlock(4);
        setupMagicNumber(4);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        if (upgraded){
            this.addToBot(new ScryAction(this.magicNumber));
        }
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeDescription(cardStrings);
    }
}