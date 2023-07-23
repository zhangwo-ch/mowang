package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.MarchAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class InsightStrike extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(InsightStrike.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public InsightStrike() {
        super(ID, InsightStrike.class.getSimpleName(),
                cardStrings, 1, ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setupDamage(8);
        setupMagicNumber(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToEnemy(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        this.addToBot(new MarchAction(m));
        if (ModHelper.CanReturn(6)){
            this.addToBot(new ScryAction(this.magicNumber));
        }
        if (ModHelper.CanReturn(12)){
            this.addToBot(new DrawCardAction(1));
        }
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}
