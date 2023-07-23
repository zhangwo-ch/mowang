package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.ExhaustDisCardPileCardAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Cards.Skill.Lantern;
import mowang.Helpers.ModHelper;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class FlameStrike extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(FlameStrike.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public FlameStrike() {
        super(ID, FlameStrike.class.getSimpleName(),
                cardStrings, 2, ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setupDamage(11);
        setupMagicNumber(2);
        cardsToPreview = new Lantern();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToEnemy(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if (ModHelper.CanReturn(7)){
            this.addToBot(new ExhaustDisCardPileCardAction(magicNumber));
        }
        if (ModHelper.CanReturn(14)){
            this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview.makeStatEquivalentCopy(), magicNumber, true, true));
        }
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(4);
        upgradeMagicNumber(1);
    }
}
