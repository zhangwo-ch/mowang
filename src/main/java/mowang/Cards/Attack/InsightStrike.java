package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.EmptyAction;
import mowang.Action.MarchAction;
import mowang.Action.DelayAAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

public class InsightStrike extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(InsightStrike.class.getSimpleName());

    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public InsightStrike() {
        super(ID, InsightStrike.class.getSimpleName(), cardStrings, 1, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
        setupDamage(8);
        setupMagicNumber(3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToEnemy(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        addToBot((AbstractGameAction) new MarchAction((AbstractCreature) m));
        if (ModHelper.CanReturn(4, (AbstractGameAction) new EmptyAction()).booleanValue())
            addToBot((AbstractGameAction) new ScryAction(this.magicNumber));

        addToBot(new DelayAAction(new AbstractGameAction() {
            @Override
            public void update() {
                if (ModHelper.CanReturn(12))
                    addToBot((AbstractGameAction) new DrawCardAction(1));
                this.isDone = true;
            }
        }
        ));
    }

    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}
