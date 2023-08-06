package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.ActionAction;
import mowang.Action.MarchAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import mowang.powers.ServitorPower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class ExpellingChop extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(ExpellingChop.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public ExpellingChop() {
        super(ID, ExpellingChop.class.getSimpleName(),
                cardStrings, 1, ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.setupDamage(8);
        upgradeMagicNumber(6);
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToAllEnemies(AbstractGameAction.AttackEffect.FIRE);
        ModHelper.CanReturn(9, new AbstractGameAction() {
            @Override
            public void update() {
                addToBot(new ActionAction());
                isDone = true;
            }
        });
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
        upgradeMagicNumber(2);
    }
}
