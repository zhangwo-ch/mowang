package mowang.Cards.demo;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import mowang.powers.shimoPower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class geishimo extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(geishimo.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public geishimo() {
        super(ID, geishimo.class.getSimpleName(),
                cardStrings, 1, ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
    setupMagicNumber(6);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToPlayer(new shimoPower(p,magicNumber));
        this.addToBot(new ApplyPowerAction(m,p,new shimoPower(m,magicNumber)));
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeMagicNumber(3);
    }
}
